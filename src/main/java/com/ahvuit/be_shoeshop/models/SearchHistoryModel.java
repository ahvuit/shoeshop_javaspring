package com.ahvuit.be_shoeshop.models;

import com.ahvuit.be_shoeshop.entity.SearchHistory;

public class SearchHistoryModel extends SearchHistory {

    public SearchHistoryModel() {
    }

    public SearchHistoryModel(String searchId, String keyword, String userId) {
        super(searchId, keyword, userId);
    }

}
