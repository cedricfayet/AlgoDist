
public class IdentifiantNoeud {

	private String adresse;
	private String port;
	private Integer id_process;
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	//TO DO: A revoir cette histoire d'Integer
	int getId_process() {
		return id_process.intValue();
	}
	void setId_process(int id_process) {
		this.id_process = new Integer(id_process);
	}
	
	public String toString() {
		return "//"+adresse+":"+port+"/"+id_process.toString();
	}
	
}
