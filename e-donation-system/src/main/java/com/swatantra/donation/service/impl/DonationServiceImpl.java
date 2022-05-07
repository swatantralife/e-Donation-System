package com.swatantra.donation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swatantra.donation.dto.DonationDto;
import com.swatantra.donation.entity.DonationEntity;
import com.swatantra.donation.repository.DonationRepository;
import com.swatantra.donation.service.DonationService;

@Service(value = "donationService")
public class DonationServiceImpl implements DonationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DonationServiceImpl.class);
	
	
	@Autowired
	private DonationRepository donationRepository;

	@Override
	public DonationDto addDonation(DonationDto donationDto) {
		DonationEntity entity = new DonationEntity(); 
		BeanUtils.copyProperties(donationDto, entity);
		entity = donationRepository.save(entity);
		donationDto.setDonationId(entity.getDonationId());
		LOGGER.info("Donation Saved");
		return donationDto;
	}

	@Override
	public List<DonationDto> finddonationsByDonorId(Long donorId) {
		List<DonationEntity> donationEntityList = donationRepository.findByDonorId(donorId);
		if(donationEntityList.isEmpty()) {
			LOGGER.error("No Donations found for DonorId : {}", donorId);
		}
		List<DonationDto> donationDtoList = new ArrayList<>();
		for(DonationEntity entity : donationEntityList) {
			DonationDto dto = new DonationDto();
			BeanUtils.copyProperties(entity, dto);
			donationDtoList.add(dto);
		}
		
		return donationDtoList;
	}

}
