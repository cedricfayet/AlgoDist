import java.util.concurrent.Semaphore;


public class Metier extends Thread
{
	private Semaphore s;
	private Controleur controleur;
	
	public Metier(Semaphore s,Controleur controleur)
	{
		this.s=s;
		this.controleur=controleur;
	}
	
	public void run()
	{
		System.out.println("Ah j'attend que l'on me donne accès à la SC");
		controleur.demander_SectionCritique();
		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Je l'ai eut :)");
		controleur.sortir_SectionCritique();
	}
}
