package com.jl.spring.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jl.spring.dao.CustomerDao;
import com.jl.spring.model.Customer;

public class Webapp {
	public static void main(String args[]){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Model.xml");
		CustomerDao customerDao = (CustomerDao)applicationContext.getBean("customerDao");
		Customer customer = new Customer("3","ceshi3",23);
		
		//����ͻ�
//		customerDao.insert(customer);
		
		//��ѯ�ͻ�
		Customer findCustomer = customerDao.findCustomerById("3");
		System.out.println(findCustomer.name);
	}
}
