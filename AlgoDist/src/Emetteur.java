import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class Emetteur {

	private static int port = 1099;
	private static Transmission gestionnaire_de_transmission = null;

	public static void init() {

		System.setProperty("java.rmi.server.codebase", "file:./");
		System.setProperty("java.security.policy", "./java.policy");

		if (System.getSecurityManager() == null) {
		System.setSecurityManager(new RMISecurityManager());
		}

		try {
			gestionnaire_de_transmission = (Transmission) Naming.lookup("//"
					+ InetAddress.getLocalHost().getHostAddress() + ":" + port
					+ "/Transmission");
			gestionnaire_de_transmission.transmettre_jeton(new Jeton());

		} catch (Exception e) {
			System.out.print(e.getMessage());
			System.out.println("La remote a pas fonctionné");
		}

	}

}
