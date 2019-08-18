package com.refinitiv.carlos.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.refinitiv.carlos.app.models.entity.User;

public interface IUserDAO extends CrudRepository<User, Long>{

}
