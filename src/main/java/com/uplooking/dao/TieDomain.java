package com.uplooking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.uplooking.pojo.Tie;
import com.uplooking.pojo.User;

public interface TieDomain  extends JpaRepository<Tie, Integer>,JpaSpecificationExecutor<Tie>{
		
}
