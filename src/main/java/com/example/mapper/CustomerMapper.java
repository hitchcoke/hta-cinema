package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.CustomerCriteria;
import com.example.vo.Customer;

@Mapper
public interface CustomerMapper {
	Customer getCustomerById(String id);
	Customer getCustomerByCriteria(CustomerCriteria criteria);
	Customer updateCustomer(Customer customer);
}
