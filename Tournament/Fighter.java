package Abstract;

import java.util.Objects;

public class Fighter extends Character {
	private double grit;

	public Fighter(String name, int level, double grit) {
		super(name, level);
		this.grit = grit;
	}
	
	public int getStrength() {
		return (int)Math.round(getLevel() * grit);
	}

	public double getGrit() {
		return grit;
	}

	public void setGrit(double grit) {
		this.grit = grit;
	}
	
	public String toString() {
		return super.toString() + " fighter with " + grit + " grit";
	}
	
	public int compareTo(Character other) {
		if(other instanceof Rogue) {
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
		Fighter other = (Fighter) obj;
		return Double.doubleToLongBits(grit) == Double.doubleToLongBits(other.grit);
	}
	
	
}
