package com.swatantra.donation.service;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.swatantra.donation.dto.DonorDto;
import com.swatantra.donation.exceptions.DonorNotFoundException;
import com.swatantra.donation.exceptions.NgoNotFoundException;
import com.swatantra.donation.testutility.MasterData;



@SpringBootTest
class DonorServiceImplTest {
	
	@Autowired
	private DonorService donorService;
	
	@Test
	void registerDonorTest() {		
		DonorDto donorDto = MasterData.getDonorDto();
		DonorDto actualDto = donorService.registerDonor(donorDto);
		Assert.assertEquals(donorDto,actualDto);
		
	}
	
	@Test
	void registerDonorNgoIdExceptionTest() {		
		DonorDto donorDto = MasterData.getDonorDto();
		donorDto.setNgoId(10L);
		Assert.assertThrows(NgoNotFoundException.class, () -> {donorService.registerDonor(donorDto);});	
		
	}
	
	@Test
	void updateDonorTest() {		
		DonorDto donorDto = MasterData.getDonorDto();
		donorDto.setDonarId(1L);
		donorDto.setDonarName("updatedDonor");
		DonorDto actualDto = donorService.updateDonor(donorDto);
		Assert.assertEquals(donorDto,actualDto);
		
	}
	
	@Test
	void findAllDonors() {
		List<DonorDto> donorDtoList = donorService.findAll();
		Assert.assertEquals(2 , donorDtoList.size());
		
	}
	
	@Test
	void updateDonorNgoIdExceptionTest() {		
		DonorDto donorDto = MasterData.getDonorDto();
		donorDto.setDonarId(1L);
		donorDto.setNgoId(10L);
		Assert.assertThrows(NgoNotFoundException.class, () -> {donorService.updateDonor(donorDto);});	
		
	}
	
	@Test
	void updateDonorDonorIdExceptionTest() {		
		DonorDto donorDto = MasterData.getDonorDto();
		donorDto.setDonarId(10L);
		Assert.assertThrows(DonorNotFoundException.class, () -> {donorService.updateDonor(donorDto);});	
		
	}
	
	@Test
	void findDonorWithNgoIdExceptionTest() {
			Assert.assertThrows(DonorNotFoundException.class, () -> {donorService.getDonorsWithNgoId(10L);});
	}
	
	@Test
	void findDonorWithNgoIdTest() {
		List<DonorDto> donorDtoList = donorService.getDonorsWithNgoId(1L);
		Assert.assertEquals(1 , donorDtoList.size());
		
	}
	
	@Test
	void findDonorByIdExceptionTest() {
		Assert.assertThrows(DonorNotFoundException.class, () -> {donorService.findDonorById(10L);});
	}
	
	@Test
	void findDonorByIdTest() {
		DonorDto donorDto = MasterData.getDonorDto();
		donorDto = donorService.registerDonor(donorDto);
		DonorDto actualDto = donorService.findDonorById(donorDto.getDonarId());
		Assert.assertEquals(donorDto,actualDto);
	}

}
