/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shiftreportjava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Snowy
 */
public class PreviewListener implements ActionListener {
    private User user;
    private DefaultTableModel labs,footprints,misc = null;
    private JTextField email = null;
    public PreviewListener(User u) {
        user = u;
    }
    public ActionListener putInfo(JTextField _email,DefaultTableModel labTable,DefaultTableModel footTable,DefaultTableModel miscTable){
        email = _email;
        labs = labTable;
        footprints = footTable;
        misc = miscTable;
        return this;
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
    @Override
    public void actionPerformed(ActionEvent e) {
        
        ShiftReport sr = new ShiftReport(email.getText().split("@")[0],parseLabs());
        setFootprints(sr);
        setMisc(sr);
        Previewer pv = new Previewer(sr.buildEmailWOHead());
        
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
