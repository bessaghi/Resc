package factory;

import java.util.ArrayList;

public class Tache {

	int pt, w_uses;										//Processing time and number of workers 
	String ID, m_uses, family, profession;				//ID and task family, machine used, profession needed
	ArrayList<String> a_used, a_excluded, successors;	//Areas used/excluded and successors
	
	public Tache(int pt, int w_uses, String iD, String m_uses, String family, String profession,
			ArrayList<String> a_used, ArrayList<String> a_excluded, ArrayList<String> successors) {
		super();
		this.pt = pt;
		this.w_uses = w_uses;
		ID = iD;
		this.m_uses = m_uses;
		this.family = family;
		this.profession = profession;
		this.a_used = a_used;
		this.a_excluded = a_excluded;
		this.successors = successors;
	}
	
	public int getPt() {
		return pt;
	}
	public void setPt(int pt) {
		this.pt = pt;
	}
	public int getW_uses() {
		return w_uses;
	}
	public void setW_uses(int w_uses) {
		this.w_uses = w_uses;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getM_uses() {
		return m_uses;
	}
	public void setM_uses(String m_uses) {
		this.m_uses = m_uses;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public ArrayList<String> getA_used() {
		return a_used;
	}
	public void setA_used(ArrayList<String> a_used) {
		this.a_used = a_used;
	}
	public ArrayList<String> getA_excluded() {
		return a_excluded;
	}
	public void setA_excluded(ArrayList<String> a_excluded) {
		this.a_excluded = a_excluded;
	}
	public ArrayList<String> getSuccessors() {
		return successors;
	}
	public void setSuccessors(ArrayList<String> successors) {
		this.successors = successors;
	}
	
}