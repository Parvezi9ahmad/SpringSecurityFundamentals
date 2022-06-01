package com.bharath.springcloud.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc

class CouponRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testGetcouponwithout_auth() throws Exception {
		mvc.perform(get("/couponapi/coupons/SUPERSALE")).andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(roles = "USER")
	public void testGetCouponwith_auth() throws Exception {
		mvc.perform(get("/couponapi/coupons/SUPERSALE")).andExpect(status().isOk()).andExpect(
				content().string("{\"id\":1,\"code\":\"SUPERSALE\",\"discount\":10.000,\"expDate\":\"12/12/2020\"}"));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testCreateCoupon_withoutcsrf_forbidden() throws Exception {
		mvc.perform(post("/couponapi/coupons")
				.content("{\"code\":\"SUPERSALE345678\",\"discount\":10.000,\"expDate\":\"12/12/2020\"})")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void testCreateCoupon_withcsrf() throws Exception {
		mvc.perform(post("/couponapi/coupons")
				.content("{\"code\":\"FLOPALE123\",\"discount\":10.000,\"expDate\":\"12/12/2020\"})")
				.contentType(MediaType.APPLICATION_JSON).with(csrf().asHeader())).andExpect(status().isOk());
	}

}
