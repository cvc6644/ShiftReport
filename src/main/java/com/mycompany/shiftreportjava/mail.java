package com.mycompany.shiftreportjava;

import java.time.LocalDate;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
public class mail
{

	public static boolean sendMail(String email, String _from){
		
                Calendar cal = Calendar.getInstance();
                
		String from = _from;
		//String to ="its_lab_admins@rit.edu";
		String to ="cvc6644@rit.edu";
		String host ="mymail.rit.edu";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host",host);
		Session session = Session.getDefaultInstance(properties);
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject(from.split("@")[0]+" "+((cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.YEAR)))+" Shift Report");
			message.setContent(email,"text/html");
			Transport.send(message);
			return true;
		}catch(MessagingException e){
			e.printStackTrace();
			return false;
		}


	}
}