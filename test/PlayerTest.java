
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

import pandemics.City;
import pandemics.ClassicalBoard;
import pandemics.Disease;
import pandemics.Game;
import pandemics.Player;
import pandemics.PlayerCard;
import pandemics.TypicalCard;

/** PlayerTest Class */
public class PlayerTest {

	private Player player1;
	private City city1;
	private City city2;
	private City city3;
	private City city4;
	private City city5;
	private City city6;
	private Disease disease1;
	private Disease disease2;
	private ArrayList<PlayerCard> playersHand1;
	private PlayerCard card1;
	private PlayerCard card2;
	private PlayerCard card3;
	private PlayerCard card4;
	private PlayerCard card5;
	private PlayerCard card6;
	private Stack<TypicalCard> def;
	private ArrayList<Disease> diseases;
	private Game game;

	@Before
	public void before() throws FileNotFoundException {
		this.def = new Stack<>();
		this.diseases = new ArrayList<>();
		this.city1 = new City("ville-1", 1);
		this.city2 = new City("ville-2", 1);
		this.city3 = new City("ville-3", 1);
		this.city4 = new City("ville-4", 1);
		this.city5 = new City("ville-5", 1);
//		this.city6 = new City("ville-6", 1);
		this.disease1 = new Disease("maladie-1");
		this.disease2 = new Disease("maladie-2");
		this.card1 = new PlayerCard(city1, disease1);
		this.card2 = new PlayerCard(city2, disease1);
		this.card3 = new PlayerCard(city3, disease1);
		this.card4 = new PlayerCard(city4, disease2);
		this.card5 = new PlayerCard(city5, disease1);
		this.card6 = new PlayerCard(city6, disease2);
		this.playersHand1 = new ArrayList<>();
		this.playersHand1.add(card1);
		this.playersHand1.add(card2);
		this.playersHand1.add(card3);
		this.playersHand1.add(card4);
		this.playersHand1.add(card5);
		this.playersHand1.add(card6);
		this.player1 = new Player("joueur-1", city1, disease1, playersHand1);
		this.city1.addNeighbors(city2);
		this.city1.addNeighbors(city3);
		this.diseases.add(disease1);
		this.diseases.add(disease2);
		this.game = new Game("Map.json");
		ClassicalBoard.SetMAXACTION(4);
	}

	@Test
	public void initilizationIsOkWithoutMaxAction() {
		assertEquals(player1.getPlayerName(), "joueur-1");
		assertEquals(player1.getCity(), city1);
		assertEquals(player1.getDisease(), disease1);
	}

	@Test
	public void initilizationIsOkWithMaxAction() {
		assertEquals(player1.getPlayerName(), "joueur-1");
		assertEquals(player1.getCity(), city1);
		assertEquals(player1.getDisease(), disease1);
		assertEquals(ClassicalBoard.MAXACTION, 4);
	}

	@Test
	public void Getter_and_Setter_PlayerName() {
		assertEquals(player1.getPlayerName(), "joueur-1");
		player1.setPlayerName("premier joueur");
		assertEquals(player1.getPlayerName(), "premier joueur");
	}

	@Test
	public void Getter_and_Setter_City() {
		assertEquals(player1.getCity(), city1);
		player1.setCity(city2);
		assertEquals(player1.getCity(), city2);
	}

	@Test
	public void Getter_and_Setter_Disease() {
		assertEquals(player1.getDisease(), disease1);
		player1.setDisease(disease2);
		assertEquals(player1.getDisease(), disease2);
	}

	@Test
	public void TestMovePlayer() {
		assertEquals(player1.getCity(), city1);
		assertTrue(player1.move(city2, diseases));

		player1.setCity(city1);

		assertEquals(player1.getCity(), city1);
		assertFalse(player1.move(city4, diseases));
	}

	@Test
	public void TestBuildPlayer() {
		assertFalse(player1.getCity().isResearch());
		assertTrue(player1.build(def));
		assertFalse(player1.getPlayersHand().contains(this.card1));
		assertTrue(player1.getPlayersHand().contains(this.card2));
		assertTrue(player1.getPlayersHand().contains(this.card3));
		assertTrue(player1.getPlayersHand().contains(this.card4));
		assertTrue(player1.getCity().isResearch());
		assertFalse(player1.build(def));
	}

	@Test
	public void testFindCure() {
		assertFalse(player1.getDisease().isRemedy());
		player1.getCity().setResearch(true);
		assertTrue(player1.findACure());
		assertFalse(player1.getPlayersHand().contains(this.card1));
		assertFalse(player1.getPlayersHand().contains(this.card2));
		assertFalse(player1.getPlayersHand().contains(this.card3));
		assertTrue(player1.getPlayersHand().contains(this.card4));
		assertFalse(player1.findACure());
		assertTrue(player1.getDisease().isRemedy());

	}

	@Test
	public void TestTreatDisease() {
		assertFalse(player1.getDisease().isRemedy());
		assertFalse(player1.treatADisease());
		player1.getDisease().setRemedy(true);
		assertTrue(player1.treatADisease());
	}

	@Test
	public void TestPassAndMaxAction() {
		assertTrue(player1.pass());
		assertTrue(player1.pass());
		assertTrue(player1.pass());
		assertTrue(player1.pass());
		assertFalse(player1.pass());
	}

}
