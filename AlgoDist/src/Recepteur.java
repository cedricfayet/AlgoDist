import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;

public class Recepteur {

	static int port = 1099;

	public  void init() {
		// Mise en place du security manager de la JVM
		// On peut aussi directement configurer la JVM, cmd
		// -Djava.security.manager

		System.setProperty("java.rmi.server.codebase", "file:./");
		System.setProperty("java.security.policy", "./java.policy");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		try 
		{
			Transmission transmission = new GestionnaireTransmission(new IdentifiantNoeud(), new Semaphore(0));
			Naming.rebind("//" + InetAddress.getLocalHost().getHostAddress()+ ":" + port + "/Transmission", transmission);
			System.out.println("Enregistrement du gestionnaire de transmission");
		} 
		catch (java.net.UnknownHostException e) {
			System.out.println("Mauvais nom de serveur");
		} 
		catch (java.net.MalformedURLException e) {
			System.out.println("Mauvais nom de serveur");
		} 
		catch (RemoteException e) {
			System.out.println("Problème remote");
			System.out.println(e.getMessage());
		}

	}
}
