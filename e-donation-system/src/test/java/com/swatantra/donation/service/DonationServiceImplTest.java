package com.swatantra.donation.service;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.swatantra.donation.dto.DonationDto;
import com.swatantra.donation.testutility.MasterData;



@SpringBootTest
public class DonationServiceImplTest {
	
	@Autowired
	private DonationService donationService;
	
	@Test
	public void addDonationTest() {
		
		DonationDto donationDto = MasterData.getDonationDto();
		DonationDto actualDto = donationService.addDonation(donationDto);
		Assert.assertTrue(donationDto.equals(actualDto));
		
	}
	
	@Test
	public void findDonationsByDonorIdNoResultTest() {
			List<DonationDto> donationDtoList = donationService.finddonationsByDonorId(10L);
			Assert.assertTrue(donationDtoList.isEmpty());
	}
	
	@Test
	public void findDonationsByDonorIdTest() {
		DonationDto donationDto = MasterData.getDonationDto();
		donationDto.setDonorId(2L);
		donationService.addDonation(donationDto);
		List<DonationDto> donationDtoList = donationService.finddonationsByDonorId(2L);
		Assert.assertTrue(donationDtoList.size()==1);
	}

}
