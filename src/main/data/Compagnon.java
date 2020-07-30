package data;

/**
 * Represents a human resource called "Compagnon"
 */
public class Compagnon {
	
	private int id;
	private int typeH;
	private String profession;
	private String equipe;

	private Compagnon(Builder builder) {
		this.id = builder.id;
		this.typeH = builder.typeH;
		this.profession = builder.profession;
		this.equipe = builder.equipe;
	}

	public static Builder newCompagnon() {
		return new Builder();
	}

	public int getId() {
		return this.id;
	}


	public static final class Builder {
		private int id;
		private int typeH;
		private String profession;
		private String equipe;

		private Builder() {
		}

		public Compagnon build() {
			return new Compagnon(this);
		}

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder typeH(int typeH) {
			this.typeH = typeH;
			return this;
		}

		public Builder profession(String profession) {
			this.profession = profession;
			return this;
		}

		public Builder equipe(String equipe) {
			this.equipe = equipe;
			return this;
		}
	}

}
