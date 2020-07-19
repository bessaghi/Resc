package factory;

public class Affectation {

	/**
	 * Represents the couple compagnon/task and 
	 * at which time unit the task has been started
	 * by this specific compagnon
	 */
	
	int c_id, start, end;
	String t_id;

	public Affectation(String t_id, int c_id, int start, int end) {
		super();
		this.c_id = c_id;
		this.t_id = t_id;
		this.start = start;
		this.end = end;
	}
	
	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public String getT_id() {
		return t_id;
	}

	public void setT_id(String t_id) {
		this.t_id = t_id;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
}
