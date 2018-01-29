package com.jl.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.jl.spring.model.Customer;

public class JdbcCustomerDao implements CustomerDao{

	private DataSource dataSource;

	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Customer customer) {
		String sql = "insert into sys.Customer (CUST_ID, NAME_, AGE_) values (?,?,?)";
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,customer.custId );
			ps.setString(2, customer.name);
			ps.setInt(3, customer.age);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public Customer findCustomerById(String id) {
		String sql = "select * from sys.Customer t where t.cust_id = ?";
		Connection conn = null;
		Customer customer = new Customer();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				customer = new Customer(rs.getString("CUST_ID"),rs.getString("NAME_"),rs.getInt("AGE_"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			customer = null;
		} finally {
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return customer;
	}

}
