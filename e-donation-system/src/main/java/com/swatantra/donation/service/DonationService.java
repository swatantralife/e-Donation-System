package com.swatantra.donation.service;

import java.util.List;

import com.swatantra.donation.dto.DonationDto;

public interface DonationService {
	
	public DonationDto addDonation(DonationDto donationDto) ;
	
	public List<DonationDto> finddonationsByDonorId(Long donorId);

}
