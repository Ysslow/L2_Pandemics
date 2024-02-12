package pandemics;

/** Disease Class */
public class Disease{
	
	/** Attributes */
	protected String DiseaseName;
	protected boolean remedy;
	protected boolean eradication;
	
	/** Disease Constructor
	 * @param DiseaseName the name of the name of the disease.
	 */
	public Disease(String DiseaseName){
		this.DiseaseName = DiseaseName;
		this.remedy = false;
		this.eradication = false;
	}
	
	
	/** Return the current DiseaseName
	 * @return the name of the Disease
	 */
	public String getDiseaseName(){
		return this.DiseaseName;
	}
	
	/** Set a new DiseaseName
	 * @param DiseaseName the new name
	 */
	public void setDiseaseName(String DiseaseName){
		this.DiseaseName = DiseaseName;
	}

	/** Return the status of remedy
	 * @return True if the disease has a cure, False either 
	 */
	public boolean isRemedy() {
		return remedy;
	}

	/** Set status of Remedy
	 * @param remedy the remedy
	 */
	public void setRemedy(boolean remedy) {
		this.remedy = remedy;
	}

	
	/** Return the status of eradication
	 * @return True if the disease is eradicated, False either 
	 */
	public boolean isEradication() {
		return eradication;
	}

	/**
	 * Set status of eradication
	 * 
	 * @param eradication eradication of this disease
	 */
	public void setEradication(boolean eradication) {
		this.eradication = eradication;
	}
	
	
}
