package com.furnitureshop.Furniture.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.furnitureshop.Furniture.model.Furniture;
import com.furnitureshop.Furniture.model.User;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {

	Optional<Furniture> findFurnitureById(Long id);

	@Query(value = "select * from furniture where id = :id", nativeQuery = true)
	Furniture findFurnitureByld(@Param("id") int id);
	
}
	