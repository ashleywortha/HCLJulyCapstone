package com.ashley.repo;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashley.model.Address;
import com.ashley.model.User;

@Repository 
public interface AddressRepo extends JpaRepository <Address, Integer> {
	List<Address> findByUser(User user, Sort sort);

}
