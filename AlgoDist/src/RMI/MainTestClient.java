package guillaume.algoDist.RMI;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;

public class MainTestClient {

	/**
	 * @param args
	 */
	public static void init() {
		
		  if (System.getSecurityManager() == null) {
			    System.setSecurityManager(new RMISecurityManager());
			  }

			  try {
			    Remote r = Naming.lookup("rmi://10.0.0.13/TestRMI");
			    System.out.println("La remote a fonctionné");
			    System.out.println(r);
			      if (r instanceof Gestionnaire_de_transmission) {
			        ((Gestionnaire_de_transmission) r).transmettre_jeton();
			      }
			  } catch (Exception e) {
			    e.getStackTrace();
			    System.out.println("La remote a pas fonctionné");
			  }

	}

}
