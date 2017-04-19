package letsfly.forms.admin;


import java.awt.event.*;
import javax.swing.*;

import letsfly.forms.ViewFlight;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Admin extends JFrame {
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JLabel jLabel1;
    
    public Admin() {
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new JLabel("Admin");
        jButton2 = new JButton("View Flights");
        jButton3 = new JButton("Delete Flight");
        jButton4 = new JButton("Add Flight");


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
   
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        
        JButton btnAddAirline = new JButton("Add Airline");
        
        JButton btnAddAirport = new JButton("Add Airport");
        
        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(250)
        					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(192)
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnAddAirline, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnAddAirport, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))))
        			.addContainerGap(194, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(20)
        			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        			.addGap(11)
        			.addComponent(btnAddAirline, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnAddAirport, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(26, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);
        setResizable(false);
        pack();
    }                                          

                                       
    private void jButton2ActionPerformed(ActionEvent evt) {
        this.setVisible(false);
        ViewFlight v = new ViewFlight("admin");
        v.setVisible(true);
    }
    
    private void jButton3ActionPerformed(ActionEvent evt) {                                         
        this.setVisible(false);
        DeleteFlight df = new DeleteFlight();
        df.setVisible(true);
    }                                        
    private void jButton4ActionPerformed(ActionEvent evt) { 
        this.setVisible(false);
        AddFlight af = new AddFlight();
        af.setVisible(true);
    }                                        
}
