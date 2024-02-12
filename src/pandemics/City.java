package pandemics;

import java.util.ArrayList;

import javax.sound.midi.VoiceStatus;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/* City Class*/
public class City {

	/** Attributes for Json reading file */
	private FileReader reader;
	private JSONObject jsonFiles;
	private String access;

	/** Attributes */
	private String cityName;
	private int cubes;
	private ArrayList<City> neighbors;
	private boolean research;
	private int sector;
	private boolean hatching;

	/**
	 * City constructor
	 * 
	 * @param cityName the name of the city
	 * @throws FileNotFoundException
	 */
	public City(String cityName, String access) throws FileNotFoundException {
		this.access = access;
		this.neighbors = new ArrayList<>();
		this.reader = new FileReader(access);
		this.jsonFiles = new JSONObject(new JSONTokener(reader));
		this.hatching = false;
		this.cityName = cityName;
		this.cubes = 0;
		this.research = false;
		this.sector = 0;
	}

	/**
	 * City constructor
	 * 
	 * @param cityName the name of the city
	 * @param sector   the number of the sector link to the city
	 */
	public City(String cityName, int sector) {
		this.hatching = false;
		this.cityName = cityName;
		this.cubes = 0;
		this.research = false;
		this.sector = sector;
		this.neighbors = new ArrayList<>();
	}

	/**
	 * City constructor
	 * 
	 * @param cityName the name of the city
	 * @param sector   the number of the sector link to the city
	 * @throws FileNotFoundException
	 */
	public City(String cityName, int sector, String access) throws FileNotFoundException {
		this.access = access;
		this.neighbors = new ArrayList<>();
		this.reader = new FileReader(access);
		this.jsonFiles = new JSONObject(new JSONTokener(reader));
		this.hatching = false;
		this.cityName = cityName;
		this.cubes = 0;
		this.research = false;
		this.sector = sector;
	}

	/**
	 * Neighbor of c
	 * 
	 * @param c
	 * @return True if it's a neighbor of c, False either
	 */
	public boolean isNeighbors(City c) {
		return neighbors.contains(c);
	}

	/**
	 * InitNeighbors read a json file passed in param on the constructor and
	 * initialise a list of city called neighbors
	 * 
	 * @throws FileNotFoundException
	 */
	public void initNeighbors(ArrayList<City> city) throws FileNotFoundException {
		JSONObject neigh = jsonFiles.getJSONObject("neighbors");
		JSONArray arr = neigh.getJSONArray(this.cityName);

		for (Object oue : arr) {
			String voiString = (String) oue;
			for (City c : city) {
				if (c.getCityName().equals(voiString)) {
					this.neighbors.add(c);
				}
			}
		}
	}

	/** Add a level of infection */
	public void addInfection() {
		if (this.cubes < ClassicalBoard.MAXCUBE) {
			this.cubes++;
		} else if (!hatching) {
			this.hatching = true;
			spreadInfection();
		}
	}

	/** Spread the Infection */
	public void spreadInfection() {
		for (City c : neighbors) {
			c.addInfection();
		}
	}

	/**
	 * Return the name of the city
	 * 
	 * @return the city name
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * Set a new name for the city
	 * 
	 * @param cityName the new name
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * Return the InfectionLevel of the city
	 * 
	 * @return the InfectionLevel
	 */
	public int getInfection() {
		return cubes;
	}

	/**
	 * Set a new InfectionLevel for the city
	 * 
	 * @param infection the new InfectionLevel
	 */
	public void setInfection(int infection) {
		this.cubes = infection;
	}

	/**
	 * Return the status of research
	 * 
	 * @return True if the city is a research station, False either
	 */
	public boolean isResearch() {
		return research;
	}

	/**
	 * Set a new research for the city
	 * 
	 * @param research a boolean
	 */
	public void setResearch(boolean research) {
		this.research = research;
	}

	/**
	 * Return the current sector
	 * 
	 * @return the current sector
	 */
	public int getSector() {
		return sector;
	}

	/**
	 * Set a new sector for the city
	 * 
	 * @param sector the new sector
	 */
	public void setSector(int sector) {
		this.sector = sector;
	}

	/**
	 * Return the status of hatching
	 * 
	 * @return True if the city has suffered an hatching, False either
	 */
	public boolean isHatching() {
		return hatching;
	}

	/**
	 * Set a hatching for the city
	 * 
	 * @param hatching a boolean
	 */
	public void setHatching(boolean hatching) {
		this.hatching = hatching;
	}

	/**
	 * add a neighbor to city
	 * 
	 * @param c a City
	 */
	public void addNeighbors(City c) {
		this.neighbors.add(c);
	}

	/**
	 * Return the Neighbors list in String
	 * 
	 * @return the Neighbors list in String
	 */
	public String getNeighbors() {
		String res = "[ ";
		for (City city : neighbors) {
			res += city.getCityName() + " , ";
		}
		return res + " ]";
	}

	public ArrayList<City> getNeig() {
		return this.neighbors;
	}

	/**
	 * A simple sentence to describe the City
	 * 
	 * @return the sentence
	 */
	@Override
	public String toString() {
		return cityName + " [ infection=" + cubes + ", research=" + research + ", sector=" + sector + " neighbors = "
				+ this.getNeighbors() + "]";
	}

}
