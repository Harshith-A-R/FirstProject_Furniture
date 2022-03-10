package com.furnitureshop.Furniture.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.furnitureshop.Furniture.model.User;

public interface UserRepository extends  JpaRepository<User, Long>{
	
	
	@Query(value = "SELECT * FROM User u WHERE u.name = :name and u.password = :password", nativeQuery = true)
	User userLogin(@Param("name") String userName, @Param("password") String password);
	
	@Query(value = "select * from user where email = :email", nativeQuery = true)
	User findUserByEmail(@Param("email") String email);

	Optional<User> findUserById(Long id);
	
 Optional<User> findByEmail(String email);

//Optional<User> findByEmailid(String emailid);

//	User findByEmail(String email);


	
}
