import java.io.*;

public class User implements Serializable
{
	public static boolean priorUserExists(){
		return new File("ShiftReportUser.ser").exists();
	}
	public static boolean writeUser(User user){
		try
		{
			FileOutputStream fos = new FileOutputStream("ShiftReportUser.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(user);
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static User retrieveUser(){
		try
		{
			FileInputStream fis = new FileInputStream("ShiftReportUser.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			return (User)ois.readObject();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
}
