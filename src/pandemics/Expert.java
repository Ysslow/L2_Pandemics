package pandemics;

/** Expert Class */
public class Expert extends Player{
	
	/**
	 * Expert first constructor
	 * 
	 * @param PlayerName the name of the player
	 * @param city the city where the player is
	 * @param disease the disease of the city
	 */
	public Expert(String PlayerName, City city, Disease disease) {
		super(PlayerName, city, disease);
	}


	
	/**
	 * Build a station in the city without any station card
	 * 
	 * @return True if the player was able to build, false otherwise
	 */
	public boolean build() {
		if (!this.city.isResearch() && this.nbAction != ClassicalBoard.MAXACTION) {
				this.city.setResearch(true);
				this.nbAction++;
				System.out.println("    Station construite");
				return true;
			}
		System.out.println("    Erreur de construction");
		return false;
	}
}
