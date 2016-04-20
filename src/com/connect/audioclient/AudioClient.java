package com.connect.audioclient;

import com.connect.model.SECUREINFO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
/**
 *
 * @author jevaughnferguson
 */
public class AudioClient {

    boolean stopCapture = false;
    ByteArrayOutputStream byteArrayOutputStream;
    AudioFormat audioFormat;
    TargetDataLine targetDataLine;
    AudioInputStream audioInputStream;
    ObjectOutputStream out;
    ObjectInputStream in;
    Socket sock;
    SourceDataLine sourceDataLine;
    final String host = SECUREINFO.AUDIOHOST;
    final int port = SECUREINFO.AUDIOPORT;
    final int byteSize = 64;
    
    
    public AudioClient(){
        try {
           // AudioFormat(AudioFormat.Encoding encoding, 
            //float sampleRate, int sampleSizeInBits, 
            //int channels, int frameSize, 
            //float frameRate, boolean bigEndian)
            /*
            sampleRate - the number of samples per second 
            sampleSizeInBits - the number of bits in each sample 
            channels - the number of channels (1 for mono, 2 for stereo, and so on) 
            signed - indicates whether the data is signed or unsigned 
            bigEndian - indicates whether the data for a single sample is stored in big-endian byte order (false means little-endian) 
            */
            audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
            /*
            lineClass - the class of the data line described by the info object 
            format - desired format
            */
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
            
            targetDataLine = (TargetDataLine)AudioSystem.getLine(info);
            
            DataLine.Info dataLineInfo1 = new DataLine.Info(SourceDataLine.class, audioFormat);
            
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo1);
            
            
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean startAudioServer(String c, String r){
        try{
            sock = new Socket(host, port);
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
            
             //send user outputwriter
            out.writeObject(new String[]{c,r});
            
        } catch (IOException  ex) {
            return false;
        }
        
        if(sock != null  && !sock.isClosed() ){
            System.out.println("Connection accepted " + sock.getInetAddress().getHostName() + ":" + sock.getPort());
            return true;
        }else{
            return false;
        }

    }
    
    public void captureAudio() {
    try {

        targetDataLine.open(audioFormat);
        targetDataLine.start();

        Thread captureThread = new CaptureThread();
        captureThread.start();

        sourceDataLine.open(audioFormat);
        sourceDataLine.start();

        Thread playThread = new PlayThread();
        playThread.start();

    } catch (Exception e) {
       e.printStackTrace();
        }
    }

    class CaptureThread extends Thread {

        byte tempBuffer[] = new byte[byteSize];

        @Override
        public void run() {
            stopCapture = false;
            try {
                while (!stopCapture) {

                    targetDataLine.read(tempBuffer, 0,
                            tempBuffer.length);

                    out.write(tempBuffer);

                }

            } catch (Exception e) {
               e.printStackTrace();
               // System.exit(0);
            }
        }
    }

    class PlayThread extends Thread {

        byte tempBuffer[] = new byte[byteSize];

        @Override
        public void run() {
            try {
                while (in.read(tempBuffer) != -1 && !stopCapture) {
                    sourceDataLine.write(tempBuffer, 0, byteSize);

                }
                sourceDataLine.drain();
                sourceDataLine.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
      }

    public void stopAudiChat(){
        stopCapture = true;
    }
    
}
