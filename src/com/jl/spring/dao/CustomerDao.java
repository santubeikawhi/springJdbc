package com.jl.spring.dao;

import com.jl.spring.model.Customer;

public interface CustomerDao {
	public void insert(Customer customer);
	public Customer findCustomerById(String id);
}
