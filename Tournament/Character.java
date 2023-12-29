package Abstract;

import java.util.Objects;

public abstract class Character implements Comparable<Character>{
	private String name;
	private int level;
	public Character(String name, int level) {
		super();
		this.name = name;
		this.level = level;
	}
	
	public abstract int getStrength();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return name + ", the level " + level;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		return level == other.level && Objects.equals(name, other.name);
	}
	
	
}
