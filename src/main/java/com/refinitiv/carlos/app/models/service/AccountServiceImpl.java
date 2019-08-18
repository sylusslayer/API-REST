package com.refinitiv.carlos.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.refinitiv.carlos.app.models.dao.IAccountDAO;
import com.refinitiv.carlos.app.models.entity.Account;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired 
	private IAccountDAO accountDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Account> findAllAccounts() {
		return (List<Account>)accountDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Account findOneAccount(Long idAccount) {
		return accountDao.findById(idAccount).orElse(null);
	}

	@Transactional
	public Account createAccount(Account account) {
		return accountDao.save(account);
	}
	
	@Override
	@Transactional
	public void deleteAccount(Long idAccount) {
		accountDao.deleteById(idAccount);
	}

}
