package com.rishi.service;

import com.rishi.modal.Seller;
import com.rishi.modal.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);
}
