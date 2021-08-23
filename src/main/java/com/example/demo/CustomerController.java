package com.example.demo;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController // This means that this class is a Controller
public class CustomerController {

	private final CustomerRepository customerRepository;
	

	  CustomerController(CustomerRepository repository) {
	    this.customerRepository = repository;
	  }
	//Create Customer
	 @PostMapping("/customers")
	 Customer newCustomer(@RequestBody Customer newCustomer) {
	    return customerRepository.save(newCustomer);
	  }

	// Get a Single Customer
	@GetMapping("/customers/{id}")
	Customer one(@PathVariable(value = "id")Long id)  throws CustomerNotFoundException{
	    
	    return customerRepository.findById(id)
	      .orElseThrow(() -> new CustomerNotFoundException(id));
	  }

	// Update a Customer
	@PutMapping("/customers/{id}")
	  Customer updateCustomer(@RequestBody Customer customerDetails, @PathVariable(value = "id") Long id) {
	    
	    return customerRepository.findById(id)
	      .map(customer -> {
	    	  customer.setName(customerDetails.getName());
	    	  customer.setPhone(customerDetails.getPhone());
	        return customerRepository.save(customer);
	      })
	      .orElseGet(() -> {
	    	  customerDetails.setId(id);
	        return customerRepository.save(customerDetails);
	      });
	  }

	// Delete a Customer
	@DeleteMapping("/customers/{id}")
	  void deleteCustomer(@PathVariable Long id) {
		customerRepository.deleteById(id);
	  }

	// Aggregate root
	  // tag::get-aggregate-root[]
	  @GetMapping("/customers")
	  List<Customer> all() {
	    return (List<Customer>) customerRepository.findAll();
	  }
	  // end::get-aggregate-root[]
	/*@GetMapping(path = "/all")
	public @ResponseBody Iterable<Customer> getAllUsers() {
		// This returns a JSON or XML with the users
		return customerRepository.findAll();
	}*/
}
