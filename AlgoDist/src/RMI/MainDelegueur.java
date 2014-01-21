import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;


public class MainDelegueur {

	/**
	 * @param args
	 */
	public static void init() {
		
	
		
			
		
		//Delegueur les taches
		
		/*Test de déléguage, à supprimer*/
		Delegueur d;
		
		try {
			d = new Delegueur();
			d.delegue(4);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
