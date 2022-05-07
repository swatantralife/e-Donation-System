package com.swatantra.donation.service;

import java.util.List;

import com.swatantra.donation.dto.DonorDto;

public interface DonorService {
	
	public DonorDto registerDonor(DonorDto donorDto) ;
	
	public DonorDto findDonorById(Long donorId) ;
	
	public List<DonorDto> getDonorsWithNgoId(Long ngoId) ;

	public DonorDto updateDonor(DonorDto donorDto);

	List<DonorDto> findAll();

}
