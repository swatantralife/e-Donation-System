package com.swatantra.donation.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.swatantra.donation.dto.NgoDto;
import com.swatantra.donation.service.DonationService;
import com.swatantra.donation.service.DonorService;
import com.swatantra.donation.service.NgoService;
import com.swatantra.donation.testutility.MasterData;

@WebMvcTest(DonationController.class)
@AutoConfigureMockMvc
class NgoTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DonorService donorService;
	
	@MockBean
	private NgoService ngoService;
	
	@MockBean
	private DonationService donationService;
	
	
		
	/*   NGO Test Cases    */
	
	@Test
	void testRegisterNgo() throws Exception{
		NgoDto ngoDto = MasterData.getNgoDto();
		
		when(this.ngoService.registerNgo(ngoDto)).thenReturn(ngoDto);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donors/register-ngo")
				.content(MasterData.convertToJson(ngoDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.convertToJson(ngoDto)));
	}
	
	@Test
	void testRegisterNgoInvalidAddress() throws Exception{
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setAddress("abc");
		
		when(this.ngoService.registerNgo(ngoDto)).thenReturn(ngoDto);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donors/register-ngo")
				.content(MasterData.convertToJson(ngoDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void testEstablishedDateFutureDate() throws Exception{
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setEstablishedDate(LocalDate.of(3099, 4, 30));
		
		when(this.ngoService.registerNgo(ngoDto)).thenReturn(ngoDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donors/register-ngo")
				.content(MasterData.convertToJson(ngoDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void testInvalidNgoName() throws Exception{
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setNgoName("Ngo");
		
		when(this.ngoService.registerNgo(ngoDto)).thenReturn(ngoDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donors/register-ngo")
				.content(MasterData.convertToJson(ngoDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void testInvalidPassword() throws Exception{
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setPassword("12");
		
		when(this.ngoService.registerNgo(ngoDto)).thenReturn(ngoDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donors/register-ngo")
				.content(MasterData.convertToJson(ngoDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void testInvalidPhoneNumber() throws Exception{
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setPhoneNumber(1111111L);
		
		when(this.ngoService.registerNgo(ngoDto)).thenReturn(ngoDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donors/register-ngo")
				.content(MasterData.convertToJson(ngoDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void testInvalidUserName() throws Exception{
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setUserName("abc");
		
		when(this.ngoService.registerNgo(ngoDto)).thenReturn(ngoDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donors/register-ngo")
				.content(MasterData.convertToJson(ngoDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.BAD_REQUEST.value());
	}

}
