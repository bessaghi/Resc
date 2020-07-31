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

	public Task setProcessingTime(int processingTime) {
		this.processingTime = processingTime;
		return this;
	}

	public Task setNumberOfCompagnons(int numberOfCompagnons) {
		this.numberOfCompagnons = numberOfCompagnons;
		return this;
	}

	public Task setId(String id) {
		this.id = id;
		return this;
	}

	public Task setRegroupement(String regroupement) {
		this.regroupement = regroupement;
		return this;
	}

	public Task setProfessionNeeded(String professionNeeded) {
		this.professionNeeded = professionNeeded;
		return this;
	}

	public Task setUsedMachines(String usedMachines) {
		this.usedMachines = usedMachines;
		return this;
	}

	public Task setAreaUsed(List<String> areaUsed) {
		this.areaUsed = areaUsed;
		return this;
	}

	public Task setAreaExcluded(List<String> areaExcluded) {
		this.areaExcluded = areaExcluded;
		return this;
	}

	public Task setSuccessors(List<String> successors) {
		this.successors = successors;
		return this;
	}

	public String getId() {
		return id;
	}

	public List<String> getSuccessors() {
		return successors;
	}
}