package factory;

import java.util.ArrayList;

public class Compagnon {
	
	/**Represents a human resource called "Compagnon"*/
	
	int ID, type_h;
	String profession, equipe;
	
	public Compagnon(int iD, int type_h, String profession, String team) {
		super();
		ID = iD;
		this.type_h = type_h;
		this.profession = profession;
		this.equipe = team;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getType_h() {
		return type_h;
	}

	public void setType_h(int type_h) {
		this.type_h = type_h;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getEquipe() {
		return equipe;
	}

	public void setEquipe(String team) {
		this.equipe = team;
	}
	
	
	
}
