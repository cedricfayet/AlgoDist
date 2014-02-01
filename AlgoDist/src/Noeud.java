import java.util.concurrent.Semaphore;



public class Noeud 
{
	private static Exemple1 metier;
	private static Controleur controleur;

	public static void main(String[] args)
	{

		Semaphore s=new Semaphore(0);

		//TODO: Ajouter les arguments du main definissant mon pere.
		controleur=new Controleur(args,s);
		metier=new Exemple1(s,controleur);

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
