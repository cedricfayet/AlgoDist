

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Transmission extends Remote {

	//Methode permettant de transmettre un objet
	//TO DO: Il faut rajouter un objet jeton en argument
	public void transmettre_jeton() throws RemoteException;
	
	//Methode qui permet de transmettre une requête
	//TO DO: Il faut rajouter l'objet requête en argument
	public void transmettre_requête() throws RemoteException;
}
