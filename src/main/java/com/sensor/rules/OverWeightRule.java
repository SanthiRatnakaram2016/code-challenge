package com.sensor.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import com.sensor.domain.Alert;

/**
 * This is OverWeightRule class, used to implement the rule for overweight.
 */
@Rule(name = "overWeightRule")
public class OverWeightRule {
	
	private Alert alert;
	
	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	/**
	 * This method defines the condition of overweight.
	 */
	@Condition
    public boolean isOverWeight() {
		return this.alert.getValue() > 1.10 * alert.getBaseWeight() ;
    }

	
	/**
	 * This method is executed when above overweight condition satisfies.
	 * On satisfying above condition the indicator is set as Over Weight. 
	 */
	@Action(order = 2)
    public void markAsOverWeight(){
		this.alert.setIndicator("Over Weight");
		System.out.println("Inside markAsOverWeight...");
    }
	
}
