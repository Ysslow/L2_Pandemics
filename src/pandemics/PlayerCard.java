package pandemics;

public class PlayerCard extends TypicalCard{

	protected City city;
	protected Disease dis;
	
	/**
	 * Constructor PlayerCard, make a player card
	 * @param cityName city's name of the player card
	 * @param disName disease name of the card
	 */
	public PlayerCard(City cityName, Disease disName) {
		super();
		this.city = cityName;
		this.dis = disName;
	}
	
	
	/**
	 * Returns the City object associated with the player card.
	 * @return the City object associated with the player card
	 */
	public City getCity() {
		return city;
	}


	/**
	 * Sets the City object associated with the player card.
	 * @param cityName the City object to associate with the player card
	 */
	public void setCity(City cityName) {
		this.city = cityName;
	}


	/**
	 * Returns the Disease object associated with the player card.
	 * @return the Disease object associated with the player card
	 */
	public Disease getDis() {
		return dis;
	}


	/**
	 * Sets the Disease object associated with the player card.
	 * @param disName the Disease object to associate with the player card
	 */
	public void setDis(Disease disName) {
		this.dis = disName;
	}


	/**
	 * Returns a string representation of the PlayerCard object.
	 * @return a string representation of the PlayerCard object
	 */
	@Override
	public String toString() {
		return super.toString()+"PlayerCard [ cityName=" + city.getCityName() + ", disName=" + dis.getDiseaseName() + "]";
	}
	
	

}
