
package com.connect.view;

import com.connect.model.ChatMessage;
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
public class AllContacts extends javax.swing.JInternalFrame {

    ClientController controller;
    /**
     * Creates new form AllContacts
     * @param c
     * @param listOfGroups
     */
    public AllContacts(ClientController c, String[] listOfGroups) {
        controller = c;
        initComponents();
        Object columnHeaders[] = {"Friend name","Manage"};
            //create table with data and headers
            final DefaultTableModel model = new DefaultTableModel(columnHeaders,0);
            
            
            for(Object d : listOfGroups){
                model.insertRow(model.getRowCount(), new Object[]{d, "delete"});
            }
            //change the header on the tableHeader to a different font and center the text
            jTable1.setModel(model);
            jTable1.getTableHeader().setFont( new Font( "Arial" , Font.BOLD, 15 ));
            ((DefaultTableCellRenderer)jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
            //center the text of all Objects in the cells
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            jTable1.setDefaultRenderer(Object.class, centerRenderer);
            jTable1.setRowHeight(24);
        new ButtonColumn(jTable1, new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.valueOf( e.getActionCommand());
                //System.out.println(row );
                //System.out.println( model.getValueAt(row, 0));
                //tell server to update relationship
                String updateUser = model.getValueAt(row, 0).toString();
                controller.deleteFriend(new ChatMessage(ChatMessage.DELETEFRIEND, new String[]{controller.getClientUsername(),updateUser}));
                
                //remove from list of friend request
                model.removeRow(row);
            }
        }, 1);
                       
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Friend Name", "Delete", "View Messages"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
