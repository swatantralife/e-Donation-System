package com.swatantra.donation.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.swatantra.donation.dto.NgoDto;
import com.swatantra.donation.exceptions.NgoNotFoundException;
import com.swatantra.donation.testutility.MasterData;



@SpringBootTest
public class NgoServiceImplTest {
	
	@Autowired
	private NgoService ngoService;
	
	@Test
	public void registerNgoTest() {
		
		NgoDto ngoDto = MasterData.getNgoDto();		
		NgoDto responseDto = ngoService.registerNgo(ngoDto);		
		Assert.assertEquals(responseDto,ngoDto);	
		
	}
	
	@Test
	public void findByIdTest() {
		NgoDto ngoDto = MasterData.getNgoDto();	
		ngoService.registerNgo(ngoDto);
		NgoDto responseDto = ngoService.findById(1L);
		Assert.assertTrue(responseDto.equals(responseDto));	
	}
	
	@Test
	public void findByIdTestException() {
		Assert.assertThrows(NgoNotFoundException.class, () -> {ngoService.findById(2L);});
	}

}
