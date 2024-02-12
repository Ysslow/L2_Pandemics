package pandemics;

/**
 * This class represents an infection card in the Pandemic game. It contains
 * information about the city and the disease that the card represents.
 */
public class InfectionCard extends Card {

	protected City city;
	protected Disease dis;

	/**
	 * Constructor of InfectionCards
	 * 
	 * @param city The city that the infection card represents
	 * @param dis  The disease that the infection card represents
	 */
	public InfectionCard(City city, Disease dis) {
		super();
		this.city = city;
		this.dis = dis;

	}

	/**
	 * Returns the city represented by this infection card
	 * 
	 * @return The city object
	 */
	public City getCity() {
		return city;
	}

	/**
	 * Sets the city represented by this infection card
	 * 
	 * @param city The new city object to be set
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * Returns the disease represented by this infection card
	 * 
	 * @return The disease object
	 */
	public Disease getDis() {
		return dis;
	}

	/**
	 * Infects the city represented by this infection card
	 */
	public void action() {
		this.city.addInfection();

	}

	/**
	 * Returns a string representation of the InfectionCard object
	 * 
	 * @return A string representing the city and disease of the card
	 */
	@Override
	public String toString() {
		return "InfectionCard [" + "city=" + city.getCityName() + ", dis=" + dis.getDiseaseName() + "]";
	}
}
