package com.swatantra.donation.testutility;

import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swatantra.donation.dto.DonationDto;
import com.swatantra.donation.dto.DonorDto;
import com.swatantra.donation.dto.NgoDto;

public class MasterData {
	
	public static NgoDto getNgoDto() {
		NgoDto dto = new NgoDto();
		dto.setAddress("hyderabad");
		dto.setEstablishedDate(LocalDate.of(2022, 4, 30));
		dto.setNgoName("TestNgo");
		dto.setPassword("qwerty");
		dto.setPhoneNumber(1234567890L);
		dto.setUserName("abcdef");
		return dto;
	}
	
	public static DonationDto getDonationDto() {
		DonationDto dto = new DonationDto();
		dto.setAmount(200.0);
		dto.setDonationDate(LocalDate.now());
		dto.setDonationType("Charity");
		dto.setDonorId(1L);
		dto.setNgoId(1L);
		return dto;
	}
	
	public static DonorDto getDonorDto() {
		DonorDto dto = new DonorDto();
		dto.setAddress("hyderabad");
		dto.setDonarName("testdonor");
		dto.setEmail("abc@def.com");
		dto.setNgoId(1L);
		dto.setPassword("qwerty");
		dto.setPhoneNumber(1234567890L);
		dto.setUserName("abcde");
		return dto;
	}
	public static String convertToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objMap = new ObjectMapper();
		return objMap.writeValueAsString(obj);
		
	}

}
