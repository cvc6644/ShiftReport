/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shiftreportjava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 *
 * @author snowyowl
 */
public class TimerListener implements ActionListener {
    private JDialog dialog;
    private boolean dd=false;
    public TimerListener(JDialog jd){
        dialog = jd;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        dd = true;
        dialog.dispose();
    }
    public boolean afkUser(){
        return dd;
    }
    
}
