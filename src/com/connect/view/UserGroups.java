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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jevaughnferguson
 */
public class UserGroups extends javax.swing.JInternalFrame {
    
    ClientController controller;
    final DefaultTableModel model, model1;
    Object[] listOfGroups;
    final Object columnHeaders[] = {"Member name", "Manage group" };
    /**
     * Creates new form UserGroups
     * @param c
     * @param lg
     */
    public UserGroups(ClientController c, Object[] lg) {
        controller = c;
        listOfGroups= lg;
        initComponents();
        
        jList2.setFixedCellHeight(24);
        jList1.setFixedCellHeight(24);
        final DefaultListModel jList2list = new DefaultListModel();
        final DefaultListModel jList1list = new DefaultListModel();
        
         
        //create table with data and headers
        model = new DefaultTableModel(columnHeaders,0);
        model1 = new DefaultTableModel(new Object[]{"Member name"},0);
       
        if(listOfGroups[0] != null){
            for(Object d : (ArrayList<String>)(listOfGroups[0])){
                jList2list.addElement(d);
            }
        }
        if(listOfGroups[2] != null){
            for(Object d : (ArrayList<String>)(listOfGroups[2])){
                jList1list.addElement(d);
            }
        }
        
        if(listOfGroups[1] != null){
            for(Map.Entry<String, ArrayList> d : ((LinkedHashMap<String, ArrayList>)listOfGroups[1]).entrySet()){
                System.out.println(d);
                if(d == null) continue;
                if(d.getValue()== null) break;
                for( Object e : d.getValue() ) {
                     model.insertRow(model.getRowCount(), 
                        new Object[]{
                            e, "delete"
                        });
                     //System.out.println(e);
                }
                break;//only the first 
            }
        }
       jList1.setModel(jList1list);
        jList2.setModel(jList2list);
        jList2.setSelectedIndex(0);
        //change the header on the tableHeader to a different font and center the text
        jTable2.setModel(model);
        jTable2.setRowHeight(24);
        jTable2.getTableHeader().setFont( new Font( "Arial" , Font.BOLD, 15 ));
        ((DefaultTableCellRenderer)jTable2.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //center the text of all Objects in the cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable2.setDefaultRenderer(Object.class, centerRenderer);
        //actions for when button is clicked
        
        
        registerClick();
        

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jSplitPane2.setDividerSize(1);

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setText("Members of selected groups");

        jTable2.setName("jTable1"); // NOI18N
        jScrollPane4.setViewportView(jTable2);

        jButton2.setText("Add member");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(378, 378, 378)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane2.setRightComponent(jPanel3);

        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList2ValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jList2);

        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel1.setText("Other groups");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel4.setText("Groups I Own");

        jButton3.setText("Leave Group");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Delete Group");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(1, 1, 1)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList2ValueChanged
        // TODO add your handling code here:
        //everytime a NEW item is clicked load the stored members of that list into the jtable
        
        String group = (String) jList2.getSelectedValue();
        model.setRowCount(0);
        //model.setColumnCount(1);
        //model.addColumn("Manage group");
        if(listOfGroups[1] != null){
            ArrayList d = ((LinkedHashMap<String, ArrayList>)listOfGroups[1]).get(group);
            if(d == null) return;
            for(Object e : d ){
                model.insertRow(model.getRowCount(), 
                           new Object[]{
                               e, "delete"
                        });
            }
            jTable2.setModel(model);
            registerClick();
        }
    }//GEN-LAST:event_jList2ValueChanged

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        //jList2.setSelectedIndex(-1);
        String group = (String) jList1.getSelectedValue();
        model1.setRowCount(0);
        if(listOfGroups[3] != null){
            ArrayList d = ((LinkedHashMap<String, ArrayList>)listOfGroups[3]).get(group);
            if(d == null) return;
            //model.setColumnCount(1);
            
            for(Object e : d ){
                model1.insertRow(model1.getRowCount(), 
                           new Object[]{
                               e
                        });
            }
            jTable2.setModel(model1);
            
        }
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        String group = (String) jList2.getSelectedValue();
        if( group == null){
               //pressed cancel
               return;
           }
        if(group.trim().isEmpty()){
            JOptionPane.showMessageDialog(null,
                            "Please enter a name for the group",
                            "Create Group",
                            JOptionPane.ERROR_MESSAGE);
               return;
        }
        String memberName = (String)JOptionPane.showInputDialog(null,
                            "Adding member to "+ group +" group",
                            "Create Group",
                            JOptionPane.INFORMATION_MESSAGE);
        
        
        System.out.println("Creating a group name " + memberName);
        
            if(memberName == null){
               //pressed cancel
               return;
           }
           if(memberName.trim().isEmpty()){
               JOptionPane.showMessageDialog(null,
                            "Please enter a name for the group",
                            "Create Group",
                            JOptionPane.ERROR_MESSAGE);
               return;
           }
           
           controller.addMemberToGroup(new ChatMessage(ChatMessage.ADDMEMBERTOGROUP, new String[]{memberName,group }));
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jList2.isSelectionEmpty()){
            return;
        }
        String group = jList2.getSelectedValue().toString();
        int response= JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to delete "+group +" group",
                            "Please take note",
                            JOptionPane.YES_NO_OPTION);
         if (response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION) {
    
          } else {
             //work
             controller.deleteMyGroup(new ChatMessage(ChatMessage.DELETEGROUP, new String[]{controller.getClientUsername(),group }));
         }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(jList1.isSelectionEmpty()){
            return;
        }
        
        String group = jList1.getSelectedValue().toString();
        int response= JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to leave "+group +" group",
                            "Please take note",
                            JOptionPane.YES_NO_OPTION);
         if (response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION) {
             //do nothing
          } else {
             controller.leaveGroup(new ChatMessage(ChatMessage.LEAVEGROUP,new String[]{controller.getClientUsername(),group}));
             
         }
       
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

    private void registerClick() {
        new ButtonColumn(jTable2, new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(jList2.isSelectionEmpty()){
                    return;//nothing is selected some how
                }
                int row = Integer.valueOf( e.getActionCommand());
                 String updateUser = model.getValueAt(row, 0).toString();
                 
                int response= JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to delete "+updateUser +" member",
                            "Please take note",
                            JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION) {

                     } else {
                    
                        controller.deleteMemberFromGroup(
                                new ChatMessage(ChatMessage.DELETEMEMBERFROMGROUP,
                                        new String[]{ 
                                            updateUser, jList2.getSelectedValue().toString()
                                        }));

                         //remove from list of friend request
                         model.removeRow(row);
               }
            }
         }, 1);
    }
}
