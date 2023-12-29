package Abstract;

import java.util.Objects;

public class Commoner extends Character {
	private boolean determined;

	public Commoner(String name, int level, boolean determined) {
		super(name, level);
		this.determined = determined;
	}
	
	public int getStrength() {
		if(determined) {
			return getLevel() * 20;
		} else {
			return 0;
		}
	}

	public boolean isDetermined() {
		return determined;
	}

	public void setDetermined(boolean determined) {
		this.determined = determined;
	}
	public int compareTo(Character other) {
		return getStrength() - other.getStrength();
	}
	
	public String toString() {
		if(determined) {
			return super.toString() + " commoner, who is very determined";
		} else {
			return super.toString() + " commoner, who's a bit scared";
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
		Commoner other = (Commoner) obj;
		return determined == other.determined;
	}
	
	
	
}
