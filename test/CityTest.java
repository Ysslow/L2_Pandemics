

import static org.junit.Assert.*;
import org.junit.*;
import pandemics.City;
import pandemics.ClassicalBoard;


/** CityTest Class */
public class CityTest {
	
	private City city;
	private City city1;
	private City city2;
	private City city3;
	private City city4;
	
		
	@Before
	public void before(){
		this.city = new City("ville-1",1);
		this.city1 = new City("ville-2",1);
		this.city2 = new City("ville-3",1);
		this.city3 = new City("ville-4",1);
		this.city4 = new City("ville-5",1);
		ClassicalBoard.SetMAXCUBE(3);
	}
	
	@Test
    public void initiliizationIsOk(){
        assertEquals(city.getCityName(), "ville-1");
        assertEquals(city.getInfection(), 0);
        assertEquals(city.isResearch(), false);
        assertEquals(city.getSector(), 1);
        assertEquals(city.isHatching(), false);
        assertEquals(city.getNeighbors(), "[  ]");
    }
	
	@Test
	public void Getter_and_Setter_CityName(){
		assertEquals(city.getCityName(), "ville-1");
		city.setCityName("changement");
		assertEquals(city.getCityName(), "changement");
	}
	
	@Test
	public void Getter_and_Setter_Infection(){
		assertEquals(city.getInfection(), 0);
		city.setInfection(2);
		assertEquals(city.getInfection(), 2);
	}
	
	@Test
	public void Getter_and_Setter_Neighbors(){
		this.city.addNeighbors(city1);
		this.city.addNeighbors(city2);
		this.city.addNeighbors(city3);
		assertEquals(this.city.isNeighbors(city1), true);
		assertEquals(this.city.isNeighbors(city2), true);
		assertEquals(this.city.isNeighbors(city3), true);
		assertEquals(this.city.isNeighbors(city4), false);
	}
	
	@Test
	public void Getter_and_Setter_Research(){
		assertEquals(city.isResearch(), false);
		city.setResearch(true);
		assertEquals(city.isResearch(), true);
		city.setResearch(false);
		assertEquals(city.isResearch(), false);
	}
	
	@Test
	public void Getter_and_Setter_Sector(){
		assertEquals(city.getSector(), 1);
		city.setSector(3);
		assertEquals(city.getSector(), 3);
	}
	
	@Test
	public void Getter_and_Setter_Hatching(){
		assertEquals(city.isHatching(), false);
		city.setHatching(true);
		assertEquals(city.isHatching(), true);
		city.setHatching(false);
		assertEquals(city.isHatching(), false);
	}
	
	@Test
	public void Test_AddInfection(){
		this.city.addNeighbors(city1);
		this.city.addNeighbors(city2);
		this.city.addNeighbors(city3);
		assertEquals(city.isHatching(),false);
		assertEquals(city.getInfection(),0);
		city.addInfection();
		assertEquals(city.getInfection(),1);
		city.addInfection();
		assertEquals(city.getInfection(),2);
		city.addInfection();
		assertEquals(city.getInfection(),3);
		city.addInfection();
		assertEquals(city.isHatching(),true);
		assertEquals(city1.getInfection(),1);
		assertEquals(city2.getInfection(),1);
		assertEquals(city3.getInfection(),1);
		city.addInfection();
		assertEquals(city1.getInfection(),1);
		assertEquals(city2.getInfection(),1);
		assertEquals(city3.getInfection(),1);
	}
}