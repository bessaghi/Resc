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

	private Affectation(Builder builder) {
		this.compagnonId = builder.compagnonId;
		this.start = builder.start;
		this.end = builder.end;
		this.taskId = builder.taskId;
	}

	public static Builder newAffectation() {
		return new Builder();
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


	public static final class Builder {
		private int compagnonId;
		private int start;
		private int end;
		private String taskId;

		private Builder() {
		}

		public Affectation build() {
			return new Affectation(this);
		}

		public Builder compagnonId(int compagnonId) {
			this.compagnonId = compagnonId;
			return this;
		}

		public Builder start(int start) {
			this.start = start;
			return this;
		}

		public Builder end(int end) {
			this.end = end;
			return this;
		}

		public Builder taskId(String taskId) {
			this.taskId = taskId;
			return this;
		}
	}
}
