package com.refinitiv.carlos.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.refinitiv.carlos.app.models.entity.Account;
import com.refinitiv.carlos.app.models.service.IAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/accounts")
@Api("API de cuentas de usuarios.")
public class AccountController {

	@Autowired
	private IAccountService accountService;
	
	@GetMapping("/")
	@ApiOperation("Lista todas las cuentas registradas en base de datos.")
	public List<Account> findAll() {
		return accountService.findAllAccounts();
	}
	
	@GetMapping("/{accountId}")
	@ApiOperation("Muestra el detalle de una cuenta de usuario por ID.")
	public Account findOne(@PathVariable("accountId") Long accountId) {
		return accountService.findOneAccount(accountId);
	}
	
	@PostMapping("/")
	@ApiOperation("Agrega una nueva cuenta a un usuario proporcionando el id_usuario.")
	public Account create(@RequestBody Account account) {
		return accountService.createAccount(account);
	}
	
	@DeleteMapping("/{accountId}")
	@ApiOperation("Elimina una cuenta por ID.")
	public ResponseEntity<HttpStatus> delete(@PathVariable("accountId") Long accountId) {
		accountService.deleteAccount(accountId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
}
