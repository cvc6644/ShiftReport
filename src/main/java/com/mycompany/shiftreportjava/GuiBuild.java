/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shiftreportjava;

import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author snowyowl
 */
public class GuiBuild extends JFrame{
    private User u;
    private boolean priorUser;
    public GuiBuild(User _u){
        u=_u;
        priorUser = true;
    }
    public GuiBuild(){
        u= new User();
        priorUser = false;
    }
    public void build(){
        this.setTitle("Building Shift Report");
        
        
        
        this.setLayout(new GridLayout(0,1));
        this.add(email());
        this.add(labs());
        this.add(tickets());
        this.add(misc());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    private JPanel email(){
        JPanel jpUsername= new JPanel();
        jpUsername.add(new JLabel("Username"));
        JTextField email = new JTextField(20);
        if(priorUser){
            email.setText(u.getAddress());
        }
        jpUsername.add(email);
        return jpUsername;
    }
    private JPanel labs(){
        JPanel jpLabs= new JPanel(new GridLayout(3,1));
        jpLabs.add(new JPanel().add(new JLabel("Lab Rounds:")));
        //add jTable
        JTable jtLabs= new JTable();
        DefaultTableModel dtmLabs = new DefaultTableModel(1,2);
        dtmLabs.setColumnIdentifiers(new String[]{"Labs","Status"});
        jtLabs.setModel(dtmLabs);
        if(priorUser){
            
            
            for(String i : u.getLabs().keySet()){
                dtmLabs.addRow(new String[]{i,u.getLabs().get(i)});
            }
            
            
        }
        //dtmLabs.
        jpLabs.add(new JScrollPane(jtLabs));
        //add button to increase table rows
        JButton jbAddLab = new JButton("Add Lab");
        //add listener
        jbAddLab.addActionListener(new RowButtonListener(dtmLabs));
        jpLabs.add(jbAddLab);
        
        return jpLabs;
    }

    private JPanel tickets() {
        JPanel jpTickets= new JPanel(new GridLayout(3,1));
        jpTickets.add(new JPanel().add(new JLabel("Footprints Tickets:")));
        //add jTable
        JTable jtTickets= new JTable();
        DefaultTableModel dtmTickets = new DefaultTableModel(1,3);
        dtmTickets.setColumnIdentifiers(new String[]{"Ticket #","Status","Description"});
        jtTickets.setModel(dtmTickets);
        
        //dtmLabs.
        jpTickets.add(new JScrollPane(jtTickets));
        //add button to increase table rows
        JButton jbAddTicket = new JButton("Add Ticket");
        //add listener
        jbAddTicket.addActionListener(new RowButtonListener(dtmTickets));
        jpTickets.add(jbAddTicket);
        
        return jpTickets;
    }

    private JPanel misc() {
        JPanel jpMisc= new JPanel(new GridLayout(3,1));
        jpMisc.add(new JPanel().add(new JLabel("Misc:")));
        //add jTable
        JTable jtMisc= new JTable();
        DefaultTableModel dtmMisc = new DefaultTableModel(1,2);
        dtmMisc.setColumnIdentifiers(new String[]{"Event/Task","Description"});
        jtMisc.setModel(dtmMisc);
        
        //dtmLabs.
        jpMisc.add(new JScrollPane(jtMisc));
        //add button to increase table rows
        JButton jbAddMisc = new JButton("Add Misc");
        //add listener
        jbAddMisc.addActionListener(new RowButtonListener(dtmMisc));
        jpMisc.add(jbAddMisc);
        
        return jpMisc;
    }
}
