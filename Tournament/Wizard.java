package Abstract;

import java.util.Objects;

public class Wizard extends Character {
	private int knownSpells;

	public Wizard(String name, int level, int knownSpells) {
		super(name, level);
		this.knownSpells = knownSpells;
	}
	
	public int getStrength() {
		return getLevel()/2 + knownSpells*2; 
	}

	public int getKnownSpells() {
		return knownSpells;
	}

	public void setKnownSpells(int knownSpells) {
		this.knownSpells = knownSpells;
	}
	
	public String toString() {
		return super.toString() + " wizard with " + knownSpells + " known spells";
	}
	
	public int compareTo(Character other) {
		if(other instanceof Fighter) {
			return getStrength()*2 - other.getStrength();
		} else {
			return getStrength() - other.getStrength();
		}
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wizard other = (Wizard) obj;
		return knownSpells == other.knownSpells;
	}
	
	
}
