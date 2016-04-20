package com.connect.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.connect.view.Main;
import java.util.ArrayList;
/**
 *
 * @author jevaughnferguson
 */
public class ListenFromServer extends Thread{
	
	private Socket clientSocket;
	private ObjectOutputStream clientWriter;
	private ObjectInputStream clientReader;
	private Main cg;
	
	
	public ListenFromServer(Socket s, ObjectOutputStream cw, ObjectInputStream cr, Main cl) {
            clientSocket = s;
            try{
                    clientWriter = cw;
                    clientReader = cr;
                    cg = cl;

            }catch(Exception ioe){
                    //could not create read write streams
                    System.err.println("Error creating read and write streams");
                    ioe.printStackTrace();
            }
	}
	
	@Override
    public void run(){
        boolean logout = false;
        while(true){
            //if something is sent from the server
            //decide what should happen here

            try {

                Object messageObj =  clientReader.readObject();
                //System.out.println(messageObj.toString());
                switch( ((ChatMessage)messageObj).getType() ){
                    
                    case ChatMessage.AVAILABLE:
                        String[] connectedFriends = (String[]) ((ChatMessage)messageObj).getMessage();
                        if(connectedFriends ==  null || connectedFriends.length == 0){
                            cg.updateOnlineUserJList( "" , true);
                            //System.out.println("Number of friends: " +connectedFriends.length);
                        }else{
                            for(String s1 : connectedFriends){
                                //System.out.println("Friend: " + s1);
                                cg.updateOnlineUserJList( s1 , false);
                            }
                        }
                        break;
                    case ChatMessage.MESSAGE:
                        //System.out.println("a message to this client");
                        String message = (String) ((ChatMessage)messageObj).getMessage();
                        //System.out.println("Message: "+ message);
                        cg.addToChatStream(message,java.awt.Component.LEFT_ALIGNMENT);
                        break;
                    case ChatMessage.BROADCAST:
                        //group broadcast
                        
                        break;
                    case ChatMessage.IMAGEMESSAGE:
                        //image message
                        Object image = ((ChatMessage)messageObj).getMessage();
                        cg.addImageToChatStream(image,java.awt.Component.LEFT_ALIGNMENT);
                        //create a jlabel and change the icon property to that of the image
                        break;
                    case ChatMessage.REQUEST:
                        String[] requestResponse = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.responseToFriendRequest(requestResponse);
                        
                        break;
                    case ChatMessage.REQUESTINFO:
                        String[] requestingFriendship = (String[]) ((ChatMessage)messageObj).getMessage();
                        
                        cg.showRequestFriends(requestingFriendship);
                        
                        break;
                    case ChatMessage.REQUESTUPDATE:
                        String[] response = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.alertMessage(response);
                        //cg.showRequestFriends(requestingFriendship);
                        
                        break;
                    case ChatMessage.CREATEGROUP:
                        String[] groupInfo = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.alertMessage(groupInfo);
                        //cg.showRequestFriends(requestingFriendship);
                        
                        break;
                    case ChatMessage.LISTGROUPREQUEST:
                        String[] listOfRequestedGroups = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.showListOfGroupRequesting(listOfRequestedGroups);
                        //cg.showRequestFriends(requestingFriendship);
                        
                        break;
                    case ChatMessage.LISTGROUPS:
                        Object[] listOfMyGroups = (Object[]) ((ChatMessage)messageObj).getMessage();
                        cg.showMyGroups(listOfMyGroups);
                        //cg.showRequestFriends(requestingFriendship);
                        
                        break;
                    case ChatMessage.LISTMYGROUPS:
                       
                        Object[] LISTMYGROUPS = (Object[]) ((ChatMessage)messageObj).getMessage();
                        cg.showTheGroups(LISTMYGROUPS);
                        //cg.showRequestFriends(requestingFriendship);
                        
                        break;
                    case ChatMessage.DELETEMEMBERFROMGROUP:
                        String[] deleteResponse = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.alertMessage(deleteResponse);                        
                        break; 
                    case ChatMessage.ADDMEMBERTOGROUP:
                        String[] addResponse = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.alertMessage(addResponse);                        
                        break;
                    case ChatMessage.DELETEGROUP:
                        String[] deleteGroupResponse = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.alertMessage(deleteGroupResponse);                        
                        break;
                    case ChatMessage.DELETEMEMBERGROUPREQUEST:
                        String[] DELETEMEMBERGROUPREQUEST = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.alertMessage(DELETEMEMBERGROUPREQUEST);                        
                        break;
                    case ChatMessage.UPDATEMEMBERTOGROUP:
                        String[] UPDATEMEMBERTOGROUP = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.alertMessage(UPDATEMEMBERTOGROUP);                        
                        break;
                    case ChatMessage.LEAVEGROUP:
                        String[] LEAVEGROUP = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.alertMessage(LEAVEGROUP);                        
                        break; 
                    case ChatMessage.GETFRIENDS:
                        String[] GETFRIENDS = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.showFriends(GETFRIENDS);                        
                        break;
                    case ChatMessage.DELETEFRIEND:
                        String[] DELETEFRIEND = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.alertMessage(DELETEFRIEND);                     
                        break;
                    case ChatMessage.AUDIOCHATREQUEST:
                        String[] AUDIOCHATREQUEST = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.optionMessage(AUDIOCHATREQUEST, ChatMessage.AUDIOCHATREQUEST);                        
                        break;
                    case ChatMessage.VIDEOCHATREQUEST:
                        String[] VIDEOCHATREQUEST = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.optionMessage1(VIDEOCHATREQUEST, ChatMessage.VIDEOCHATREQUEST);                        
                        break; 
                    case ChatMessage.STOPAUDIOCHAT:
                        String[] STOPAUDIOCHAT = (String[]) ((ChatMessage)messageObj).getMessage();
                        //System.out.println(STOPAUDIOCHAT);
                        cg.optionMessage(STOPAUDIOCHAT, ChatMessage.STOPAUDIOCHAT);                        
                        break;
                    case ChatMessage.STOPVIDEOCHAT:
                        String[] STOPVIDEOCHAT = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.optionMessage1(STOPVIDEOCHAT, ChatMessage.STOPVIDEOCHAT);                        
                        break; 
                     case ChatMessage.BUSY:
                        String[] BUSY = (String[]) ((ChatMessage)messageObj).getMessage();
                        cg.optionMessage1(BUSY, ChatMessage.STOPVIDEOCHAT);                        
                        break;
                    case ChatMessage.GETMESSAGES:
                        ArrayList<String[]> GETMESSAGES = (ArrayList<String[]>) ((ChatMessage)messageObj).getMessage();
                        cg.updateChatStream(GETMESSAGES, ChatMessage.GETMESSAGES);                        
                        break; 
                    case ChatMessage.GETGROUPMESSAGES:
                        ArrayList<String[]> GETGROUPMESSAGES = (ArrayList<String[]>) ((ChatMessage)messageObj).getMessage();
                        cg.updateChatStream(GETGROUPMESSAGES, ChatMessage.GETGROUPMESSAGES);                        
                        break; 
                    case ChatMessage.LOGOUT:
                      //  String LOGOUT = (String) ((ChatMessage)messageObj).getMessage();
                        logout = true;
                        cg.logout();                        
                        break; 
                }
                //System.out.println("What's up here");

            } catch (ClassNotFoundException | IOException e) {
                if(logout == false){
                    JOptionPane.showMessageDialog(null,
                                "Server has gone offline, attempting to restore",
                                "Server not available",
                                JOptionPane.ERROR_MESSAGE);
                }
                logout =false;
                break;
            }
        }
    }
	
	public void disconnect() {

		try{
			if(clientWriter != null){
				clientWriter.close();
			}
			if(clientReader != null){
				clientReader.close();
			}
			if(clientSocket != null){
				clientSocket.close();
			}
		}catch(IOException ioe){
			//could not close stream
			ioe.printStackTrace();
		}
	}
	
}
