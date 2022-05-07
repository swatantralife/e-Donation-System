package com.swatantra.donation.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import com.swatantra.donation.dto.DonationDto;
import com.swatantra.donation.service.DonationService;
import com.swatantra.donation.service.DonorService;
import com.swatantra.donation.service.NgoService;
import com.swatantra.donation.testutility.MasterData;

@WebMvcTest(DonationController.class)
@AutoConfigureMockMvc
class DonationTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DonorService donorService;

	@MockBean
	private NgoService ngoService;

	@MockBean
	private DonationService donationService;

	/* Donation Test Cases */

	@Test
	public void testAddDonation() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();

		when(this.donationService.addDonation(donationDto)).thenReturn(donationDto);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donation/add-donation")
				.content(MasterData.convertToJson(donationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.convertToJson(donationDto)));
	}

	@Test
	public void testAddDonationException() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		donationDto.setAmount(-2.25);

		when(this.donationService.addDonation(donationDto)).thenReturn(donationDto);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donation/add-donation")
				.content(MasterData.convertToJson(donationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void testGetDonationByDonarId() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		List<DonationDto> donationList = new ArrayList<>();
		donationList.add(donationDto);

		when(donationService.finddonationsByDonorId(donationDto.getDonorId())).thenReturn(donationList);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donations/by-id/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertTrue(result.getResponse().getContentAsString().contentEquals(MasterData.convertToJson(donationList)));
	}

}
