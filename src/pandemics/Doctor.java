package pandemics;

import java.awt.event.ContainerAdapter;

/** Doctor */
public class Doctor extends Player{

	/**
	 * Doctor first constructor
	 * 
	 * @param PlayerName the name of the player
	 * @param city the city where the player is
	 * @param disease the disease of the city
	 */
	public Doctor(String PlayerName, City city, Disease disease) {
		super(PlayerName, city, disease);
	}

	
	/**
	 * Treat the disease
	 * 
	 * @return True if the Disease was able to be treat, false otherwise
	 */
	@Override 	
	public boolean treatADisease() {
		boolean erad = true;
		if (!this.disease.isEradication() && this.nbAction != ClassicalBoard.MAXACTION) {
			
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
}
