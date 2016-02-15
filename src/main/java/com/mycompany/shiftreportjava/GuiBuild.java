/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shiftreportjava;

import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author snowyowl
 */
public class GuiBuild extends JFrame{
    private User u;
    private boolean priorUser;
    private JXTable jtLabs,jtTickets,jtMisc;
    private DefaultTableModel dtmLabs, dtmTickets,dtmMisc;
    private JTextField email;
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
        
        
        
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
        this.add(email());
        this.add(labs());
        this.add(tickets());
        this.add(misc());
        this.add(sender());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    private JPanel email(){
        JPanel jpUsername= new JPanel();
        jpUsername.add(new JLabel("Email:"));
        email = new JTextField(20);
        if(priorUser){
            email.setText(u.getAddress());
        }
        jpUsername.add(email);
        return jpUsername;
    }
    private JPanel labs(){
        JPanel jpLabs= new JPanel();
        jpLabs.setLayout(new BoxLayout(jpLabs,BoxLayout.Y_AXIS));
        jpLabs.add(new JPanel().add(new JLabel("Lab Rounds:")).getParent());
        //add jTable
        jtLabs= new JXTable();
        
        dtmLabs = new DefaultTableModel(1,1);
        dtmLabs.setColumnIdentifiers(new String[]{"Labs","Status"});
        jtLabs.setModel(dtmLabs);
        
        if(priorUser){
            
            
            for(String i : u.getLabs().keySet()){
                dtmLabs.addRow(new String[]{i,u.getLabs().get(i)});
            }
            
            
        }
        //dtmLabs.
        jtLabs.setVisibleRowCount(4);
        jtLabs.setOpaque(false);
        jpLabs.add(new JScrollPane(jtLabs));
        //add button to increase table rows
        JButton jbAddLab = new JButton("Add Lab");
        //add listener
        jbAddLab.addActionListener(new RowButtonListener(dtmLabs));
        jpLabs.add(new JPanel().add(jbAddLab).getParent());
        
        return jpLabs;
    }

    private JPanel tickets() {
        JPanel jpTickets= new JPanel();
        jpTickets.setLayout(new BoxLayout(jpTickets,BoxLayout.Y_AXIS));
        jpTickets.add(new JPanel().add(new JLabel("Footprints Tickets:")).getParent());
        //add jTable
        jtTickets= new JXTable();
        dtmTickets = new DefaultTableModel(1,3);
        dtmTickets.setColumnIdentifiers(new String[]{"Ticket #","Status","Description"});
        jtTickets.setModel(dtmTickets);
        jtTickets.setVisibleRowCount(4);
        jtTickets.setOpaque(false);
        //dtmLabs.
        jpTickets.add(new JScrollPane(jtTickets));
        //add button to increase table rows
        JButton jbAddTicket = new JButton("Add Ticket");
        //add listener
        jbAddTicket.addActionListener(new RowButtonListener(dtmTickets));
        jpTickets.add(new JPanel().add(jbAddTicket).getParent());
        
        return jpTickets;
    }

    private JPanel misc() {
        JPanel jpMisc= new JPanel();
        jpMisc.setLayout(new BoxLayout(jpMisc,BoxLayout.Y_AXIS));
        jpMisc.add(new JPanel().add(new JLabel("Misc:")).getParent());
        //add jTable
        jtMisc= new JXTable();
        dtmMisc = new DefaultTableModel(1,2);
        dtmMisc.setColumnIdentifiers(new String[]{"Event/Task","Description"});
        jtMisc.setModel(dtmMisc);
        jtMisc.setVisibleRowCount(4);
        jtMisc.setOpaque(false);
        //dtmLabs.
        jpMisc.add(new JScrollPane(jtMisc));
        //add button to increase table rows
        JButton jbAddMisc = new JButton("Add Misc");
        //add listener
        jbAddMisc.addActionListener(new RowButtonListener(dtmMisc));
        jpMisc.add(new JPanel().add(jbAddMisc).getParent());
        
        return jpMisc;
    }
    private JPanel sender(){
        JPanel jpSend = new JPanel();
        JButton jbPreview = new JButton("Preview");
        jbPreview.addActionListener(new PreviewListener(u).putInfo(email, dtmLabs,dtmTickets,dtmMisc));
        JButton jbSend;
        SenderListener sendListen;
        if(priorUser){
            jbSend = new JButton("Send Email");
            sendListen = new SenderListener(u);
        }else{
            jbSend = new JButton("Save User and Send Email");
            sendListen = new SenderListener();
        }
        sendListen.putInfo(email, dtmLabs,dtmTickets,dtmMisc);
        jbSend.addActionListener(sendListen);
        jpSend.add(jbPreview);
        jpSend.add(jbSend);
        jbSend.setEnabled(false);
        jbPreview.setEnabled(false);
        return jpSend;
    }
}
