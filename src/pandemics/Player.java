package pandemics;

import java.util.ArrayList;
import java.util.Stack;

/* Player Class*/
public class Player {

	protected String PlayerName;
	protected City city;
	protected Disease disease;
	protected int nbAction;
	protected ArrayList<PlayerCard> playersHand;
	protected int NbCardsToFindCure = 3;

	/**
	 * Player constructor
	 * 
	 * @param PlayerName the name of the player
	 * @param city       the city where the player is
	 * @param disease    the disease of the city
	 */
	public Player(String PlayerName, City city, Disease disease) {
		this.PlayerName = PlayerName;
		this.city = city;
		this.disease = disease;
		this.playersHand = new ArrayList<>();
		this.nbAction = 0;
	}

	/**
	 * Player constructor
	 * 
	 * @param PlayerName the name of the player
	 * @param city       the city where the player is
	 * @param disease    the disease of the city
	 */
	public Player(String PlayerName, City city, Disease disease, ArrayList<PlayerCard> playerCards) {
		this.PlayerName = PlayerName;
		this.city = city;
		this.disease = disease;
		this.playersHand = playerCards;
		this.nbAction = 0;
	}

	public void setNbAction(int val) {
		this.nbAction = val;
	}

	/**
	 * Return the name of the player
	 * 
	 * @return the player name
	 */
	public String getPlayerName() {
		return PlayerName;
	}

	/**
	 * Set a new name for the player
	 * 
	 * @param playerName the new name
	 */
	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}

	/**
	 * Return the disease of the city
	 * 
	 * @return the Disease's city
	 */
	public Disease getDisease() {
		return disease;
	}

	/**
	 * va etre modifier après car disease ne se gère pas ici
	 * 
	 * 
	 * @param disease
	 */
	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	/**
	 * Return the city where the player is
	 * 
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * Set a new city for the position where the player is
	 * 
	 * @param city the new city
	 */
	public void setCity(City city) {
		this.city = city;
	}

	public ArrayList<PlayerCard> getPlayersHand() {
		return playersHand;
	}

	public void setPlayersHand(ArrayList<PlayerCard> playersHand) {
		this.playersHand = playersHand;
	}

	/**
	 * Moves the player to a another city
	 * 
	 * @param c the new city where the player is going
	 * @return True if the player was able to move, false otherwise
	 */
	public boolean move(City c, ArrayList<Disease> arr) {
		if (this.city.isNeighbors(c) && this.nbAction <= ClassicalBoard.MAXACTION) {
			this.setCity(c);
			this.disease = arr.get(c.getSector());
			this.nbAction++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Build a station in the city
	 * 
	 * @return True if the player was able to build, false otherwise
	 */
	public boolean build(Stack<TypicalCard> def) {
		if (!this.city.isResearch() && this.nbAction != ClassicalBoard.MAXACTION) {
			for (PlayerCard card : this.playersHand) {
				if (card.getCity().getCityName() == this.city.getCityName()) {
					this.city.setResearch(true);
					this.playersHand.remove(card);
					def.push(card);
					this.nbAction++;
					System.out.println("    Station construite");
					return true;
				}
			}
		}
		System.out.println("    Erreur de construction");
		return false;
	}

	/**
	 * Find the cure of the disease
	 * 
	 * @return True if the cure was able to be find, false otherwise
	 */
	public boolean findACure() {
		int cardCount = 0;
		ArrayList<PlayerCard> matchingCards = new ArrayList<>();

		if (!this.disease.isRemedy() && this.nbAction != ClassicalBoard.MAXACTION && this.city.isResearch()) {
			for (PlayerCard card : this.playersHand) {
				if (card.getDis().getDiseaseName() == this.disease.getDiseaseName() && cardCount != NbCardsToFindCure) {
					matchingCards.add(card);
					cardCount++;
				}
			}
			if (cardCount == NbCardsToFindCure) {
				this.playersHand.removeAll(matchingCards);
				this.disease.setRemedy(true);
				this.nbAction++;
				System.out.println("    Remede decouvert");
				return true;
			}
		}
		System.out.println("    Remede non decouvert");
		return false;
	}

	/**
	 * Treat the disease
	 * 
	 * @return True if the Disease was able to be treat, false otherwise
	 */
	public boolean treatADisease() {
		boolean erad = true;
		if (this.disease.isRemedy() && !this.disease.isEradication() && this.nbAction != ClassicalBoard.MAXACTION) {
			
			for (City c : this.city.getNeig()) {
				if (c.getInfection() != 0) {
					 erad = false;
				}
			}
			if (!erad) {
				this.city.setInfection(this.city.getInfection() - 1);
			}
			this.disease.setEradication(erad);
			this.nbAction++;
			System.out.println("    Maladie traite");
			return true;
		} else {
			System.out.println("    Maladie traite non");
			return false;
		}
	}

	/**
	 * pass the player's turn so he does nothing during this action
	 * 
	 * @return True if the player was able to pass, false otherwise
	 */
	public boolean pass() {
		if (this.nbAction != ClassicalBoard.MAXACTION) {
			this.nbAction++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method allows the player to draw a specified number of typical cards
	 * from the typical card stack
	 * 
	 * @param tc    The typical card stack to draw cards from
	 * @param nb    The number of cards to draw
	 * @param defTc The discard stack for typical cards
	 * @param cb    The classical board of the game
	 * @param ic    The infection card stack of the game
	 * @param defIc The discard stack for infection cards
	 */
	public void piocherTc(int nb, Game g) {
		TypicalCard oue;
		if (nb < g.getPlayerCards().size()) {
			System.out.println("    main du joueur avant pioche :");
			for (PlayerCard playerCard : playersHand) {
				System.out.print(" " + playerCard.toString());
			}
			System.out.println("");

			if ((playersHand.size() + nb) > 7) {
				System.out.println("    Le joueur a trop de carte il doit en defausser");
				for (int i = 0; i < playersHand.size() - nb; i++) {
					g.getTypDef().add((TypicalCard) this.playersHand.remove(i));
				}
				for (int i = 0; i < nb; i++) {
					oue = g.getPlayerCards().pop();
					if (oue instanceof PlayerCard) {
						this.playersHand.add((PlayerCard) oue);
					} else {
						oue = (EpidemicCard) oue;
						oue.action(g.getInfectionCards(), g.getcBoard(), g.getInfDef());
						g.getTypDef().push(oue);
					}
				}
			} else {
				for (int i = 0; i < nb; i++) {
					oue = g.getPlayerCards().pop();
					if (oue instanceof PlayerCard) {
						this.playersHand.add((PlayerCard) oue);
					} else {
						oue = (EpidemicCard) oue;
						System.out.println(oue.equals(g.getcBoard().getCities().get(i)));
						oue.action(g.getInfectionCards(), g.getcBoard(), g.getInfDef());
						g.getTypDef().push(oue);
					}
				}
			}
			System.out.println("    main du joueur après pioche :");
			for (PlayerCard playerCard : playersHand) {
				System.out.print(" " + playerCard.toString());
			}
			System.out.println("");
		}

		else {
			g.setEnd(true);
			System.out
					.println("    " + this.PlayerName + " ne peut pas piocher pas assez de carte restante Fin du jeu");
		}
	}

	/**
	 * This method allows the player to draw a specified number of infection cards
	 * from the infection card stack
	 * 
	 * @param ic    The infection card stack to draw cards from
	 * @param tgi   The number of cards to draw
	 * @param defIc The discard stack for infection cards
	 */
	public void piocherIc(Game g) {
		if (g.getInfectionCards().size() < g.getcBoard().getGlobInfRate()) {
			g.setEnd(true);
		} else {
			InfectionCard ifCard;
			City tkt;
			System.out.println();
			for (int i = 0; i < g.getcBoard().getGlobInfRate(); i++) {

				ifCard = g.getInfectionCards().pop();

				System.out.println("    " + ifCard.getCity().getCityName() + " se fait infecter ");
				System.out.println("    Nb cubes initiale : " + ifCard.getCity().getInfection());
				ifCard.action();
				System.out.println("    Nb cubes après : " + ifCard.getCity().getInfection());
				g.getInfDef().push(ifCard);
			}
		}

	}

	public String toString() {
		return this.PlayerName;
	}
}
