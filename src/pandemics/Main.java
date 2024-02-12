package pandemics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
//		ClassicalBoard classicalBoard = new ClassicalBoard(args[0]);
		

		Game newGame = new Game("Map.json");

		newGame.play();

//		System.out.println("\n---------------TYPICAL STACK---------------\n");
//		newGame.PrintTypicalStack();
//		System.out.println("\n---------------INFECTION STACK---------------\n");
//		newGame.PrintInfectionStack();
//		
//		System.out.println("\n---------------CLASSICAL BOARD---------------\n");
//		System.out.println(classicalBoard.toString());
	}

}
