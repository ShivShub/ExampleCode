package Abstract;

import java.util.Objects;

public class Rogue extends Character {
	private int stealth;

	public Rogue(String name, int level, int stealth) {
		super(name, level);
		this.stealth = stealth;
		// TODO Auto-generated constructor stub
	}
	
	public int getStrength() {
		return getLevel() + stealth;
	}

	public int getStealth() {
		return stealth;
	}

	public void setStealth(int stealth) {
		this.stealth = stealth;
	}
	
	public String toString() {
		return super.toString() + " rogue with " + stealth + " stealth";
	}
	
	public int compareTo(Character other) {
		if(other instanceof Wizard) {
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
		Rogue other = (Rogue) obj;
		return stealth == other.stealth;
	}
	
	
}
