import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;

public class Controleur
{
	//Semaphore de communication avec le thread metier.
	private Semaphore s;
	
	//Communication avec les autres processus.
	private IdentifiantNoeud id_perso;
	private IdentifiantNoeud identifiant_pere;
	private GestionnaireTransmission gestionnaire_de_transmission;
	private Jeton jeton;
	
	//Variable de gestion des contrôle.
	private boolean demande_sc = false;
	private boolean present_sc = false;
	
	public Controleur(String[] args, Semaphore s) {
		
		this.s=s;
		
		//Definition des identifiants
		this.id_perso = new IdentifiantNoeud(args[0], args[1]);
		this.identifiant_pere = new IdentifiantNoeud(args[2], args[3]);
		
		//On instancie un gestionnaire de transmission
		try {
			gestionnaire_de_transmission = new GestionnaireTransmission(this);
			
			//Si le noeud n'a pas de père .
			if(identifiant_pere.est_nul())
				//On créer le jeton
				jeton=new Jeton();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	public IdentifiantNoeud getId_perso() {
		return id_perso;
	}

	/**
	 * @author guillaume
	 * Methode de demande d'entrée en section critique.
	 * 
	 */	
	public boolean demander_SectionCritique()
	{
		//SI ya déjà une demande ne pas autoriser une nouvelle
		if(demande_sc || present_sc)
		{
			System.out.println("C: J'ai recu une demande de sc mais j'en ai déjà eu une");
			return false;
		}
		//Sinon
		else {
			System.out.println("C: J'ai recu une demande de section critique");
			// Si je possède le jeton (ie je n'ai pas de père, je suis le père)
			if (identifiant_pere.est_nul()) {
				// J'ajoute ma requête au jeton
				jeton.file_requetes.ajouterRequete(new Requete(id_perso));
				// Je previens le processus metier qu'il peut aller en sc
				s.release();
				// Je me positionne comme étant présent dans la sc
				present_sc = true;
			}
			// Si je n'ai pas le jeton
			else {
				// je fais une demande de transmission de requête
				Requete requete = new Requete(id_perso);
				//Je me met dans la requête
				requete.ajouterNoeudChemin(id_perso);
				
				gestionnaire_de_transmission.transmettre(requete,identifiant_pere);
				// Et je me positionne en tant que demandant de sc
				demande_sc = true;
			}
			
			return true;
		}
		
		
	}	
	
	/**
	 * @author guillaume
	 * Methode de demande de sorti de section critique.
	 * 
	 */
	public boolean sortir_SectionCritique()
	{
		if(present_sc)
		{
			//Je ne me positionne plus dans la sc
			present_sc = false;
			//La requête individuel est supprimé de la file
			jeton.file_requetes.retirerRequete();
			
			// Si la file n'est pas vide
			if(!jeton.file_requetes.estVide())
			{
				//Je modifie les requêtes de rootage
				identifiant_pere = jeton.file_requetes.rootage(id_perso);
				//J'envoi le jeton au processus 
				gestionnaire_de_transmission.transmettre(jeton, identifiant_pere);
			}
			
			System.out.println("C: Je suis sortie de la SC");	
			return true;
		}
		else
		{
			return false;
		}
	}
	
	void recevoir(Jeton jeton)
	{
		//Je récupére le jeton
		this.jeton=jeton;
		
		System.out.println("J'ai reçu une demande de transmission de jeton");
		//C'est maintenant ce processus le possesseur du jeton, donc pas de père.
		identifiant_pere=new IdentifiantNoeud("_","_");
		
		//Je récupère l'identifiant de celui à qui je dois envoyé le jeton
		IdentifiantNoeud demandeur = jeton.file_requetes.voirRequete().getDemandeur();
		
		//Je regarde si le demandeur correspond à mon nom.s.release();
		//TO DO: Traiter les cas ou le jeton est vide ou des trucs du style
		if(demandeur.equals(id_perso))
		{
			//Si je suis le demandeur
			//=> J'autorise mon processus métier à aller dans la sc
			s.release();
			//=> present_dans_sc = true; et demande_dans_sc = false;
			present_sc = true;
			demande_sc = false;
			
			
		}
		else
		{
			//Reforme la liste des requêtes du jeton
			identifiant_pere=jeton.file_requetes.rootage(id_perso);
			/*Envoi du jeton au fils*/
			gestionnaire_de_transmission.transmettre(jeton,identifiant_pere);
			
			
		}
	}
	/**
	 * 
	 * @author guillaume
	 * Lorsque je recois une requête
	*/
	void recevoir(Requete requete)
	{
		//Si je suis le possesseur du jeton
		if(identifiant_pere.est_nul())
		{
			//Si je suis présent dans la sc
			if(present_sc)
			{
				//J'ajoute la requête à la file de requête
				jeton.file_requetes.ajouterRequete(requete);
			}
			//Si je suis pas dans la sc
			else
			{
				//Je récupère l'id du processus auquel je dois envoyer la requete
				identifiant_pere = requete.retirer_premier_NoeudChemin();
				//Ajouter la requête au jeton
				jeton.file_requetes.ajouterRequete(requete);
				//j'envoi le jeton au processus cible qui est mon père
				gestionnaire_de_transmission.transmettre(jeton, identifiant_pere);
				//On met le jeton à nulle
				this.jeton = null;
			}
			
		}
		else
		{
			//je me rajoute sur le chemin
			requete.ajouterNoeudChemin(id_perso);
			//je transmet la requête à mon père
			gestionnaire_de_transmission.transmettre(requete, identifiant_pere);
		}
	}
	
	public Object get_objet_partage(String id)
	{
		return jeton.objets_partages.get(id);
	}
	
	public void set_objet_partage(String id,Object reference)
	{
		 jeton.objets_partages.add(id,reference);
	}
	
	public IdentifiantNoeud get_identifiant_pere()
	{
		return identifiant_pere;
	}

}
