package com.sensor.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import com.sensor.domain.Alert;

/**
 * This is UnderWeightRule class, used to implement the rule for underweight.
 */
@Rule(name = "underWeightRule")
public class UnderWeightRule{
	
	private Alert alert;
	
	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	/**
	 * This method defines the condition of underweight.
	 */
	@Condition
    public boolean isUnderWeight() {
		return this.alert.getValue() < 0.1 * this.alert.getBaseWeight() ;
    }
	
	/**
	 * This method is executed when above underweight condition satisfies.
	 * On satisfying above condition the indicator is set as Under Weight. 
	 */
	@Action(order = 1)
    public void markAsUnderWeight(){
    	this.alert.setIndicator("Under Weight");
    	System.out.println("Inside markAsUnderWeight...");
    }
	
}
