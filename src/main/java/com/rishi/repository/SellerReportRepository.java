package com.rishi.repository;

import com.rishi.modal.SellerReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerReportRepository extends JpaRepository<SellerReport, Long> {
    // Additional query methods can be defined here if needed
    // For example, you might want to find seller reports by seller ID or other criteria
    SellerReport findBySellerId(Long sellerId);
}
