package com.vedant.smartcomplaintmanagement.repository;

import com.vedant.smartcomplaintmanagement.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}