import java.util.Stack;

public class Requete
{
	private IdentifiantNoeud demandeur;
	private Stack<IdentifiantNoeud> chemin;
	
	Requete(IdentifiantNoeud demandeur,Stack<IdentifiantNoeud>chemin)
	{
		this.setDemandeur(demandeur);
		this.setChemin(chemin);
	}
	
	public IdentifiantNoeud retirerNoeudChemin()
	{
		return chemin.pop();
	}
	
	public IdentifiantNoeud voirNoeudChemin()
	{
		return chemin.peek();
	}
	
	public void ajouterNoeudChemin(IdentifiantNoeud nom)
	{
		chemin.push(nom);
	}

	public IdentifiantNoeud getDemandeur() {
		return demandeur;
	}

	private void setDemandeur(IdentifiantNoeud demandeur) {
		this.demandeur = demandeur;
	}

	public Stack<IdentifiantNoeud> getChemin() {
		return chemin;
	}

	private void setChemin(Stack<IdentifiantNoeud> chemin) {
		this.chemin = chemin;
	}
}
