


import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainTestClient {

	static int port = 1099;
	/**
	 * @param args
	 */
	public static void main(String [ ] argv) {
		
		System.setProperty("java.security.policy","./java.policy");
		
		  if (System.getSecurityManager() == null) {
			    System.setSecurityManager(new RMISecurityManager());
			  }
		  	  Transmission serveur = null;
			  try 
			  {
				  serveur = (Transmission)Naming.lookup("//" + InetAddress.getLocalHost().getHostAddress()+":"+ port +"/Transmission");
				  serveur.transmettre_jeton();
				  
			  } 
			  catch (Exception e) 
			  {
			    System.out.print(e.getMessage());
			    System.out.println("La remote a pas fonctionné");
			  }

	}

}
