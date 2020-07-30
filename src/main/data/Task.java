package data;

import java.util.List;

public class Task {

	private int processingTime;
	private int numberOfCompagnons;
	private String id;
	private String usedMachines;
	private String regroupement;
	private String professionNeeded;
	private List<String> areaUsed;
	private List<String> areaExcluded;
	private List<String> successors;

	private Task(Builder builder) {
		this.processingTime = builder.processingTime;
		this.numberOfCompagnons = builder.numberOfCompagnons;
		this.id = builder.id;
		this.regroupement = builder.regroupement;
		this.professionNeeded = builder.professionNeeded;
	}

	public static Builder newTask() {
		return new Builder();
	}

	public String getId() {
		return id;
	}

	public void setUsedMachines(String usedMachines) {
		this.usedMachines = usedMachines;
	}

	public void setAreaUsed(List<String> areaUsed) {
		this.areaUsed = areaUsed;
	}

	public void setAreaExcluded(List<String> areaExcluded) {
		this.areaExcluded = areaExcluded;
	}

	public void setSuccessors(List<String> successors) {
		this.successors = successors;
	}

	public static final class Builder {
		private int processingTime;
		private int numberOfCompagnons;
		private String id;
		private String regroupement;
		private String professionNeeded;

		private Builder() {
		}

		public Task build() {
			return new Task(this);
		}

		public Builder processingTime(int processingTime) {
			this.processingTime = processingTime;
			return this;
		}

		public Builder numberOfCompagnons(int numberOfCompagnons) {
			this.numberOfCompagnons = numberOfCompagnons;
			return this;
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder regroupement(String regroupement) {
			this.regroupement = regroupement;
			return this;
		}

		public Builder professionNeeded(String professionNeeded) {
			this.professionNeeded = professionNeeded;
			return this;
		}
	}
}