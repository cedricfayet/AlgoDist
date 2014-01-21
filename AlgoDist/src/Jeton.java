import java.util.LinkedList;
import java.util.Queue;

public class Jeton 
{
	private Queue<Requete> file_requete;
	
	Jeton()
	{
		file_requete = new LinkedList<Requete>();
	}
	
	public void ajouterRequete(Requete requete)
	{
		file_requete.add(requete);
	}
	
	public Requete voirRequete()
	{
		return file_requete.peek();
	}
	
	public Requete retirerRequete()
	{
		return file_requete.poll();
	}
	
	public boolean estVide()
	{
		return file_requete.isEmpty();
	}
	
	public IdentifiantNoeud rootage(IdentifiantNoeud identifiantperso)
	{
		IdentifiantNoeud suiv;
		retirerRequete();
		suiv=retirerRequete().voirNoeudChemin();
		
		for(Requete req:file_requete)
		{
			if(req.voirNoeudChemin() == identifiantperso)
			{
				req.retirerNoeudChemin();
				
			}
			else
			{
				req.ajouterNoeudChemin(identifiantperso);
			}
		}
		
		return suiv;
	}
	

}
