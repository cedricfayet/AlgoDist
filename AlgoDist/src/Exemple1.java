import java.util.Scanner;
import java.util.concurrent.Semaphore;



public class Exemple1 extends Thread {

	private Semaphore s;
	private Controleur c;
	private Scanner in;

	public Exemple1(Semaphore s, Controleur c) {
		this.s=s;
		this.c=c;
	}

	public void run()
	{
		 String resultat;
		 in = new Scanner(System.in);
		 
		 while(true)
		 {
			 resultat = in.nextLine();
			 
			 if("DemanderSC".equals(resultat))
			 {
				 System.out.println("Métier : demande d'entrée en section critique prise en compte.");
				 if(c.demander_SectionCritique())
				 {
					 try 
					 {
						s.acquire();
						System.out.println("Métier : section critique acquise.");
	
					 } 
					 catch (InterruptedException e) 
					 {
						e.printStackTrace();
					 }
				 }
			 }
			 
			 if("SortirSC".equals(resultat))
			 {
				 if(c.sortir_SectionCritique())
					 System.out.println("Métier : section critique rendue.");					 
			 }
			 
		 }
	}
}