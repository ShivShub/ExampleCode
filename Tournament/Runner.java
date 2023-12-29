package Abstract;

import java.util.Scanner;

public class Runner {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter name of tournament: ");
		String filename = scan.nextLine();
		
		Tournament tourney = new Tournament(filename);
		
		System.out.println("Do you want to add your own character? (Y/N)");
		String response = scan.nextLine();
		
		while(response.equalsIgnoreCase("Y")) {
			try {
				System.out.println("Enter the character's name: ");
				String name = scan.nextLine();
				System.out.println("Enter the character's class (Fighter, Rogue, Wizard, or Commoner): ");
				String playerClass = scan.nextLine();
				System.out.println("Enter the character's level: ");
				int level = scan.nextInt();
				if(playerClass.equals("Fighter")) {
					System.out.println("Enter your fighter's grit: ");
					double grit = scan.nextDouble();
					tourney.addCompetitor(new Fighter(name, level, grit));
				} else if(playerClass.equals("Rogue")) {
					System.out.println("Enter your rogue's stealth: ");
					int stealth = scan.nextInt();
					tourney.addCompetitor(new Rogue(name, level, stealth));
				} else if(playerClass.equals("Wizard")) {
					System.out.println("Enter your wizard's number of known spells: ");
					int knownSpells = scan.nextInt();
					
					tourney.addCompetitor(new Wizard(name, level, knownSpells));
				} else if(playerClass.equals("Commoner")) {
					System.out.println("Is your commoner determined? (true/false)");
					boolean determined = scan.nextBoolean();
					tourney.addCompetitor(new Commoner(name, level, determined));
				} else {
					System.out.println("Invalid character.");
				}
				scan.nextLine();
			} catch(Exception e) {
				System.out.println("Invalid character.");
				scan.nextLine();
			}
			System.out.println("Enter another character? (Y/N)");
			response = scan.nextLine();
		}
		System.out.println("Our contestants are:");
		for(Character c : tourney.getCompetitors()) {
			System.out.println(c);
		}
		
		System.out.println("Let the tournament begin!");
		Character victor = tourney.findVictor();
		System.out.println("And the winner is... " + victor.toString() + "!");
	}
}
