
import java.io.Serializable;
import java.util.Stack;


class Requete implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9166591421857178677L;
	private IdentifiantNoeud demandeur;
	private Stack<IdentifiantNoeud> chemin;
	
	/**Stack<IdentifiantNoeud>chemin
	 * @author guillaume
	 * @param demandeur
	 * 
	 * Methode de création d'une nouvelle requête de demande de jeton avec l'id du demandeur.
	 */
	Requete(IdentifiantNoeud demandeur)
	{
		this.setDemandeur(demandeur);
		this.chemin = new Stack<IdentifiantNoeud>();
	}
	
	IdentifiantNoeud retirer_premier_NoeudChemin()
	{
		return chemin.pop();
	}
	
	IdentifiantNoeud voir_premier_NoeudChemin()
	{
		return chemin.peek();
	}
	
	void ajouterNoeudChemin(IdentifiantNoeud nom)
	{
		chemin.push(nom);
	}

	IdentifiantNoeud getDemandeur() {
		return demandeur;
	}

	private void setDemandeur(IdentifiantNoeud demandeur) {
		this.demandeur = demandeur;
	}

	Stack<IdentifiantNoeud> getChemin() {
		return chemin;
	}
}
