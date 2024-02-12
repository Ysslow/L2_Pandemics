package pandemics;

import java.util.ArrayList;

/* Globetrotter Class */
public class Globetrotter extends Player{

	/**
	 * Globetrotter first constructor
	 * 
	 * @param PlayerName the name of the player
	 * @param city the city where the player is
	 * @param disease the disease of the city
	 */
	public Globetrotter(String PlayerName, City city, Disease disease) {
		super(PlayerName, city, disease);
	}

	
	/**
	 * Moves the player to a another city
	 * 
	 * @param c the new city where the player is going
	 * @return True if the player was able to move, false otherwise
	 */
	@Override
	public boolean move(City c,ArrayList<Disease> arr){
		if (this.nbAction != ClassicalBoard.MAXACTION) {
			this.setCity(c);
			this.disease = arr.get(c.getSector());
			this.nbAction++;
			return true;
		} else {
			return false;
		}
	}
}
