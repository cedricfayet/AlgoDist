

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class GestionnaireTransmission extends UnicastRemoteObject implements Transmission
{

	private static final long serialVersionUID = 1L;
	private Controleur controleur;
	

	GestionnaireTransmission(Controleur controleur) throws RemoteException
	{
		super();
		this.controleur = controleur;
		
		//Gestion des propriété de la JVM et du sécurity manager 
		System.setProperty("java.rmi.server.codebase", "file:./");
		System.setProperty("java.security.policy", "./java.policy");

		if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
		}
		
		try 
		{
			Naming.rebind(controleur.getId_perso().toString(), this);
			System.out.println("Enregistrement du gestionnaire de transmission");
			System.out.println(controleur.getId_perso().toString());
		} 
		catch (java.net.MalformedURLException e) {
			System.out.println("Mauvais nom de serveur");
		} 
		catch (RemoteException e) {
			System.out.println("Problème remote");
			System.out.println(e.getMessage());
		}
		
	}
	
	public void envoyer(Jeton jeton)
	{
		System.out.println("Jeton recut");
		controleur.recevoir(jeton);		
	}
	
	public void envoyer(Requete requete)
	{
		controleur.recevoir(requete);
	}
	
	public void transmettre(Jeton jeton,IdentifiantNoeud id_pere) {
		
		try {

			Transmission pere_gestionnaire_de_transmission;
			// Récupération du père;
			//TODO test
			System.out.println(id_pere.toString());
			pere_gestionnaire_de_transmission = (Transmission) Naming.lookup(id_pere.toString());
			// Demande de transmission au père
			pere_gestionnaire_de_transmission.envoyer(jeton);

		} catch (Exception e) {
			//System.out.print(e.getMessage());
			System.out.println("La remote a pas fonctionné");
		}
	}
	
	public void transmettre(Requete requete,IdentifiantNoeud id_pere) {
		
		try {

			Transmission gestionnaire_de_transmission;
			// Récupération du père;
			//TODO test
			System.out.println(id_pere.toString());
			gestionnaire_de_transmission = (Transmission) Naming.lookup(id_pere.toString());
			// Demande de transmission au père
			gestionnaire_de_transmission.envoyer(requete);

		} catch (NotBoundException e) {
			//System.out.print(e.getMessage());
			System.out.println("La remote n'est pas enregistré - not bound");
		}
		catch(java.net.MalformedURLException e)
		{
			System.out.println("La remote n'est pas enregistré - mal formed url");
		}
		catch(RemoteException e)
		{
			System.out.println("La remote n'est pas enregistré - remote");
		}
	}
	

	
	
}
