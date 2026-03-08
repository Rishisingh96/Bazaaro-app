package com.rishi.service;

import com.rishi.modal.Home;
import com.rishi.modal.HomeCategory;

import java.util.List;

public interface HomeService {
    public Home createHomePageData(List<HomeCategory> allCategories);
}
