package pandemics;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/** Board Class */
public abstract class Board {

	protected ArrayList<City> cities;
	protected String access;
	protected int globInfRate;

	/**
	 * Board Constructor
	 * 
	 * @param access the defined access
	 */
	public Board(String access) {
		this.cities = new ArrayList<>();
		this.access = access;
		this.globInfRate = 2;
	}

	/** InitBoard method 
	 * @throws FileNotFoundException */
	protected abstract void initBoard() throws FileNotFoundException;


	/** getNUmbersStations get the total number of station in the map
	 * 
	 * @return integer cpt
	 */
	public int getNumbersStations() {
		int cpt = 0;
		for (City city : cities) {
			if (city.isResearch()) {
				cpt++;
			}
		}
		return cpt;
	}

	/**getCities get the cities list 
	 * 
	 * @return
	 */
	public ArrayList<City> getCities() {
		return cities;
	}
	
	
	/**getBlobInfRate
	 * 
	 * @return
	 */
	public int getGlobInfRate() {
		return globInfRate;
	}

	/**
	 * 
	 * @param globInfRate
	 */
	public void incGlobInfRate() {
		this.globInfRate ++;
	}

	/**moveStation move station from city c1 to city c2
	 * 
	 * @param c1 first city
	 * @param c2 second city where the station is move
	 * @return true if the station has been moved, false otherwise 
	 */
	public boolean moveStation(City c1,City c2) {
		 if (cities.indexOf(c1) == -1 || cities.indexOf(c2) == -1) {
			return false;
		}
		 else if (this.getNumbersStations() == 6) {
			c1.setResearch(false);
			c2.setResearch(true);
			return true;
		}
		else {
			return false;
		}
	}

	/** Initiate the neighbors of each city
	 * @throws FileNotFoundException */
	protected abstract void initNeighbors() throws FileNotFoundException;
}
