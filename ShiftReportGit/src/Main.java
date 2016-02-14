import User;
public class Main
{
	public static void main(String[] args){
		if(User.priorUserExists()){
			User user = User.retrieveUser();
			//JOptionPane jop = new JOptionPane("Add Additional Information",JOptionPane.INFORMATION_MESSAGE);
			//JDialog dialog = jop.createDialog(parent,"AFK Check");
		}else{
			
		}
	}
}
