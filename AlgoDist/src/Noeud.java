import java.util.concurrent.Semaphore;

import metier.Metier;

import HT.controleur.Controleur;

public class Noeud 
{
	private static Metier metier;
	private static Controleur controleur;
	
	
	public static void main(String[] args)
	{
				
		Semaphore s=new Semaphore(0);
		
		//TODO: Ajouter les arguments du main definissant mon pere.
		controleur=new Controleur(s);
		metier=new Metier(s,controleur);

		metier.start();
		try 
		{
			metier.join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
