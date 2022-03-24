package com.wallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.dto.WalletDTO;
import com.wallet.entities.Wallet;
import com.wallet.response.Response;
import com.wallet.service.WalletService;
import com.wallet.utils.Util;

@RestController
@RequestMapping("wallet")
public class WalletController {
	
	@Autowired
	private WalletService walletService;
	
	@PostMapping
	public ResponseEntity<Response<WalletDTO>> create(@Valid @RequestBody WalletDTO walletDto, BindingResult result) {
		
		Response<WalletDTO> response = new Response<WalletDTO>();
		if(result.hasErrors())
		{
			result.getAllErrors().forEach(erro -> response.getErrors().add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Wallet resonseWallet = walletService.save(Util.convertWalletDtoToEntity(walletDto));
		
		response.setData(Util.convertEntityToWalletDto(resonseWallet));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}

}
