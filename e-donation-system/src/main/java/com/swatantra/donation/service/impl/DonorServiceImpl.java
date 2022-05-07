package com.swatantra.donation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swatantra.donation.dto.DonorDto;
import com.swatantra.donation.entity.DonorEntity;
import com.swatantra.donation.exceptions.DonorNotFoundException;
import com.swatantra.donation.repository.DonorRepository;
import com.swatantra.donation.service.DonorService;
import com.swatantra.donation.service.NgoService;

@Service(value = "donorService")
public class DonorServiceImpl implements DonorService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DonorServiceImpl.class);
	
	@Autowired
	private DonorRepository donorRepository;
	
	@Autowired
	private NgoService ngoService;
	
	@Override
	public DonorDto registerDonor(DonorDto donorDto) {
		ngoService.findById(donorDto.getNgoId());
		DonorEntity donorEntity = new DonorEntity();
		BeanUtils.copyProperties(donorDto, donorEntity);
		donorEntity = donorRepository.save(donorEntity);
		donorDto.setDonarId(donorEntity.getDonarId());
		LOGGER.info("Donor Saved");
		return donorDto;
	}
	
	@Override
	public DonorDto updateDonor(DonorDto donorDto) {
		findDonorById(donorDto.getDonarId());
		ngoService.findById(donorDto.getNgoId());
		DonorEntity donorEntity = new DonorEntity();
		BeanUtils.copyProperties(donorDto, donorEntity);
		donorEntity = donorRepository.save(donorEntity);
		donorDto.setDonarId(donorEntity.getDonarId());
		LOGGER.info("Donor Saved");
		return donorDto;
	}
	
	@Override
	public List<DonorDto> findAll() {
		List<DonorEntity> donorEntityList = donorRepository.findAll();
		
		List<DonorDto> donorDtoList = new ArrayList<>();
		for(DonorEntity entity : donorEntityList) {
			DonorDto dto = new DonorDto();
			BeanUtils.copyProperties(entity, dto);
			donorDtoList.add(dto);
		}
		
		return donorDtoList;
	}

	@Override
	public List<DonorDto> getDonorsWithNgoId(Long ngoId) {
		List<DonorEntity> donorEntityList = donorRepository.findByNgoId(ngoId);
		if(donorEntityList.isEmpty()) {
			LOGGER.error("No Donors found for NgoId : {}", ngoId);
			throw new DonorNotFoundException("No Donors found for queried NgoId");
		}
		List<DonorDto> donorDtoList = new ArrayList<>();
		for(DonorEntity entity : donorEntityList) {
			DonorDto dto = new DonorDto();
			BeanUtils.copyProperties(entity, dto);
			donorDtoList.add(dto);
		}
		
		return donorDtoList;
	}

	@Override
	public DonorDto findDonorById(Long donorId) {
		Optional<DonorEntity> donorEntity = donorRepository.findById(donorId);
		if(donorEntity.isPresent()) {
			DonorDto donorDto = new DonorDto();
			BeanUtils.copyProperties(donorEntity.get(), donorDto);
			LOGGER.info("Donor found for DonorID : {}" , donorId);
			return donorDto;
		}else {
			LOGGER.error("Donor does not exist for DonorId : {} ", donorId);
			throw new DonorNotFoundException("Donor does not exists for given DonorId");
		}
	}

	
	
	
	
	

}
