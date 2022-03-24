package com.wallet.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.wallet.entities.Wallet;

@SpringBootTest
@Profile("test")
public class WalletRepositoryTest {

	@Autowired
	public WalletRepository walletRepository;

	public static final Long ID = 1L;
	public static final String NAME = "TEST";
	public static final BigDecimal VALUE = new BigDecimal(1);

	@BeforeEach
	public void setUp() {

		Wallet wallet = new Wallet();
		wallet.setId(ID);
		wallet.setName(NAME);
		wallet.setValue(VALUE);
		walletRepository.save(wallet);

	}

	@Test
	public void shouldSaveWallet() {

		Wallet wallet = new Wallet();
		wallet.setId(ID);
		wallet.setName(NAME);
		wallet.setValue(VALUE);
		Wallet savedWallet = walletRepository.save(wallet);

		assertNotNull(savedWallet);

	}

	@AfterEach
	public void tearDown() {
		walletRepository.deleteAll();
	}

}
