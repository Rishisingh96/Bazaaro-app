package com.rishi.service.impl;

import com.rishi.modal.Seller;
import com.rishi.modal.SellerReport;
import com.rishi.repository.SellerReportRepository;
import com.rishi.service.SellerReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class sellerReportServiceImpl implements SellerReportService {

    private final SellerReportRepository sellerReportRepository;

    @Override
    public SellerReport getSellerReport(Seller seller) {
        SellerReport sellerReport = sellerReportRepository.findBySellerId(seller.getId());
        if(sellerReport == null){
            SellerReport newSellerReport = new SellerReport();
            newSellerReport.setSeller(seller);
            return sellerReportRepository.save(newSellerReport);
        }
        return sellerReport;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {
        return null;
    }
}
