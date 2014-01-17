package guillaume.algoDist.RMI;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;

public class MainTestServer {

	public static void init() {
		//Mise en place du security manager de la JVM
		//On peut aussi directement configurer la JVM, cmd -Djava.security.manager
		try {
			
			//Lancement du registre de nom RMI 
			//A vérifier: Attention ne doit être lancé qu'une seul fois! 
			LocateRegistry.createRegistry(1099);
			
		    if (System.getSecurityManager() == null) {
		      System.setSecurityManager(new RMISecurityManager());
		    }
		    
		    //Création de l'objet qui sera manipulé.
		    Gestionnaire_de_transmission gestionnaire_de_transmission = new Gestionnaire_de_transmission();
		    
		    //Enregistrement dans le registre de nom.
		    // ?? Le registre de nom est un fichier de la JVM ?
		    String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/TestRMI";
		    // ?? C'est quoi testRMI à la fin de l'adresse ?
		    System.out.println("Enregistrement de l'objet avec l'url : " + url);
		    Naming.rebind(url, gestionnaire_de_transmission);
		    
		  } catch (Exception e) {
		     e.getStackTrace();
		  }
		System.out.println("Le gestionnaire de transmission a été lancé avec succès");
		}
}
