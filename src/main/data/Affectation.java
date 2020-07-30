package data;

public class Affectation {

	/**
	 * Represents the couple compagnon/task and 
	 * at which time unit the task has been started
	 * by this specific compagnon
	 */
	
	private int compagnonId;
	private int start;
	private int end;
	private String taskId;

	public int getEnd() {
		return end;
	}

	public String getTaskId() {
		return taskId;
	}

	public int getStart() {
		return start;
	}

	public Affectation setCompagnonId(int compagnonId) {
		this.compagnonId = compagnonId;
		return this;
	}

	public Affectation setStart(int start) {
		this.start = start;
		return this;
	}

	public Affectation setEnd(int end) {
		this.end = end;
		return this;
	}

	public Affectation setTaskId(String taskId) {
		this.taskId = taskId;
		return this;
	}
}
