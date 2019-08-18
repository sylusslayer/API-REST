package com.refinitiv.carlos.app.models.service;

import java.util.List;

import com.refinitiv.carlos.app.models.entity.Account;

public interface IAccountService {

	public List<Account> findAllAccounts();
	
	public Account findOneAccount(Long ccountId);
	
	public void deleteAccount(Long accountId);
	
	public Account createAccount(Account account);
}
