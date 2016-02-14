/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shiftreportjava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author snowyowl
 */
public class RowButtonListener implements ActionListener  {
    private DefaultTableModel dtm;
    public RowButtonListener(DefaultTableModel _dtm){
        dtm = _dtm;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        dtm.addRow(new String[dtm.getColumnCount()]);
    }
    
}
