
package com.connect.view;

import com.connect.controller.ClientController;
import javax.swing.JOptionPane;

/**
 *
 * @author jevaughnferguson
 */
public class LoginView extends javax.swing.JFrame {

    private final ClientController controller;
    public static final int ALLOGGEDIN = 1;
    public static final int SUCCESS = 2;
    public static final int INCORRECT = 3;
    public static final int SERVERDOWN = 4;
    
    public LoginView(ClientController cc, String n) {
        this( cc );
        jTextField1.setText(n);
        jPasswordField1.requestFocus();
    }
    
    public LoginView(ClientController cc) {
        this.controller = cc;
        initComponents();
        //center window
        setLocationRelativeTo(null);
        //so when enter button is press this event fires
        this.getRootPane().setDefaultButton(jButton1);
        //show
        setVisible(true);
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login to Connet");

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        jLabel1.setText("Welcome to Connect");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel3.setText("Password");

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Register");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(79, 79, 79))
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordField1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(143, 143, 143))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                    //user click login
                    //validate user name and password
                    //parse text fields 
                    String username = jTextField1.getText();
                    char[] password = jPasswordField1.getPassword(); 
                    if(controller.startClient()){
                            //boolean loginSuccess = ;
                            Main cg = new Main(controller);
                            //.getPassword returns a char[] so pass it to a String constructor to get a string version
                            int response = controller.loginClient( username, new String( password ),cg);
                            if(response == LoginView.SUCCESS){
                                    //dispose this window and show ClientGui
                                    LoginView.this.dispose();
                                    cg.setTitle("Welcome to connect " + username);
                                    cg.setVisible(true);
                                    //new ClientGui(controller);
                            }else if(response == LoginView.INCORRECT){
                                    //System.out.println("Figure this out");
                                    //user not a validated user
                                    //show a pop up tell user
                                    JOptionPane.showMessageDialog(getContentPane(),
                                                "Username password combination not available",
                                                "Check credentials",
                                                JOptionPane.ERROR_MESSAGE);
                                    jPasswordField1.setText("");
                                    cg.dispose();
                            }else if(response == LoginView.ALLOGGEDIN){
                                    JOptionPane.showMessageDialog(getContentPane(),
                                                "User already logged in",
                                                "Check credentials",
                                                JOptionPane.ERROR_MESSAGE);
                                    jPasswordField1.setText("");
                                    cg.dispose();
                            }
                            else if(response == LoginView.SERVERDOWN){
                                    JOptionPane.showMessageDialog(getContentPane(),
                                                "Could not connect to server",
                                                "Server unavailable",
                                                JOptionPane.ERROR_MESSAGE);
                                    jPasswordField1.setText("");
                                    cg.dispose();
                            }
                    }else{
                        JOptionPane.showMessageDialog(getContentPane(),
                                                "Could not connect to server, try again later",
                                                "Server unavailable",
                                                JOptionPane.ERROR_MESSAGE);
                    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Register(controller);
    }//GEN-LAST:event_jButton2ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    
}
