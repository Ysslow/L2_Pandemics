package pandemics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Game {
	private ArrayList<Player> players;
	private Stack<InfectionCard> infectionCards;
	private Stack<TypicalCard> playerCards;
	private Stack<InfectionCard> infDef;
	private Stack<TypicalCard> typDef;
	private ClassicalBoard cBoard;
	private ArrayList<Disease> diseases;
	private boolean end;

	public Game(String map) throws FileNotFoundException {
		this.players = new ArrayList<>();
		this.infectionCards = new Stack<>();
		this.infDef = new Stack<>();
		this.typDef = new Stack<>();
		this.playerCards = new Stack<>();
		this.diseases = new ArrayList<>();
		this.cBoard = new ClassicalBoard(map);
		this.end = false;
		ClassicalBoard.SetMAXACTION(4);

		this.initDis();
		this.initPlayers();
		this.initPaquets();

	}

	private void playerPhase(Player p) {
		System.out.println("PHASE D'ACTION\n");
		for (int i = 0; i < 4; i++) {

			System.out.println("    " + "Action : " + (i + 1));
			int alea = (int) Math.round(Math.random() * 4);

			switch (alea) {
			case 0:
				System.out.println("    " + p + " ne fait rien ");
				break;
			case 1:
				System.out.println("    " + p + " construit un centre de recherche");
				p.build(typDef);
				break;
			case 2:
				System.out.println("    " + p + " trouve un remede");
				p.findACure();
				break;
			case 3:
				System.out.println("    " + p + " traite la maladie");
				p.treatADisease();
				break;
			case 4:
				int alea2 = (int) Math.round(Math.random() * (p.getCity().getNeig().size() - 1));
				int aleaGlobTrot = (int) Math.round(Math.random() * (this.cBoard.getCities().size() - 1));
				System.out.println("    " + "Liste des voisins");
				System.out.println("    " + p.getCity().getNeighbors());
				System.out.print("    " + p + " move from " + p.getCity().getCityName());
				if (p instanceof Globetrotter) {
					p.move(this.cBoard.getCities().get(aleaGlobTrot), this.diseases);
				} else {
					p.move(p.getCity().getNeig().get(alea2), this.diseases);
				}
				System.out.println(" on " + p.getCity().getCityName());
				break;
			}
		}
		p.setNbAction(0);
	}

	private void drawPhase(Player p) {
		System.out.println("PHASE DE PIOCHE\n");

		p.piocherTc(2, this);

	}

	private void spreadingPhase(Player p) {
		System.out.println("PHASE DE PROPAGATION\n");

		p.piocherIc(this);

	}

	public void play() {

		boolean end = false;
		int boucle = 1;
		while (boucle <= 5 && !end) {
			System.out.println("----------------------" + "TOUR " + boucle + "----------------------");
			for (Player p : this.players) {
				System.out.println("\n");
				this.playerPhase(p);
				this.drawPhase(p);
				this.spreadingPhase(p);
			}
			boucle++;
		}
	}

	private void initPlayers() {
		int randVal = (int) Math.round((Math.random() * this.cBoard.cities.size()));
		City randCity = this.cBoard.cities.get(randVal);

		Player p1 = new Globetrotter("P1", randCity, this.diseases.get(randCity.getSector()));
		Player p2 = new Scientist("P2", randCity, this.diseases.get(randCity.getSector()));
		Player p3 = new Expert("P3", randCity, this.diseases.get(randCity.getSector()));
		Player p4 = new Doctor("P4", randCity, this.diseases.get(randCity.getSector()));

		this.players.add(p1);
		this.players.add(p2);
		this.players.add(p3);
		this.players.add(p4);
	}

	private void initDis() {
		Disease d1 = new Disease("Maladie 1");
		Disease d2 = new Disease("Maladie 2");
		Disease d3 = new Disease("Maladie 3");
		Disease d4 = new Disease("Maladie 4");

		this.diseases.add(d1);
		this.diseases.add(d2);
		this.diseases.add(d3);
		this.diseases.add(d4);
	}

	private void initInfCards() {
		for (City city : cBoard.getCities()) {
			this.infectionCards.push(new InfectionCard(city, this.diseases.get(city.getSector())));
		}

		Collections.shuffle(infectionCards);
	}

	private void insertPandCard() {
		EpidemicCard ec1 = new EpidemicCard();
		EpidemicCard ec2 = new EpidemicCard();
		EpidemicCard ec3 = new EpidemicCard();
		EpidemicCard ec4 = new EpidemicCard();

		this.playerCards.push(ec1);
		this.playerCards.push(ec2);
		this.playerCards.push(ec3);
		this.playerCards.push(ec4);
	}

	private void initPlayersCards() {
		for (City city : cBoard.getCities()) {
			this.playerCards.push(new PlayerCard(city, this.diseases.get(city.getSector())));
		}

		Collections.shuffle(playerCards);
		for (int i = 0; i < this.players.size(); i++) {
			this.players.get(i).piocherTc(2, this);
		}
//		this.distribCards();
		this.insertPandCard();
		Collections.shuffle(playerCards);
	}

	private void initPaquets() {
		this.initInfCards();
		this.initPlayersCards();

	}

	/**
	 * distribute card to the list of player
	 */
//	private void distribCards() {
//		if (players.size() == 2) {
//			for (int i = 0; i < this.players.size(); i++) {
//				this.players.get(i).piocherTc(4, this);
//			}
//		} else if (players.size() == 3) {
//			for (int i = 0; i < this.players.size(); i++) {
//				this.players.get(i).piocherTc(3, this);
//			}
//		} else if (playerCards.size() == 4) {
//			for (int i = 0; i < this.players.size(); i++) {
//				this.players.get(i).piocherTc(2, this);
//			}
//		}
//	}

	/**
	 * Print the current stack of Typical Card
	 */
	public void PrintTypicalStack() {
		if (this.playerCards.empty())
			return;
		TypicalCard x = this.playerCards.peek();
		this.playerCards.pop();
		System.out.print(x + "\n ");
		PrintTypicalStack();
		this.playerCards.push(x);
	}

	/**
	 * Print the current stack of Infection card
	 */
	public void PrintInfectionStack() {
		if (this.infectionCards.empty())
			return;
		InfectionCard x = this.infectionCards.peek();
		this.infectionCards.pop();
		System.out.print(x + "\n ");
		PrintInfectionStack();
		this.infectionCards.push(x);
	}

	/**
	 * getter for the Player
	 * 
	 * @return ArrayList<Player>
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public boolean getEnd() {
		return this.end;
	}

	public void setEnd(boolean val) {
		this.end = val;
	}

	/**
	 * setter for the Player
	 * 
	 * @param ArrayList<Player>
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public Stack<InfectionCard> getInfDef() {
		return this.infDef;
	}

	public void setInfDef(Stack<InfectionCard> infectionCards) {
		this.infDef = infectionCards;
	}

	public Stack<TypicalCard> getTypDef() {
		return this.typDef;
	}

	public void setTypDef(Stack<TypicalCard> typCard) {
		this.typDef = typCard;
	}

	/**
	 * getter for the InfectionCards
	 * 
	 * @return Stack<InfectionCard>
	 */
	public Stack<InfectionCard> getInfectionCards() {
		return infectionCards;
	}

	/**
	 * setter for the infectionCards
	 * 
	 * @param Stack<InfectionCard>
	 */
	public void setInfectionCards(Stack<InfectionCard> infectionCards) {
		this.infectionCards = infectionCards;
	}

	/**
	 * getter for the playerCards
	 * 
	 * @return Stack<TypicalCard>
	 */
	public Stack<TypicalCard> getPlayerCards() {
		return playerCards;
	}

	/**
	 * setter for the playerCards
	 * 
	 * @param Stack<TypicalCard>
	 */
	public void setPlayerCards(Stack<TypicalCard> playerCards) {
		this.playerCards = playerCards;
	}

	/**
	 * getter for the cBoard
	 * 
	 * @return ClassicalBoard
	 */
	public ClassicalBoard getcBoard() {
		return cBoard;
	}

	/**
	 * setter for the cBoard
	 * 
	 * @param ClassicalBoard
	 */
	public void setcBoard(ClassicalBoard cBoard) {
		this.cBoard = cBoard;
	}

	/**
	 * getter for the diseases
	 * 
	 * @return ArrayList<Disease>
	 */
	public ArrayList<Disease> getDiseases() {
		return diseases;
	}

	/**
	 * setter for the diseases
	 * 
	 * @param ArrayList<Disease>
	 */
	public void setDiseases(ArrayList<Disease> diseases) {
		this.diseases = diseases;
	}

}
