package HT.controleur;
import java.util.Stack;


class Requete
{
	private IdentifiantNoeud demandeur;
	private Stack<IdentifiantNoeud> chemin;
	
	Requete(IdentifiantNoeud demandeur,Stack<IdentifiantNoeud>chemin)
	{
		this.setDemandeur(demandeur);
		this.setChemin(chemin);
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

	private void setChemin(Stack<IdentifiantNoeud> chemin) {
		this.chemin = chemin;
	}
}
