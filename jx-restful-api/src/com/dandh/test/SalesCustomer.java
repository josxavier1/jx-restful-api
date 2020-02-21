package com.dandh.test;

import java.util.List;

public class SalesCustomer {

	private String customerId;

	private List<CustomerPreference> customerPreferenceList;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public List<CustomerPreference> getCustomerPreferenceList() {
		return customerPreferenceList;
	}

	public void setCustomerPreferenceList(List<CustomerPreference> customerPreferenceList) {
		this.customerPreferenceList = customerPreferenceList;
	}
	
	

}
