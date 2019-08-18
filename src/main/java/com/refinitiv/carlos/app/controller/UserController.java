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
import com.refinitiv.carlos.app.models.entity.User;
import com.refinitiv.carlos.app.models.service.IAccountService;
import com.refinitiv.carlos.app.models.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api("API de usuarios.")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAccountService accountService;
	
	@GetMapping("/")
	@ApiOperation("Lista todos los usuarios registrados en base de datos con sus respectivas cuentas.")
	public List<User> findAll(){
		return userService.findAllUsers();
	}
	
	@PostMapping("/")
	@ApiOperation("Crea un nuevo usuario con sus cuentas si son proporcionadas.")
	public User create(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/{userId}")
	@ApiOperation("Muestra el detalle un usuario por ID.")
	public User findOne(@PathVariable("userId") Long userId) {
		return userService.findOneUser(userId);
	}
	
	@DeleteMapping("/{userId}")
	@ApiOperation("Elimina un usuario por ID.")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}/{accountId}")
	@ApiOperation("Elimina una cuenta de la lista de cuentas de un usuario.")
	public ResponseEntity<HttpStatus> deleteAccountFromUser(@PathVariable("userId") Long userId, @PathVariable("accountId") Long accountId) {
		User user = userService.findOneUser(userId);
		Account account = accountService.findOneAccount(accountId);
		if (!user.getAccountList().isEmpty()) {
			List<Account> userAccounts = user.getAccountList();
			if (userAccounts.contains(account)) {
				accountService.deleteAccount(accountId);
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			} else {
				return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
	}
}
