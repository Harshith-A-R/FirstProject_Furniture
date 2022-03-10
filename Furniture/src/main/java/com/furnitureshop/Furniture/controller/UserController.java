package com.furnitureshop.Furniture.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.sailesh.model.Employee;
import com.furnitureshop.Furniture.Repository.UserRepository;
import com.furnitureshop.Furniture.Response.ServiceResponse;
import com.furnitureshop.Furniture.Service.MailService;
import com.furnitureshop.Furniture.Service.UserService;
import com.furnitureshop.Furniture.model.Furniture;
import com.furnitureshop.Furniture.model.User;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/get")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
//	@GetMapping("/getid")
//	public List<User> getUsersUsingId(@PathVariable("id") int id) {
//		return userRepository.findAll();
//	}
	@PostMapping("/add")
	public User createUser(@RequestBody User user) {
		System.out.println(user);
		return userRepository.save(user);
	}
//	@GetMapping("/deleteuser/{id}" )
//	public User deleteUser(@PathVariable("id") long id) {
//		User user =  userRepository.getOne(id);
//		System.out.println(user);
//		userRepository.deleteById(id);
//		System.out.println(user);
//		return user;
//	}
	
	@GetMapping("/deleteuser/{id}")
	  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
	    try {
	      userRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	@GetMapping("/updateuser/{id}")
	public ServiceResponse updatedata(@PathVariable("id") @RequestBody User data) {
		System.out.println(data);
		Optional<User> user = userRepository.findUserById(data.getId());
		System.out.println(user);
		if (user.isPresent())
		{
			User usr = user.get();
			
			usr.setId(data.getId());
			usr.setName(data.getName());
			usr.setPassword(data.getPassword());
		
			userRepository.save(usr);
			System.out.println(usr);
		}
		return null;
	
	}
	
	@PostMapping("/login")		
	public ServiceResponse createEmployee(@RequestBody User user) {
		
		ServiceResponse response = new ServiceResponse();
		
		
		System.out.println(user);
		
		
		User userObject = userRepository.userLogin(user.getName(), user.getPassword());
		
		System.out.println(userObject);
		
		if(userObject == null)
		{
			response.setMessage("Invalid Credentials");
			response.setStatus("Fail");
		}
		else {
			Random random = new Random();
			String OTPstring = Integer.toString(random.nextInt(9999));
			userService.saveOTP(OTPstring);
			mailService.sendSimpleEmail("harshithsharma7251@gmail.com",OTPstring ,"OTP");
			
			
//			response.setMessage("User Login Succesfull");
			response.setStatus("Success");
//			response.setObject(user);
			
		}	
		
	
		return response;
}
	@GetMapping("/getOTP")
	public ServiceResponse getOTP( ) {
		ServiceResponse response = new ServiceResponse();
		
		Random random = new Random();	 
		response.setMessage("OTP generated Succesfull");
		// response.setObject(random.nextInt(9999));
		 
		 String OTPstring = Integer.toString(random.nextInt(9999));
		 
		 userService.saveOTP(OTPstring);
		 
		 
		 
		 response.setStatus("Success");
		 
		 mailService.sendSimpleEmail("harshithsharma7251@gmail.com",OTPstring ,"OTP");
		
		return response;
		
	}
	
	@PostMapping("/authenticationOTP")
	public ServiceResponse authenticationOTP(@RequestBody User user)
	{
		ServiceResponse response = new ServiceResponse();
		System.out.println(user);
		
		User userObject = userRepository.findUserByEmail("harshithsharma7251@gmail.com");
		
		System.out.println(userObject);
		
		if(user.getOtp().equals(userObject.getOtp()))
		{
			response.setMessage("User Login Succesfull");
			response.setStatus("Success");
			response.setObject(user);
		}
		else
		{
			response.setMessage("OTP is incorrect.Please try again.");
			response.setStatus("Fail");
			//response.setObject(user);
		}
		
		
		
		
		return response; 
	}
	
	@GetMapping("/searchuser/email/{email}")
	public ServiceResponse getDataByemail(@PathVariable(value = "email") String email) {

//	User user = userService.getUserByEmail(email);
		ServiceResponse response = userService.getUserByEmailid(email);
	
		return response;

	}
//	@PostMapping("/updatepassword") 
//	public ServiceResponse authenticateUserByEmail(@RequestBody User user) {
//		
//		ServiceResponse response = new ServiceResponse();
//		
//		
//		System.out.println(user);
//		
//		
//		User userObject1 = userRepository.findByEmail(user.getEmail());
//		
//		if(userObject1==null) {
//		response.setMessage("email does not exist");	
//		response.setStatus("fail");
//		response.setObject(user);
//		}
//		else{
//			Random random = new Random();
//			String email = Integer.toString(random.nextInt(9999));
//			userObject1.setEmail(email);
//			userRepository.save(userObject1);
//			
//			
//	}
//	
//return	response;
//}
}