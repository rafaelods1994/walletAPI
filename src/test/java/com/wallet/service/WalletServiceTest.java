package com.wallet.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;

import com.wallet.entities.Wallet;
import com.wallet.repository.WalletRepository;

@SpringBootTest
@Profile("test")
public class WalletServiceTest {

	@MockBean
	private WalletRepository walletRepository;

	@Autowired
	private WalletService walletService;

	public static final Long ID = 1L;
	public static final String NAME = "TEST";
	public static final BigDecimal VALUE = new BigDecimal(1);
	
	@BeforeEach
	public void setUp() {
		BDDMockito.given(walletRepository.save(any())).willReturn(new Wallet());

	}
	
	@Test
	public void shouldSaveWallet() {

		Wallet wallet = new Wallet();
		wallet.setId(ID);
		wallet.setName(NAME);
		wallet.setValue(VALUE);
		Wallet savedWallet = walletService.save(wallet);

		assertNotNull(savedWallet);

	}

}
