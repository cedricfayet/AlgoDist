package guillaume.algoDist.RMI;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class Gestionnaire_de_transmission extends UnicastRemoteObject implements
		Transmission {

	
	
	/**
	 * A quoi ca sert ??
	 */
	private static final long serialVersionUID = 4459518106264845826L;

	
	protected Gestionnaire_de_transmission() throws RemoteException
	{
		super();
	}
	
	@Override
	public void transmettre_jeton() throws RemoteException {
		System.out.println("J'ai reçu une demande de transmission de jeton");
		//TO DO: Récuperer le jeton et si il n'est pas pour nous, le transmettre à son père

	}

	@Override
	public void transmettre_requête() throws RemoteException {
		System.out.println("J'ai reçu une demande d'envoi de requête, je la transmet à mon père.");
		//TO DO: Trantement et envoi de la requête, la transmettre à son père si on est pas le pocesseur du jeton.

	}

}
