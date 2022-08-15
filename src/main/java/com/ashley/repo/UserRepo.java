package com.ashley.repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashley.model.User;
@Repository 
public interface UserRepo extends JpaRepository <User, Integer>{
//	User findById(int id);
	Optional<User> findByUsername(String username);

}
