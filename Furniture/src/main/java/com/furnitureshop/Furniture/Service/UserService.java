package com.furnitureshop.Furniture.Service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furnitureshop.Furniture.Repository.UserRepository;
import com.furnitureshop.Furniture.Response.ServiceResponse;
import com.furnitureshop.Furniture.model.User;


@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MailService mailService;
	
	public void saveOTP(String OTP) {
		
	//	loginRepository.fi
		
		User user = userRepository.findUserByEmail("harshithsharma7251@gmail.com");
		
		System.out.println(user);
		user.setOtp(OTP);
		userRepository.save(user);
		//System.out.println(user);
		
	
		System.out.println("OTP saved");
		System.out.println(OTP);
		
	}

	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	public User getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
			System.out.println("inside service");
			System.out.println(user.get());
			
			return user.get();
		}
	
	public ServiceResponse getUserByEmailid(String email) {
		
		Optional<User> user = userRepository.findByEmail(email);
		
		ServiceResponse response = new ServiceResponse();
		
		//System.out.println(user.get());
		
		//User userObject1 =user.get();
	//}
	//return customer.get();
	//}

		if(user.isEmpty())
		{
			response.setMessage("Email does not exist");
			response.setStatus("Fail");
		}
		else {
			User userObject1 =user.get();
			Random random = new Random();
			String string=Integer.toString(random.nextInt(9999));
			userObject1.setPassword(string);
		   userRepository.save(userObject1);
		   mailService.sendCredentialByName(email, string,"New password");
		   response.setStatus("Success");
		   response.setMessage("New password Generated please check your Email");
		}	
		
		return response;
		
	}
}

