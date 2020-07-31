package data;

/**
 * Represents a human resource called "Compagnon"
 */
public class Compagnon {
	
	private int id;
	private int typeH;
	private String profession;
	private String equipe;

	public Compagnon setId(int id) {
		this.id = id;
		return this;
	}

	public Compagnon setTypeH(int typeH) {
		this.typeH = typeH;
		return this;
	}

	public Compagnon setProfession(String profession) {
		this.profession = profession;
		return this;
	}

	public Compagnon setEquipe(String equipe) {
		this.equipe = equipe;
		return this;
	}

	public int getId() {
		return id;
	}
}
