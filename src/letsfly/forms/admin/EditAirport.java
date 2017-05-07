package letsfly.forms.admin;

import javax.swing.*;

import letsfly.forms.admin.airport.AddAirport;
import letsfly.forms.admin.airport.DeleteAirport;
import letsfly.forms.admin.airport.Modify;

import java.awt.event.*;
public class EditAirport extends JFrame {
    private JButton ModifyAirportButton;
    private JButton addAirportButton;
    private JButton deleteAirportButton;
    private JLabel title;

    public EditAirport() {
        initComponents();
    }

   
    private void initComponents() {

        title = new JLabel();
        addAirportButton = new JButton();
        deleteAirportButton = new JButton();
        ModifyAirportButton = new JButton();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                dispose();
                new Admin().setVisible(true);
            }
        });
        title.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        title.setText("Edit Airport");

        addAirportButton.setText("Add Airport");
        addAirportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addAirportButtonActionPerformed(evt);
            }
        });

        deleteAirportButton.setText("Delete Airport");
        deleteAirportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deleteAirportButtonActionPerformed(evt);
            }
        });

        ModifyAirportButton.setText("Modify Airport");
        ModifyAirportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ModifyAirportButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(ModifyAirportButton, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteAirportButton, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                    .addComponent(addAirportButton, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(172, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(title, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(addAirportButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(ModifyAirportButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(deleteAirportButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }

    private void deleteAirportButtonActionPerformed(ActionEvent evt) {                                                    
        dispose();
        new DeleteAirport().setVisible(true);
    }                                                   

    private void ModifyAirportButtonActionPerformed(ActionEvent evt) {                                                    
        dispose();
        new Modify().setVisible(true);
    }                                                   

    private void addAirportButtonActionPerformed(ActionEvent evt) {                                                 
        dispose();
        new AddAirport().setVisible(true);
    }                                                

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new editAirport().setVisible(true);
//            }
//        });
//    }

}
