//Author:Samarth 
package com.erail.models;

public class PasswordRule {

	private String ruleName;
	private long ruleValue;
	private long status;
	private String ruleDes;

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public long getRuleValue() {
		return ruleValue;
	}

	public void setRuleValue(long ruleValue) {
		this.ruleValue = ruleValue;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public String getRuleDes() {
		return ruleDes;
	}

	public void setRuleDes(String ruleDes) {
		this.ruleDes = ruleDes;
	}

}
