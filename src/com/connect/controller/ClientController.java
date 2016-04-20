package com.connect.controller;

import com.connect.model.ChatMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.connect.model.ActionMessage;
import com.connect.model.ListenFromServer;
import com.connect.model.SECUREINFO;
import com.connect.view.LoginView;
import com.connect.view.Main;
import com.connect.view.Register;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author jevaughnferguson
 */
public class ClientController {
	private Socket clientSocket;
	private ObjectOutputStream clientWriter;
	private ObjectInputStream clientReader;
	private String clientUsername;

	private final String host;
	private final int port;
	@SuppressWarnings("unused")
	private final LoginView clientLogin;
	
	public ClientController(String h, int p){
		this.host = h;
		this.port =  p;
		this.clientLogin = new LoginView(this);
	}
	
	public boolean startClient(){
		try{
			this.clientSocket = new Socket(this.host, this.port);
			this.clientWriter = new ObjectOutputStream(clientSocket.getOutputStream());
			this.clientReader = new ObjectInputStream(clientSocket.getInputStream());
		}catch(IOException unk){
                    return false;
		}
		
                if(clientSocket != null  && !clientSocket.isClosed() ){
                    System.out.println("Connection accepted " + clientSocket.getInetAddress().getHostName() + ":" + clientSocket.getPort());
                }else{
                    return false;
                }
		
		// success we inform the caller that it worked		
		return true;
	}

	public int loginClient(String username, String password, Main cg){
            //ATTEMPT LOGIN IN HAPPENS IN HERE
            // Send our user name to the server this is the only message that we
            // will send as a String. All other messages will be ChatMessage objects

            try{
                    //System.out.println(user name + " " + password);//debugging
                    clientWriter.writeObject(new ActionMessage("login", new String[] { username, password } ));
                    //listen for response
                    String[] messageObj =  (String[]) clientReader.readObject();
                    //index 0 is the result
                    //index 1 is the sentence reason

                    String response = messageObj[0];
                    if(response.equalsIgnoreCase("success")){
                        clientUsername = username;
                        System.out.println(clientUsername+" credentials successfully validated \n Server: "+ messageObj[1]);
                        //then start listening for chat messages if connected

                        //create thread to keep listening to server
                        ListenFromServer listeningThread =  new ListenFromServer(clientSocket,clientWriter, clientReader,cg);
                        listeningThread.start();

                        return LoginView.SUCCESS;
                    }else if(response.equalsIgnoreCase("already")){
                        System.err.println("User already logged in \nMessage from server "+ messageObj[1]);
                        return LoginView.ALLOGGEDIN;
                    }else if(response.equalsIgnoreCase("serverdown")){
                        System.err.println("Server not accepting connections \nMessage from server "+ messageObj[1]);
                        return LoginView.SERVERDOWN;
                    }else{
                        System.err.println("User credentials unsuccessfully validated \nMessage from server "+ messageObj[1]);
                        return LoginView.INCORRECT;
                    }

            }
            catch (IOException eIO) {
                System.out.println("Exception doing login : " + eIO.toString());
                return LoginView.INCORRECT;
            } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return LoginView.INCORRECT;
            }
	}
	
        public int registerUser(ChatMessage chatMessage) {
            try{
                    clientWriter.writeObject(new ActionMessage("register",  chatMessage));
                    //listen for response
                    String[] messageObj =  (String[]) clientReader.readObject();
                    String response = messageObj[0];
                    //index 0 is the result
                    //index 1 is the sentence reason
                    /* 
                        ALLOGGEDIN = 1;
                        SUCCESS = 2;
                        INCORRECT = 3;
                        SERVERDOWN = 4;
                    */  
                   
                    if(response.equalsIgnoreCase("registered")){
                        System.err.println("User registered successfully \nMessage from server "+ messageObj[1]);
                        return Register.SUCCESS;
                    }else if(response.equalsIgnoreCase("serverdown")){
                        System.err.println("Server not accepting connections \nMessage from server "+ messageObj[1]);
                        return Register.SERVERDOWN;
                    }else if(response.equalsIgnoreCase("already")){
                        System.err.println("User already registered in \nMessage from server "+ messageObj[1]);
                        return Register.ALLOGGEDIN;
                    }else{
                        System.err.println("User not register successfully \nMessage from server "+ messageObj[1]);
                        return Register.INCORRECT;
                    }

            }
            catch (IOException eIO) {
                System.out.println("Exception doing registration : " + eIO.toString());
                eIO.printStackTrace();
                return LoginView.INCORRECT;
            } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return LoginView.INCORRECT;
            }
        }
        
	public boolean logoutClient(){
		try{
			//a logout command on client window close or on user logout action
			clientWriter.writeObject(new ActionMessage("logout",clientUsername));
			
			return true;
		}
		catch (IOException eIO) {
            System.out.println("Exception doing login : " + eIO.toString());
		}
		return false;
	}
	
	public void sendMsgToServer(Object msgObj){
		try{
			clientWriter.writeObject(msgObj);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	public void sendFriendRequest(Object msgObj){
		try{
			clientWriter.writeObject(msgObj);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
        
        public void getUserRequest(Object msgObj) {
            try {
                clientWriter.writeObject(msgObj);
            } catch (IOException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void updateFriendStatus(String updateUser) {
            try {
               // System.out.println("updateFriendStatus " +updateUser);
                clientWriter.writeObject(new ChatMessage(ChatMessage.REQUESTUPDATE, updateUser));
            } catch (IOException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void deleteFriendStatus(String updateUser) {
            try {
               // System.out.println("updateFriendStatus " +updateUser);
                clientWriter.writeObject(new ChatMessage(ChatMessage.DELETEREQUEST, updateUser));
            } catch (IOException ex) {
                Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        	
	public String getClientUsername(){
		return clientUsername;
	}       
        
        public void sendCreateGroupRequest(ChatMessage groupMessage) {
            try {
                   // System.out.println("updateFriendStatus " +updateUser);
                    clientWriter.writeObject(groupMessage);
                } catch (IOException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        public void getGroupRequest(ChatMessage chatMessage) {
            try {
                   // System.out.println("updateFriendStatus " +updateUser);
                    clientWriter.writeObject(chatMessage);
                } catch (IOException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
         public void getSubsribedGroupsWithMembers(ChatMessage chatMessage) {
            try {
                   // System.out.println("updateFriendStatus " +updateUser);
                    clientWriter.writeObject(chatMessage);
                } catch (IOException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
         
        public void deleteMemberFromGroup(ChatMessage chatMessage) {
            try {
                   // System.out.println("updateFriendStatus " +updateUser);
                    clientWriter.writeObject(chatMessage);
                } catch (IOException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        public void addMemberToGroup(ChatMessage chatMessage) {
            try {
                   // System.out.println("updateFriendStatus " +updateUser);
                    clientWriter.writeObject(chatMessage);
                } catch (IOException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        public void deleteMyGroup(ChatMessage chatMessage) {
        try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        public void updateMemberToGroup(ChatMessage chatMessage) {
         try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }

        public void deleteMemberFromGroupRequest(ChatMessage chatMessage) {
            try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        public void leaveGroup(ChatMessage chatMessage) {
            try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
         
        public void getMyFriends(ChatMessage chatMessage) {
            try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
         
        public void getMyOnlineFriends(ChatMessage chatMessage) {
            try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        public void sendGroupToServer(ChatMessage chatMessage) {
            try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        public void deleteFriend(ChatMessage chatMessage) {
            try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        public void respondToCallRequest(ChatMessage chatMessage){
            try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        public void stopAudioCall(ChatMessage chatMessage) {
            try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        
        public void sendLogoutRequest(ChatMessage chatMessage) {
            try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
         public void getMessagesFor(ChatMessage chatMessage) {
            try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }

        public void getMessagesForGroup(ChatMessage chatMessage) {
            try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        public void stopVideoCall(ChatMessage chatMessage) {
            try {
                // System.out.println("updateFriendStatus " +updateUser);
                 clientWriter.writeObject(chatMessage);
             } catch (IOException ex) {
                 Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }

        /**
         * Launch the application.
         * @param args
         */
        public static void main(String[] args) {
            try {
                System.out.println(InetAddress.getByName(SECUREINFO.CLIENTHOST).getHostName() +" " + SECUREINFO.CLIENTPORT) ;
                // machine
                ClientController clientController = new ClientController(SECUREINFO.CLIENTHOST, SECUREINFO.CLIENTPORT); 
            } catch (UnknownHostException e) {
                    e.printStackTrace();
            }
	}

    

   

    
}
