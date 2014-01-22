import java.util.concurrent.Semaphore;

public class Noeud 
{
	private static Metier metier;
	private static Controleur controleur;
	public static IdentifiantNoeud identifiant;
	public static IdentifiantNoeud identifiant_pere;
	public static Jeton jeton;
	public static Semaphore s;
	
	public static void main(String[] args)
	{
		
		//identifiant = new IdentifiantNoeud(args[0],args[1],args[2]);
		//identifiant_pere= new IdentifiantNoeud(args[3],args[4],args[5]);
		
		identifiant = new IdentifiantNoeud("","","");
		identifiant_pere= new IdentifiantNoeud("","","");	
		
		s=new Semaphore(0);
		controleur=new Controleur(s);
		metier=new Metier(s,controleur);

		if(identifiant_pere.estRacine())
			jeton=new Jeton();

		metier.start();
		
		try 
		{
			metier.join();
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
