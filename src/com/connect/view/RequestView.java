/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect.view;

import com.connect.controller.ClientController;
import com.connect.model.ButtonColumn;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jevaughnferguson
 */
public class RequestView extends javax.swing.JInternalFrame {

    ClientController controller;
    /**
     * Creates new form RequestView
     * @param c
     * @param data
     */
    public RequestView(ClientController c,Object[] data) {
            
            controller = c;
            
            initComponents();
            
            Object columnHeaders[] = {"Requester username","Add user", "delete request"};
            //create table with data and headers
            final DefaultTableModel model = new DefaultTableModel(columnHeaders,0);
            
            
            for(Object d : data){
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
                    //System.out.println(row );
                   //System.out.println( model.getValueAt(row, 0));
                    //tell server to update relationship
                    String updateUser = model.getValueAt(row, 0).toString();
                    controller.updateFriendStatus(updateUser);
                    
                    //remove from list of friend request
                    model.removeRow(row);
                }
             }, 1);
            new ButtonColumn(jTable1, new AbstractAction(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = Integer.valueOf( e.getActionCommand());
                    String deleteUser = model.getValueAt(row, 0).toString();
                    controller.deleteFriendStatus(deleteUser);
                   // System.out.println(row  );
                    //System.out.println( model.getValueAt(row, 0));
                    model.removeRow(row);
                }
             }, 2);
            
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            setVisible(true);
     
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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
                "Requester", "Add friend", "Delete"
            }
        ));
        jTable1.setFocusTraversalKeysEnabled(false);
        jTable1.setRowHeight(24);
        jTable1.setRowMargin(3);
        jTable1.setRowSelectionAllowed(false);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowGrid(true);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
