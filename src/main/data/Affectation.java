package data;

public class Affectation {
	
	private int compagnonId;
	private String taskId;
	private int start;
	private int end;

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

	public int getEnd() {
		return end;
	}

	public String getTaskId() {
		return taskId;
	}

	public int getStart() {
		return start;
	}

}
