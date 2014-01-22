import java.util.concurrent.Semaphore;


public class Controleur
{
	private Semaphore s;
	
	Controleur(Semaphore s)
	{
		this.s=s;
	}
	
	public void demander_SectionCritique()
	{
		System.out.println("Ah j'attend avant de donner accès à la SC :D");
		s.release();
	}
	
	public void sortir_SectionCritique()
	{
		
	}
	
	public void recevoir(Jeton jeton){}
	
	public void recevoir(Requete requete){}
}
