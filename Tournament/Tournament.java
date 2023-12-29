package Abstract;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tournament {
	private ArrayList<Character> competitors;
	
	public Tournament(String fileName) {
		try {
			File file = new File(fileName);
			Scanner scan = new Scanner(file);
			
			competitors = new ArrayList<Character>();
			while(scan.hasNextLine()) {
				String name = scan.next();
				String playerClass = scan.next();
				int level = scan.nextInt();
				if(playerClass.equals("Fighter")) {
					double grit = scan.nextDouble();
					competitors.add(new Fighter(name, level, grit));
				} else if(playerClass.equals("Rogue")) {
					int stealth = scan.nextInt();
					competitors.add(new Rogue(name, level, stealth));
				} else if(playerClass.equals("Wizard")) {
					int spellsKnown = scan.nextInt();
					competitors.add(new Wizard(name, level, spellsKnown));
				} else if(playerClass.equals("Commoner")) {
					boolean determined = scan.nextBoolean();
					competitors.add(new Commoner(name, level, determined));
				}
			}
		} catch(IOException e) {
			System.out.println("File not found.");
		}
	}
	
	public void addCompetitor(Character newcomer) {
		competitors.add(newcomer);
	}
	
	public Character findVictor() {
		Character winner = competitors.get(0);
		for(Character c : competitors) {
			if(c.compareTo(winner) > 0) {
				winner = c;
			}
		}
		return winner;
	}

	public ArrayList<Character> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(ArrayList<Character> competitors) {
		this.competitors = competitors;
	}
	
	public String toString() {
		String result = "This a tournament between the following characters: ";
		for(Character c : competitors) {
			result += c.toString() + "\n";
		}
		return result;
	}
}
