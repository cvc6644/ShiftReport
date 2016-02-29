package com.mycompany.shiftreportjava;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
public class mail
{

	public static boolean sendMail(String email, String _from){
		
                Calendar cal = Calendar.getInstance();
                String subject = _from+" Shift Report "+((cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.YEAR)));
		String from = _from+"@rit.edu";
		String to ="its_lab_admins@rit.edu";
		//String to ="cvc6644@rit.edu";
		String host ="mymail.rit.edu";
                if(!isDomainComputer()){
                    Properties properties = System.getProperties();
                //properties.getProperty(to)
                    properties.setProperty("mail.smtp.host",host);
                    Session session = Session.getDefaultInstance(properties);
                    try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject(subject);
			message.setContent(email,"text/html");
			Transport.send(message);
			return true;
                    }catch(MessagingException e){
			e.printStackTrace();
                    	return false;
                    }
                }else{
                    try {
                        //File f = new File("ehe.html");
                        // f.getAbsolutePath();
                        mailto(Arrays.asList(to),subject,email);
                        return true;
                        /*
                        Process p;
                        try {
                        p = new ProcessBuilder("emailShiftReport.exe",_from,writeEmailToFile(email).getAbsolutePath()).start();
                        return true;
                        } catch (IOException ex) {
                        Logger.getLogger(mail.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                        }*/
                    } catch (IOException | URISyntaxException ex) {
                        Logger.getLogger(mail.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                    
                }

	}
        public static void mailto(List<String> recipients, String subject,
            String body) throws IOException, URISyntaxException {
                String uriStr = String.format("mailto:%s?subject=%s&body=%s",
                join(",", recipients), // use semicolon ";" for Outlook!
                urlEncode(subject),
                urlEncode(body));
            Desktop.getDesktop().browse(new URI(uriStr));
        }

    private static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String join(String sep, Iterable<?> objs) {
        StringBuilder sb = new StringBuilder();
        for(Object obj : objs) {
            if (sb.length() > 0) sb.append(sep);
            sb.append(obj);
        }
        return sb.toString();
    }
    private static boolean isDomainComputer() {
            try {
                //Process pb = new ProcessBuilder("hostname").start();
                
                return InetAddress.getByName(InetAddress.getLocalHost().getHostName()+".main.ad.rit.edu").isReachable(500);
            } catch (IOException ex) {
                return false;
            }
    }

    private static File writeEmailToFile(String email) {
            try {
                PrintWriter writer = new PrintWriter("emailShiftReport.html");
                writer.print(email);
                writer.close();
                return new File("emailShiftReport.html");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(mail.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            
        
    }
}