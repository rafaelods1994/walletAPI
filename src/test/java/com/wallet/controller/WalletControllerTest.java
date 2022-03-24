package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.entities.Wallet;
import com.wallet.service.WalletService;

@SpringBootTest
@Profile("test")
public class WalletControllerTest {

	@MockBean
	private WalletService walletService;

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	
	public static final Long ID = 1L;
	public static final String NAME = "TEST";
	public static final BigDecimal VALUE = new BigDecimal(1);
	private static final String URL = "/wallet";
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void shouldSaveWallet() throws Exception {
		BDDMockito.given(walletService.save(Mockito.any(Wallet.class))).willReturn(getMockWallet());		

		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, NAME, VALUE))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.name").value(NAME))
				.andExpect(jsonPath("$.data.value").value(VALUE));
	}
	
	@Test
	public void shouldNotSaveInvalidWallet() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, "A", null))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0]").value("Insira um valor para a carteira"))
				.andExpect(jsonPath("$.errors[1]").value("O nome deve conter no minimo 3 caracteres"));

	}
	
	private String getJsonPayload(Long id, String name, BigDecimal value) throws JsonProcessingException {
		Wallet wallet = new Wallet();
		wallet.setId(id);
		wallet.setName(name);
		wallet.setValue(value);
		ObjectMapper objectMapper = new ObjectMapper();
		
		return objectMapper.writeValueAsString(wallet);
	}

	public Wallet getMockWallet() {
		Wallet wallet = new Wallet();
		wallet.setId(ID);
		wallet.setName(NAME);
		wallet.setValue(VALUE);
		return wallet;
	}

}
