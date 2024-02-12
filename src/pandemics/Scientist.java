package pandemics;

import java.util.ArrayList;

/** Scientist Class */
public class Scientist extends Player{
	
	/**
	 * Scientist first constructor
	 * 
	 * @param PlayerName the name of the player
	 * @param city the city where the player is
	 * @param disease the disease of the city
	 */
	public Scientist(String PlayerName, City city, Disease disease) {
		super(PlayerName, city, disease);
	}
	
	/**
	 * Find the cure of the disease 
	 * 
	 * @return True if the cure was able to be find, false otherwise
	 */
	public boolean findACure(){
		  if(this.NbCardsToFindCure > 4) {
			  this.NbCardsToFindCure = 4;
		  }
		  int cardCount = 0;
	      ArrayList<PlayerCard> matchingCards = new ArrayList<>();
	      
	      if (!this.disease.isRemedy() && this.nbAction != ClassicalBoard.MAXACTION && this.city.isResearch()) {
	          for (PlayerCard card : this.playersHand) {
	              if (card.getDis().getDiseaseName() == this.disease.getDiseaseName() && cardCount!= this.NbCardsToFindCure) {
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
	    }
