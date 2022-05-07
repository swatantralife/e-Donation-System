package com.swatantra.donation.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.swatantra.donation.dto.DonorDto;
import com.swatantra.donation.exceptions.DonorNotFoundException;
import com.swatantra.donation.exceptions.NgoNotFoundException;
import com.swatantra.donation.service.DonationService;
import com.swatantra.donation.service.DonorService;
import com.swatantra.donation.service.NgoService;
import com.swatantra.donation.testutility.MasterData;

@WebMvcTest(DonationController.class)
@AutoConfigureMockMvc
class DonorTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DonorService donorService;
	
	@MockBean
	private NgoService ngoService;
	
	@MockBean
	private DonationService donationService;
	
	
	/*   Donor Test Cases    */
	
	@Test
	void testRegisterDonorNgoId() throws Exception{
		DonorDto donorDto = MasterData.getDonorDto();
		
		when(this.donorService.registerDonor(donorDto)).thenReturn(donorDto);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donors/register-donor")
				.content(MasterData.convertToJson(donorDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.convertToJson(donorDto)));
	}
	
	@Test
	void testUpdateDonor() throws Exception{
		DonorDto donorDto = MasterData.getDonorDto();
		
		when(this.donorService.registerDonor(donorDto)).thenReturn(donorDto);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/donors/update-donor")
				.content(MasterData.convertToJson(donorDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.convertToJson(donorDto)));
	}
	
	
	@Test
	void testRegisterDonorException() throws Exception{
		DonorDto donorDto = MasterData.getDonorDto();
		donorDto.setAddress("abc");
		
		when(this.donorService.registerDonor(donorDto)).thenReturn(donorDto);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donors/register-donor")
				.content(MasterData.convertToJson(donorDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void testRegisterDonorInvalidNgoIdException() throws Exception{
		DonorDto donorDto = MasterData.getDonorDto();
		
		when(this.donorService.registerDonor(donorDto)).thenThrow(new NgoNotFoundException("Ngo does not exists with given NgoId"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donors/register-donor")
				.content(MasterData.convertToJson(donorDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void testUpdateDonorException() throws Exception{
		DonorDto donorDto = MasterData.getDonorDto();
		donorDto.setAddress("abc");
		
		when(this.donorService.updateDonor(donorDto)).thenReturn(donorDto);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/donors/update-donor")
				.content(MasterData.convertToJson(donorDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void testUpdateDonorInvalidNgoIDException() throws Exception{
		DonorDto donorDto = MasterData.getDonorDto();
		
		when(this.donorService.updateDonor(donorDto)).thenThrow(new NgoNotFoundException("Ngo does not exists with given NgoId"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/donors/update-donor")
				.content(MasterData.convertToJson(donorDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void testUpdateDonorInvalidDonorIDException() throws Exception{
		DonorDto donorDto = MasterData.getDonorDto();
		
		when(this.donorService.updateDonor(donorDto)).thenThrow(new DonorNotFoundException("Donor does not exists with given donorId"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/donors/update-donor")
				.content(MasterData.convertToJson(donorDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(),HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void testGetDonorByNgoId() throws Exception{
		DonorDto donorDto = MasterData.getDonorDto();
		List<DonorDto> donorList = new ArrayList<>();
		donorList.add(donorDto);
		
		when(donorService.getDonorsWithNgoId(donorDto.getNgoId())).thenReturn(donorList);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donors/by-ngo-id/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.convertToJson(donorList)));
	}

	@Test
	void testGetDonorByDonorId() throws Exception{
		DonorDto donorDto = MasterData.getDonorDto();
		donorDto.setDonarId(1l);
		when(donorService.findDonorById(1L)).thenReturn(donorDto);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donors/by-donor-id/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.convertToJson(donorDto)));
	}
	
	@Test
	void testGetAllDonors() throws Exception{
		DonorDto donorDto = MasterData.getDonorDto();
		List<DonorDto> donorList = new ArrayList<>();
		donorList.add(donorDto);
		
		when(donorService.findAll()).thenReturn(donorList);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donors/findAll")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.convertToJson(donorList)));
	}
	
}
