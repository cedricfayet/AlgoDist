import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class GestionnaireTransmission extends UnicastRemoteObject implements Transmission
{
	private Controleur controleur;
	private IdentifiantNoeud id_pere;
	
	IdentifiantNoeud getId_pere() {
		return id_pere;
	}

	void setId_pere(IdentifiantNoeud id_pere) {
		this.id_pere = id_pere;
	}

	GestionnaireTransmission(Controleur controleur,IdentifiantNoeud id_pere) throws RemoteException
	{
		super();
		this.controleur = controleur;
		this.id_pere = id_pere;
		
		//Gestion des propriété de la JVM et du sécurity manager 
		System.setProperty("java.rmi.server.codebase", "file:./");
		System.setProperty("java.security.policy", "./java.policy");

		if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
		}
		
	}
	
	public void envoyer(Jeton jeton)
	{
		controleur.recevoir(jeton);		
	}
	
	public void envoyer(Requete requete)
	{
		controleur.recevoir(requete);
	}
	
	public void transmettre(Jeton jeton) {
		
		try {

			Transmission gestionnaire_de_transmission;
			// Récupération du père;
			gestionnaire_de_transmission = (Transmission) Naming.lookup(id_pere.toString());
			// Demande de transmission au père
			gestionnaire_de_transmission.envoyer(jeton);

		} catch (Exception e) {
			System.out.print(e.getMessage());
			System.out.println("La remote a pas fonctionné");
		}
	}
	
	public void transmettre(Requete requete) {
		
		try {

			Transmission gestionnaire_de_transmission;
			// Récupération du père;
			gestionnaire_de_transmission = (Transmission) Naming.lookup(id_pere.toString());
			// Demande de transmission au père
			gestionnaire_de_transmission.envoyer(requete);

		} catch (Exception e) {
			System.out.print(e.getMessage());
			System.out.println("La remote a pas fonctionné");
		}
	}
	
	
	public void set_id_pere(IdentifiantNoeud id_pere)
	{
		
	}
	
	
}
