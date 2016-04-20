
package com.connect.videoclient;

import com.connect.controller.ClientController;
import com.connect.model.ChatMessage;
import com.connect.model.SECUREINFO;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author jevaughnferguson
 */
public class VideoClientUI extends javax.swing.JFrame {

    private ObjectInputStream clientReader;
    private ObjectOutputStream clientWriter;
    private Socket client;
    private static Image im;
    private VideoCapture webCam;
    private final Mat frame;
    private MatOfByte mem;
    private boolean runnable = true;
    private ClientController clientController;
    
    final String host = SECUREINFO.VIDEOHOST;
    final int port = SECUREINFO.VIDEOPORT;
    final String clientName;
    final String receiverName;
    
    public VideoClientUI(ClientController contr, String c, String r) {
            
            clientController = contr;
            clientName = c;
            receiverName = r;
            
            initComponents();
            setVisible(true);
            
            this.setTitle("Client name: " +clientName + " Receiver name: " +receiverName);
            setLocationRelativeTo(null);
            
            // load native library of opencv
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            
            //object from showing/and sending image
            //the type that the frame of the video will be in
            mem = new MatOfByte();
            //the webcam object
            webCam = null;
            //the entire video frame
            frame = new Mat();
        try {  
            
            client = new Socket(host, port);
            clientWriter = new ObjectOutputStream(client.getOutputStream());
            clientReader = new ObjectInputStream(client.getInputStream());
            
            //send user outputwriter
            clientWriter.writeObject(new String[]{c,r});
            
        } catch (IOException ex) {
            Logger.getLogger(VideoClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startVideoChat(){
        
        webCam = new VideoCapture(0);
        
        sendVideoThread sv = new sendVideoThread();
        sv.start();
        
        receiveVideoThread rv = new receiveVideoThread();
        rv.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Video Chat");
        setLocationByPlatform(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("videoContainer"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );

        jButton2.setText("End Call");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 429, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        runnable = false;           
        try{
            client.close();
            clientWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
	            
        webCam.release();	
        
        //send stop on other persons
         //if in chat
            
        clientController.stopVideoCall(new ChatMessage(ChatMessage.STOPVIDEOCHAT, clientName));
        clientController.stopVideoCall(new ChatMessage(ChatMessage.STOPVIDEOCHAT, receiverName));
        stopVideo();
        //--stop video of other
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        jButton2ActionPerformed(null);
    }//GEN-LAST:event_formWindowClosing
    
    public void stopVideo(){
         webCam.release();	
        runnable = false;
        this.dispose();
    }
    
    class receiveVideoThread extends Thread {
        @Override
        public void run() {
             synchronized(this)
	        {
                while(runnable){
                    try{
                        if(client != null){
                            if( client.isConnected() ){
                                //bytes that will form the image for the clients image
                                byte[] clientImageBytes = (byte[])clientReader.readObject();
                                //convert byte array to MarOfBytes

                                mem = new MatOfByte(clientImageBytes);
                                im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                                BufferedImage buff = (BufferedImage) im;

                                Graphics g = jPanel1.getGraphics();

                                g.drawImage(buff, 0, 0, getWidth() -500, getHeight() , 0, 0, buff.getWidth(), buff.getHeight(), null);
       
                            }
                        }
                    } catch (ClassNotFoundException | IOException e) {
                           runnable = false;
                           break;//don't need to break but yea
                    }
	        }
	     }
        }
    }

    class sendVideoThread extends Thread {

	    @Override
	    public  void run()
	    {
	        synchronized(this){
                    
                    while(runnable)
                    {
                      //System.out.println("here");
                        //get a frame(image) from the webcam
                        if(webCam.read(frame)) {
                            try
                            {
                            // webCam.retrieve(frame);
                             Imgcodecs.imencode(".jpg", frame, mem);//encodes the frame into a .jpg and store it in a matOfByte
                             Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));//
                             //bytes that will form the image for the clients image
                             byte[] clientImageBytes = mem.toArray();
                             //send image bytes to client
                             clientWriter.flush();
                             clientWriter.writeObject(clientImageBytes);

                             BufferedImage buff =  (BufferedImage) im;

                             Graphics g = jPanel2.getGraphics();

                             g.drawImage(buff, 0, 0, getWidth() -500, getHeight() , 0, 0, buff.getWidth(), buff.getHeight(), null);
 
                          }
                     catch(Exception ex)
                     {
                        System.out.println("Error");
                     }
                   }
                }
                     
                if(webCam != null){
                    webCam.release();
                }  
            }
        }
    }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
