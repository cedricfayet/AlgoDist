import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;

public class Noeud 
{
	private static Metier metier;
	private static GestionnaireTransmission controleur;
	public static IdentifiantNoeud identifiant;
	public static IdentifiantNoeud identifiant_pere;
	public static Jeton jeton;
	
	public static void main(String[] args)
	{
		//Récupération de l'identifiant du noeud
		identifiant = new IdentifiantNoeud();
		try {
			identifiant.setAdresse(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		identifiant_pere.setAdresse("AdresseDuPere");//Donner l'adresse du père en argument
		Semaphore s = new Semaphore(0);
		
		metier=new Metier();
		
		try {
			controleur=new GestionnaireTransmission(identifiant_pere,s);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		if(identifiant_pere == null)
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
