package model;

public class PlanModel {
	private String planDurationDays;
	private String planPrice;
	private String planDescription;
	private String planID;


public PlanModel() {
	
}


public PlanModel( String planDurationDays, String planPrice, String planDescription, String planID) {
	super();
	this.planDurationDays = planDurationDays;
	this.planPrice = planPrice;
	this.planDescription = planDescription;
	this.planID=planID;
}

public String getPlanDurationDays() {
	return planDurationDays;
}


public String getPlanID() {
	return planID;
}


public void setPlanID(String planID) {
	this.planID = planID;
}


public void setPlanDurationDays(String planDurationDays) {
	this.planDurationDays = planDurationDays;
}


public String getPlanPrice() {
	return planPrice;
}


public void setPlanPrice(String planPrice) {
	this.planPrice = planPrice;
}


public String getPlanDescription() {
	return planDescription;
}


public void setPlanDescription(String planDescription) {
	this.planDescription = planDescription;
}


}

