/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect.view;

import com.connect.model.ChatMessage;
import com.connect.controller.ClientController;
import com.connect.model.ButtonColumn;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jevaughnferguson
 */
public class GroupChatRequest extends javax.swing.JInternalFrame {

     ClientController controller;
    /**
     * Creates new form GroupChatRequest
     * @param c
     * @param listOfGroups
     */
    public GroupChatRequest(ClientController c, String[] listOfGroups) {
         controller = c;
        initComponents();
        Object columnHeaders[] = {"Group name","Manage group", "manage join request"};
            //create table with data and headers
            final DefaultTableModel model = new DefaultTableModel(columnHeaders,0);
            
            
            for(Object d : listOfGroups){
                model.insertRow(model.getRowCount(), new Object[]{d, "add", "delete"});
            }
            //change the header on the tableHeader to a different font and center the text
            jTable1.setModel(model);
            jTable1.getTableHeader().setFont( new Font( "Arial" , Font.BOLD, 15 ));
            ((DefaultTableCellRenderer)jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
            //center the text of all Objects in the cells
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            jTable1.setDefaultRenderer(Object.class, centerRenderer);
            //actions for when button is clicked
            new ButtonColumn(jTable1, new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = Integer.valueOf( e.getActionCommand());
                    String group = model.getValueAt(row, 0).toString();
                    //System.out.println(row );
                   //System.out.println( model.getValueAt(row, 0));
                    //tell server to update relationship
                    
                    int response= JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to join "+group +" group",
                            "Please take note",
                            JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION) {

                     } else {
                        
                        controller.updateMemberToGroup(new ChatMessage(ChatMessage.UPDATEMEMBERTOGROUP, new String[]{controller.getClientUsername(),group }));

                        //remove from list of friend request
                        model.removeRow(row);

                    }
                }
             }, 1);
            new ButtonColumn(jTable1, new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = Integer.valueOf( e.getActionCommand());
                    String group = model.getValueAt(row, 0).toString();
                    
                    int response= JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to delete "+group +" group",
                            "Please take note",
                            JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION) {

                     } else {
                    
                    
                        controller.deleteMemberFromGroupRequest(new ChatMessage(ChatMessage.DELETEMEMBERGROUPREQUEST, new String[]{controller.getClientUsername(),group }));
                       // System.out.println(row  );
                        //System.out.println( model.getValueAt(row, 0));
                        model.removeRow(row);
                    }
                }
             }, 2);
            
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            setVisible(true);
     
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jTable1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Group name", "Join group", "Delete join request"
            }
        ));
        jTable1.setFocusTraversalKeysEnabled(false);
        jTable1.setRowHeight(24);
        jTable1.setRowMargin(3);
        jTable1.setRowSelectionAllowed(false);
        jTable1.setShowGrid(true);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
