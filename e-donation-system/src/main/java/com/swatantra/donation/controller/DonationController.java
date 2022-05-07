package com.swatantra.donation.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swatantra.donation.dto.DonationDto;
import com.swatantra.donation.dto.DonorDto;
import com.swatantra.donation.dto.NgoDto;
import com.swatantra.donation.exceptions.DonorNotFoundException;
import com.swatantra.donation.exceptions.InvalidDataException;
import com.swatantra.donation.exceptions.NgoNotFoundException;
import com.swatantra.donation.service.DonationService;
import com.swatantra.donation.service.DonorService;
import com.swatantra.donation.service.NgoService;

@RestController
public class DonationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DonationController.class);
	
	@Autowired
	private DonorService donorService;
	
	@Autowired
	private NgoService ngoService;
	
	@Autowired
	private DonationService donationService;
	
	@PostMapping("/donors/register-donor")
	public ResponseEntity<DonorDto> registerDonor(@Valid @RequestBody DonorDto donorDto, BindingResult result){
		if(result.hasErrors()) {
			LOGGER.error("Error while registering donor : {} ",result.getAllErrors());
			throw new InvalidDataException("Invalid Request parameters for donor regsitration");
		}
		try {
			donorService.registerDonor(donorDto);
			return ResponseEntity.ok(donorDto);
		}catch(NgoNotFoundException e) {
			LOGGER.error(e.getMessage());
			throw new NgoNotFoundException("Ngo does not exists with given NgoId") ;
		}
		
	}
	
	@PutMapping("/donors/update-donor")
	public ResponseEntity<DonorDto> updateDonor(@Valid @RequestBody DonorDto donorDto, BindingResult result){
		if(result.hasErrors()) {
			LOGGER.error("Error while registering donor : {} ",result.getAllErrors());
			throw new InvalidDataException("Invalid Request parameters for donor regsitration");
		}
		try {
			donorService.updateDonor(donorDto);
			return ResponseEntity.ok(donorDto);
		}catch(DonorNotFoundException e) {
			LOGGER.error("Donor does not exists with given donorId");
			throw new DonorNotFoundException("Donor does not exists with given donorId") ;
		}catch(NgoNotFoundException e) {
			LOGGER.error(e.getMessage());
			throw new NgoNotFoundException("Ngo does not exists with given NgoId") ;
		}
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/donors/by-donor-id/{donorId}")
	public ResponseEntity<DonorDto> getDonorById(@PathVariable Long donorId){
		DonorDto dto = donorService.findDonorById(donorId);
		return ResponseEntity.ok(dto);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/donors/findAll")
	public ResponseEntity<List<DonorDto>> getDonorsList(){
		List<DonorDto> donorList = donorService.findAll();
		return ResponseEntity.ok(donorList);
	}
	
	
	
	@PostMapping("/donors/register-ngo")
	public ResponseEntity<NgoDto> registerNgo(@Valid @RequestBody NgoDto ngoDto, BindingResult result){
		if(result.hasErrors()) {
			LOGGER.error("Error while registering ngo : {} ",result.getAllErrors());
			throw new InvalidDataException("Invalid Request parameters for Ngo regsitration");
		}
		ngoService.registerNgo(ngoDto);
		return ResponseEntity.ok(ngoDto);
	}
	
	@GetMapping("/donors/by-ngo-id/{ngoId}")
	public ResponseEntity<List<DonorDto>> getDonorsByNgoId(@PathVariable Long ngoId){
		List<DonorDto> donorList = donorService.getDonorsWithNgoId(ngoId);
		LOGGER.info("Total Number of Donors Retrieved for NgoId {} = {}",ngoId,donorList.size());
		return ResponseEntity.ok(donorList);
	}
	
	@PostMapping("/donation/add-donation")
	public ResponseEntity<DonationDto> addDonation(@Valid @RequestBody DonationDto donationDto, BindingResult result){
		if(result.hasErrors()) {
			LOGGER.error("Error while adding donation : {} ",result.getAllErrors());
			throw new InvalidDataException("Invalid Request parameters for add Donation ");
		}
		donationService.addDonation(donationDto);
		return ResponseEntity.ok(donationDto);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/donations/by-id/{donorId}")
	public ResponseEntity<List<DonationDto>> getDonationsByDonorId(@PathVariable Long donorId){
		List<DonationDto> donationList = donationService.finddonationsByDonorId(donorId);
		LOGGER.info("Total Number of Donations Retrieved for donorId {} = {}",donorId,donationList.size());
		return ResponseEntity.ok(donationList);
	}

}
