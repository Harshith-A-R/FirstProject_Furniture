package com.furnitureshop.Furniture.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.furnitureshop.Furniture.Repository.FurnitureRepository;
import com.furnitureshop.Furniture.Response.ServiceResponse;
import com.furnitureshop.Furniture.Service.FurnitureService;
import com.furnitureshop.Furniture.model.Furniture;
import com.furnitureshop.Furniture.model.User;

@RestController
@CrossOrigin(value = "http://localhost:4200")
//@RequestMapping(path = "furnitures")
public class FurnitureController {
	@Autowired
	private  FurnitureRepository  furnitureRepository;
	
	@Autowired
	private FurnitureService furnitureService;
	
	//it is used to store the image 
	private byte[] bytes;
	
	@GetMapping("/getfurniture")
	public List<Furniture> getFurniture() {
		return furnitureRepository.findAll();
	}
	@GetMapping("/getid/{id}")
	public Furniture getFurnitureById(@PathVariable("id") int id) {
		return furnitureRepository.findFurnitureByld(id);
	}
	
	@PostMapping("/upload")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		this.bytes = file.getBytes();
	}

//	@PostMapping("/add")
//	public User createUser(@RequestBody User user) {
//		return userRepository.save(user);
	//}
	@PostMapping("/create")
	public Furniture createFurniture(@RequestBody Furniture furniture)  {
		//furniture.setPicByte(this.bytes);
		System.out.println(furniture);
		return furnitureRepository.save(furniture);
		//this.bytes = null;
	}
//	@DeleteMapping("/delete/{id}" )
//	public Furniture deleteFurniture(@PathVariable("id") long id) {
//		Furniture furniture =  furnitureRepository.getOne(id);
//		furnitureRepository.deleteById(id);
//		System.out.println(furniture);
//		return furniture;
//}
	@GetMapping("/deletefurniture/{id}")
	  public ResponseEntity<HttpStatus> deleteFurniture(@PathVariable("id") long id) {
	    try {
	      furnitureRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  
}
	
	@PostMapping("/updatefurnituredata")
	public ServiceResponse updatedata(@RequestBody Furniture data) {
		System.out.println(data);
		Optional<Furniture> furniture = furnitureRepository.findFurnitureById(data.getId());
		System.out.println(furniture);
		if (furniture.isPresent())
		{
			Furniture usr = furniture.get();
			
			usr.setId(data.getId());
			usr.setProductname(data.getProductname());
			usr.setPrice(data.getPrice());
		
			furnitureRepository.save(usr);
			System.out.println(usr);
		}
		return null;
	
	}	
}


