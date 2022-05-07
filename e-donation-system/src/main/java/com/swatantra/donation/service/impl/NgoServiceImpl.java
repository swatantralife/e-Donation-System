package com.swatantra.donation.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swatantra.donation.dto.NgoDto;
import com.swatantra.donation.entity.NgoEntity;
import com.swatantra.donation.exceptions.NgoNotFoundException;
import com.swatantra.donation.repository.NgoRepository;
import com.swatantra.donation.service.NgoService;

@Service(value = "ngoService")
public class NgoServiceImpl implements NgoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NgoServiceImpl.class);

	@Autowired
	private NgoRepository ngorepository;

	@Override
	public NgoDto registerNgo(NgoDto ngoDto) {
		NgoEntity ngoEntity = new NgoEntity();
		BeanUtils.copyProperties(ngoDto, ngoEntity);
		ngoEntity = ngorepository.save(ngoEntity);
		ngoDto.setNgoId(ngoEntity.getNgoId());
		LOGGER.info("Ngo Saved");
		return ngoDto;
		
	}

	@Override
	public NgoDto findById(Long ngoId) {
		Optional<NgoEntity> ngoEntity = ngorepository.findById(ngoId);
		if(ngoEntity.isPresent()) {
			NgoDto ngoDto = new NgoDto();
			BeanUtils.copyProperties(ngoEntity.get(), ngoDto);
			LOGGER.info("NGO found for NgoId : {}" , ngoId);
			return ngoDto;
		}else {
			LOGGER.error("NGO does not exist for NgoId : {} ", ngoId);
			throw new NgoNotFoundException("NGO does not exists for given NgoId");
		}
	}
	

}
