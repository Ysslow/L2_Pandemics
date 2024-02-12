package pandemics;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/** ClassicalBoard Class */
public class ClassicalBoard extends Board {
	
	public static int MAXCUBE = 0;
	public static int MAXACTION = 0;

	private FileReader reader;
	private JSONObject jsonFiles;
	
	/**
	 * ClassicalBoard Constructor
	 * 
	 * @param access   the access
	 */

	public ClassicalBoard(String access) throws FileNotFoundException {
		super(access);
		this.reader = new FileReader(access);
		this.jsonFiles = new JSONObject(new JSONTokener(reader));
		this.initBoard();
		this.initNeighbors();
	}
	
	/**
	 * SetMAXCUBE use for test of CityTest
	 * 
	 * @param MAXCUBE max cube in a city
	 */
	public static void SetMAXCUBE(int MAXCUBE){
		ClassicalBoard.MAXCUBE = MAXCUBE ;
	}
	
	/**
	 * SetMAXACTION use for test of PlayerTest
	 * 
	 * @param MAXACTION max action by player in a the game
	 */
	public static void SetMAXACTION(int MAXACTION){
		ClassicalBoard.MAXACTION = MAXACTION;
	}

	/** 
	 * Initiate the board 
	 * 
	 * @throws FileNotFoundException */
	@Override
	protected void initBoard() throws FileNotFoundException {
		for (int i = 1; i < 49; i++) {
			String aa = "ville-" + i;
			int oue = jsonFiles.getJSONObject("cities").getInt(aa);
			this.cities.add(new City(aa,oue,this.access));
		}
	}

	
	
	/** Initiate the neighbors of each city
	 * @throws FileNotFoundException */
	@Override
	public void initNeighbors() throws FileNotFoundException {
		for (City c : cities) {
			c.initNeighbors(cities);
		}
	}

	public String toString() {
		String res = "";
		for (City city : cities) {
			res += city.toString() + "\n";
		}
		return res;
	}

}
