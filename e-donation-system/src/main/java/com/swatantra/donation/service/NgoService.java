package com.swatantra.donation.service;

import com.swatantra.donation.dto.NgoDto;

public interface NgoService {
	
	public NgoDto registerNgo(NgoDto ngodto);
	
	public NgoDto findById(Long ngoId);

}
