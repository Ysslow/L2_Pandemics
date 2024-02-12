package pandemics;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

public class EpidemicCard extends TypicalCard {

	/**
	 * Constructor EpidemicCard (à completer après avancement de Card.java)
	 * 
	 */
	public EpidemicCard() {
		super();
	}

	public void action(Stack<InfectionCard> ic, ClassicalBoard cb, Stack<InfectionCard> defIc) {
		InfectionCard oueCard = ic.pop();

		// augmentation du taux global
		cb.incGlobInfRate();

		// phase d'infection sur une carte de la pile de carte infection
//		cb.getCities().get(cb.getCities().indexOf(oueCard.getCity())).addInfection();
		oueCard.getCity().addInfection();

		// ajout de la pile defausse des cartes infection(melangé) a la pile de base
		defIc.push(oueCard);
		Collections.shuffle(defIc);
		for (int i = 0; i < defIc.size(); i++) {
			ic.push(defIc.pop());
		}

	}

	@Override
	public String toString() {
		return super.toString() + "EpidemicCard [ " + "]";
	}
}
