package com.refinitiv.carlos.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.refinitiv.carlos.app.models.entity.Account;

public interface IAccountDAO extends CrudRepository<Account, Long>{
	
}
