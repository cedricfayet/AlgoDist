package HT.controleur;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Transmission extends Remote {

	//Methode permettant de transmettre un objet
	public void envoyer(Jeton jeton) throws RemoteException;
	
	//Methode qui permet de transmettre une requête
	public void envoyer(Requete requete) throws RemoteException;
}
