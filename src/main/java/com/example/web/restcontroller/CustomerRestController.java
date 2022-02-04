package com.example.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CustomerCriteria;
import com.example.dto.ResponseDto;
import com.example.exception.ErrorException;
import com.example.service.CustomerService;
import com.example.utils.SessionUtils;
import com.example.vo.Customer;

@RestController
public class CustomerRestController {
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/customer/findIdPassword")
	public ResponseDto<Customer> findIdPassword(CustomerCriteria criteria) {
		ResponseDto<Customer> response = new ResponseDto<Customer>();
		
		Customer customer = customerService.findIdPassword(criteria);
		
		if ("id".equals(criteria.getFind())) {
			String id = customer.getId();
			String partiallyEncryptedId = id.substring(0, id.length() - 2) + "**";
			
			Customer customerToSend = Customer.builder()
					.id(partiallyEncryptedId)
					.createdDate(customer.getCreatedDate())
					.build();
			
			response.setStatus(true);
			response.setItems(customerToSend);
			
			return response;
		}
		response.setStatus(true);
		
		return response;
	}
	
	@PostMapping("/customer/resetPassword")
	public ResponseDto<Customer> restPassword(String id, String newPassword) {
		ResponseDto<Customer> response = new ResponseDto<Customer>();
		
		if (SessionUtils.getAttribute("CUSTOMER_ID") == null) {
			throw new ErrorException("잘못된 접근입니다.");
		}
		
		customerService.updatePassword(id, newPassword);
		SessionUtils.sessionInvalidate();
		return response;
	}
}
