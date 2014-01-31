package HT.controleur;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


class Jeton 
{
	File_requetes file_requetes; 
	Objets_partages objets_partages;
	
	Jeton()
	{
		this.file_requetes = new File_requetes();
		this.objets_partages = new Objets_partages();
	}	
}


class Objets_partages
{
	private HashMap<String, Object> objets_partages;
	
	Objets_partages() {
		this.objets_partages = new HashMap<String, Object>();
	}
	
	void add(String id, Object reference)
	{
		objets_partages.put(id, reference);
	}
	
	Object get(String id)
	{
		return objets_partages.get(id);
	}
	
}



class File_requetes
{
	private Queue<Requete> file_requete;
	
	File_requetes() {
		file_requete = new LinkedList<Requete>();
	}
	
	void ajouterRequete(Requete requete)
	{
		file_requete.add(requete);
	}
	
	Requete voirRequete()
	{
		return file_requete.peek();
	}
	
	Requete retirerRequete()
	{
		return file_requete.poll();
	}
	
	boolean estVide()
	{
		return file_requete.isEmpty();
	}
	
	IdentifiantNoeud rootage(IdentifiantNoeud identifiantperso)
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

