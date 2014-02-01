import java.io.Serializable;



class IdentifiantNoeud implements Serializable 
{

      
		/**
	 * 
	 */
	private static final long serialVersionUID = -7197127797324934867L;
		private String adresse;
        private String port;
        
        public IdentifiantNoeud(String adresse, String port) 
        {
        	setAdresse(adresse);
        	setPort(port);
		}
        
		public String getAdresse() 
		{
                return adresse;
        }
		
        public void setAdresse(String adresse) 
        {
                this.adresse = adresse;
        }
        
        public String getPort() 
        {
                return port;
        }
        public void setPort(String port) 
        {
                this.port = port;
        }
        
       
        
        public String toString() 
        {
                return "//"+adresse+":"+port+"/"+"gestionnaire_de_transmission";
        }
        
        public boolean est_nul()
        {
        	
        	return adresse.equals("_");
        }

	

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			IdentifiantNoeud other = (IdentifiantNoeud) obj;
			if (adresse == null) {
				if (other.adresse != null)
					return false;
			} else if (!adresse.equals(other.adresse))
				return false;
			if (port == null) {
				if (other.port != null)
					return false;
			} else if (!port.equals(other.port))
				return false;
			return true;
		}
        
        
}