package metier;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

import HT.controleur.Controleur;

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
		 in = new Scanner(System.in);
		 String resultat;
		 
		 resultat = in.nextLine();
		 
		 if("DemanderSC".equals(resultat))
		 {
			 System.out.println("Métier : demande d'entrée en section critique prise en compte.");
			 c.demander_SectionCritique();
			 
			 try 
			 {
				s.acquire();
				System.out.println("Métier : section critique acquise.");
				
				 if("SortirSC".equals(resultat))
				 {
					 c.sortir_SectionCritique();
					 System.out.println("Métier : section critique rendue.");					 
				 }
			 } 
			 catch (InterruptedException e) 
			 {
				e.printStackTrace();
			 }
		 }
	}
}
