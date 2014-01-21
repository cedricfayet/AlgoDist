public class Noeud 
{
	private static Metier metier;
	private static Controleur controleur;
	public static String identifiant;
	public static String identifiant_pere;
	public static Jeton jeton;
	
	public static void main(String[] args)
	{
		identifiant = args[0];
		identifiant_pere=args[1];
		
		metier=new Metier();
		controleur=new Controleur();

		if(identifiant_pere == null)
			jeton=new Jeton();

		controleur.start();
		metier.start();
		
		try 
		{
			metier.join();
			controleur.join();
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
