import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Jeton 
{
	public File_requetes file_requetes; 
	public Objets_partages objets_partages;
	
	public Jeton()
	{
		this.file_requetes = new File_requetes();
		this.objets_partages = new Objets_partages();
	}

	
}

class Objets_partages
{
	private HashMap<String, Object> objets_partages;
	
	public Objets_partages() {
		this.objets_partages = new HashMap<String, Object>();
	}
	
	public void add(String id, Object reference)
	{
		objets_partages.put(id, reference);
	}
	
	public Object get(String id)
	{
		return objets_partages.get(id);
	}
	
}



class File_requetes
{
	private Queue<Requete> file_requete;
	
	public File_requetes() {
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
		suiv=retirerRequete().voir_premier_NoeudChemin();
		
		for(Requete req:file_requete)
		{
			if(req.voir_premier_NoeudChemin() == identifiantperso)
			{
				req.retirer_premier_NoeudChemin();
			}
			else
			{
				req.ajouterNoeudChemin(identifiantperso);
			}
		}
		
		return suiv;
	}
}

