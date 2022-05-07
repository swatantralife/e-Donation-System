package com.swatantra.donation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swatantra.donation.entity.DonorEntity;

@Repository
public interface DonorRepository extends JpaRepository<DonorEntity,Long> {
	
	List<DonorEntity> findByNgoId(Long ngoId);

}
