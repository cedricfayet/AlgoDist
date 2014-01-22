
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

public class GestionnaireTransmission extends UnicastRemoteObject implements
		Transmission {
	
	//pour l'instant le port utilisé est le 1099 on peut le mettre dans Id jeton
	private static int port = 1099;
	
	//Semaphore pour communiquer avec le processus metier
	Semaphore s;
	
	//Identifiant du père
	IdentifiantNoeud id_pere;
	IdentifiantNoeud id_perso;
	
	
	public GestionnaireTransmission(IdentifiantNoeud identifiant_pere, Semaphore s) throws RemoteException 
	{
		super();
		
		
		//Gestion des propriété de la JVM et du sécurity manager 
		System.setProperty("java.rmi.server.codebase", "file:./");
		System.setProperty("java.security.policy", "./java.policy");

		if (System.getSecurityManager() == null) {
		System.setSecurityManager(new RMISecurityManager());
		}

	}

	
	public void transmettre_jeton(Jeton jeton) throws RemoteException {
		
		System.out.println("J'ai reçu une demande de transmission de jeton");
		//C'est maintenant ce processus le possesseur du jeton, donc pas de père.
		id_pere = null;
		//Je récupère l'identifiant de celui à qui je dois envoyé le jeton
		IdentifiantNoeud demandeur = jeton.voirRequete().getDemandeur();
		
		//Je regarde si le demandeur correspond à mon nom.
		//TO DO: Traiter les cas ou le jeton est vide ou des trucs du style
		if(demandeur == id_perso)
		{
			//Si je suis le demandeur
			//=> J'autorise mon processus métier à aller dans la sc 
			//=> present_dans_sc = true; et demande_dans_sc = false;
		}
		else if (demandeur != id_perso)
		{
			//Reforme la liste des requêtes du jeton
			IdentifiantNoeud id_pere = jeton.rootage(id_perso);
			
			/*Envoi du jeton au fils*/
			try {
				
				Transmission gestionnaire_de_transmission;
				//Récupération du père 
				//TO DO: Enlever toute la partie InetAdress qui doit être en identifiant
				gestionnaire_de_transmission = (Transmission) Naming.lookup("//"
						+ InetAddress.getLocalHost().getHostAddress() + ":" + port //Enlever toute cette ligne
						+ id_pere.toString());
				gestionnaire_de_transmission.transmettre_jeton(jeton);

			} catch (Exception e) {
				System.out.print(e.getMessage());
				System.out.println("La remote a pas fonctionné");
			}
			
		}

	}

	public void transmettre_requête() throws RemoteException {
		System.out.println("J'ai reçu une demande d'envoi de requête, je la transmet à mon père.");
		// TO DO: Trantement et envoi de la requête, la transmettre à son père
		// si on est pas le pocesseur du jeton.

	}

}
