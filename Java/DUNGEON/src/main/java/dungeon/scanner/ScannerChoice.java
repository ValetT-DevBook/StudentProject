package dungeon.scanner;

import java.util.*;

import dungeon.AdventureGame;
import dungeon.actions.Action;
import dungeon.actions.AllActions;
import dungeon.characters.*;
import dungeon.items.*;
import dungeon.rooms.*;;

public class ScannerChoice<T> {
	private Scanner scanner;
	private List<T> list;

	// BUILDER

	public ScannerChoice(List<T> l){
		this.scanner = new Scanner(System.in);
		this.list = l;
	}

	/**
	 * read an integer from 0 (included) to n (excluded) from standard input
	 * input is repeated as long as it is not correct
	 * 
	 * @return the valid read input 
	 */
	public T readChoice() {
		String input;
		T result = (T) null;
		boolean quit = false;
		
		while (!quit) {
			System.out.println("What's your choice ?");
			this.displayChoice();
			try {
				input = this.scanner.nextLine().toLowerCase();
				for (T element : this.list) {
					if (element.toString().toLowerCase().equals(input)){
						result = element;
						quit = true;
					}
				}
				if ((input.equals("back")))
					quit = true;
			} catch (InputMismatchException	 e){
				// consume the input
				this.scanner.skip(".*");
			}
		} 

		return result;
	}

	/**
	 * Display the differents choice
	 */
	private void displayChoice(){
		for (int i = 0; i < this.list.size(); i++)
			System.out.println("\t - " + this.list.get(i).toString() );
		if (!(this.list.get(0) instanceof Action))
			System.out.println("\t - Back");
	}

	
	public static void main(String[] args) {

		// TEST OF SCANNER DIRECTION

		List<Direction> dir = new ArrayList<Direction>();
		dir.add(Direction.SOUTH); 
		dir.add(Direction.WEST); 

		ScannerChoice<Direction> scan = new ScannerChoice<Direction>(dir);
		Direction d = scan.readChoice();
		if (d == null)
			System.out.println("You cancel the action.");
		else
			System.out.println("you typed : " + d.toString());

			
		// TEST OF SCANNER MONSTERS

		List<Monsters> mons = new ArrayList<Monsters>();
		AdventureGame game = new AdventureGame(new Room(false));
		for (int i = 0; i < 5; i++) {
			mons.add(AllMonsters.randomMonsters().createNewMonsters(game));
		}
		
		ScannerChoice<Monsters> scan2 = new ScannerChoice<Monsters>(mons);
		Monsters m = scan2.readChoice();
		
		if (m == null)
			System.out.println("You cancel the action.");
		else
			System.out.println("your monster : " + m.toString());	


		// TEST OF SCANNER ITEMS

		List<Items> item = new ArrayList<Items>();
		for (int i = 0; i < 6; i++) {
			try {
				Items ite = AllItems.randAllItem().newInstance(5);
				item.add(ite);
			} catch (Exception e) {}
		}

		ScannerChoice<Items> scan3 = new ScannerChoice<Items>(item);
		Items it = scan3.readChoice();

		if (it == null)
			System.out.println("You cancel the action.");
		else
			System.out.println("your item : " + it.toString());	

			
		// TEST OF SCANNER ACTION

		ScannerChoice<Action> scan4 = new ScannerChoice<Action>(AllActions.LIST_ACTIONS);
		Action a = scan4.readChoice();

		if (a == null)
			System.out.println("You cancel the action.");
		else
			System.out.println("your action : " + a.toString());	
	}
}
