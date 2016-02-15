/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shiftreportjava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Snowy
 */
public class SenderListener implements ActionListener{
    private boolean priorUser;
    private User user;
    private DefaultTableModel labs,footprints,misc = null;
    private JTextField email = null;
    public SenderListener(){
        priorUser=false;
    }
    public SenderListener(User u) {
        user = u;
        priorUser = true;
    }
    public void putInfo(JTextField _email,DefaultTableModel labTable,DefaultTableModel footTable,DefaultTableModel miscTable){
        email = _email;
        labs = labTable;
        footprints = footTable;
        misc = miscTable;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ShiftReport sr;
        if(priorUser){
            sr = new ShiftReport(user.getUsername(),parseLabs());
        }else{
            sr = new ShiftReport(email.getText().split("@")[0],parseLabs());
            User.writeUser(createNewUser());
        }
        setFootprints(sr);
        setMisc(sr);
        sr.buildAndSend();
        System.exit(0);
    }
    public User createNewUser(){
        user = new User(email.getText());
        HashMap<String,String> hm = parseLabs();
        for(String s : hm.keySet()){
            hm.put(s, "All Good");
        }
        user.setLabs(hm);
        return user;
    }
    public HashMap parseLabs(){
        HashMap<String,String> labSet =new HashMap<>();
        for(int i =0;i<labs.getRowCount();i++){
            if(labs.getValueAt(i, 0)!=null && labs.getValueAt(i,1)!=null){
                labSet.put((String)labs.getValueAt(i,0), (String)labs.getValueAt(i,1));
            }
        }
        return labSet;
    }
    private void setFootprints(ShiftReport sr) {
        
        for(int i = 0;i<footprints.getRowCount();i++){
            if(footprints.getValueAt(i, 0)!=null && footprints.getValueAt(i, 1)!=null && footprints.getValueAt(i, 2)!=null){
                sr.addFootprintsTicket((String) footprints.getValueAt(i, 0),(String)footprints.getValueAt(i, 1),(String)footprints.getValueAt(i, 2));
            }
       }
        
    }

    private void setMisc(ShiftReport sr) {
        for(int i = 0;i<misc.getRowCount();i++){
            if(misc.getValueAt(i, 0)!=null && misc.getValueAt(i, 1)!=null){
                sr.addMisc((String) misc.getValueAt(i, 0),(String)misc.getValueAt(i, 1));
            } 
        }
    }
    
}
