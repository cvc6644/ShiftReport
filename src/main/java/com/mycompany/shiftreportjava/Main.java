package com.mycompany.shiftreportjava;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Main
{
	public static void main(String[] args){
            
                    
		if(User.priorUserExists()){
                    User user = User.retrieveUser();
                    JOptionPane pane = new JOptionPane("Are you there?", JOptionPane.QUESTION_MESSAGE);
                    JDialog dialog = pane.createDialog(null, "AFK Check");
                    
                    dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                    TimerListener tl = new TimerListener(dialog);
                    Timer t = new Timer(1,tl);
                    //t.setInitialDelay(20000);
                    t.setInitialDelay(3600000);
                    t.setRepeats(false);
                    t.start();
                    dialog.setVisible(true);
                    
                    if(tl.afkUser()){
                        ShiftReport sr = new ShiftReport(user.getUsername(),user.getLabs());
                        sr.buildAndSend();
                        //System.out.println("user afk "+ t.isRunning());
                        System.exit(0);
                    }else if(!tl.afkUser()){
                        t.stop();
                        GuiBuild guiBuild = new GuiBuild(user);
                        guiBuild.build();
                        //System.out.println("user not afk "+ t.isRunning());
                        //System.exit(0);
                    }
		}else{
                    GuiBuild gb = new GuiBuild();
                    gb.build();
                    //System.out.println("no user exist");
		}
	}
        
}