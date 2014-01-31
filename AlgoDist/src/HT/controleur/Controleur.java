package HT.controleur;

import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;


public class Controleur
{
	private Semaphore s;
	private GestionnaireTransmission gestionnaire_de_transmission;
	private IdentifiantNoeud identifiant;
	private IdentifiantNoeud identifiant_pere;
	private Jeton jeton;
	//TO DO: Identifiant perso, comment on le définit ? 
	//TO DO: A enlever !
	private IdentifiantNoeud id_perso;
	
	Controleur(IdentifiantNoeud id_pere) {
		//TO DO: Definir identifiant perso
		try {
			gestionnaire_de_transmission = new GestionnaireTransmission(this, id_pere);
			if(identifiant_pere.estRacine())
				jeton=new Jeton();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TO DO: Voir comment on définit id_perso
		//TO DO: A modifier pour lancer plusieur processus
		id_perso = new IdentifiantNoeud("127.0.0.1","1O99","A");
	}

	public Controleur(Semaphore s)
	{
		this.s=s;
	}
	
	public void demander_SectionCritique()
	{
		System.out.println("Ah j'attend avant de donner acc�s � la SC :D");
		s.release();
	}	
	
	public void sortir_SectionCritique()
	{
		
	}
	
	void recevoir(Jeton jeton)
	{
		System.out.println("J'ai reçu une demande de transmission de jeton");
		//C'est maintenant ce processus le possesseur du jeton, donc pas de père.
		gestionnaire_de_transmission.set_id_pere(null);
		//Je récupère l'identifiant de celui à qui je dois envoyé le jeton
		IdentifiantNoeud demandeur = jeton.file_requetes.voirRequete().getDemandeur();
		
		//Je regarde si le demandeur correspond à mon nom.
		//TO DO: Traiter les cas ou le jeton est vide ou des trucs du style
		if(demandeur.toString() == id_perso.toString())
		{
			//Si je suis le demandeur
			//=> J'autorise mon processus métier à aller dans la sc 
			//=> present_dans_sc = true; et demande_dans_sc = false;
		}
		else if (demandeur.toString() != id_perso.toString())
		{
			//Reforme la liste des requêtes du jeton
			//TO DO: Il faut mettre id_perso dans gestionnaire_de_transmission
			//TO DO: Faire une fonction rootage du gestionnaire de transmission
			gestionnaire_de_transmission.set_id_pere(jeton.file_requetes.rootage(id_perso));
			/*Envoi du jeton au fils*/
			gestionnaire_de_transmission.transmettre(jeton);
			
			
		}
	}
	
	void recevoir(Requete requete)
	{
		
	}
	
	public Object get_objet_partage(String id)
	{
		return jeton.objets_partages.get(id);
	}
}
