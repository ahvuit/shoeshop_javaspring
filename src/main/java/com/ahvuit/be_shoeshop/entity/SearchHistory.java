package com.ahvuit.be_shoeshop.entity;

import org.springframework.data.annotation.Id;

public class SearchHistory {
    @Id
    private String searchId;
    private String keyword;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SearchHistory() {
    }

    public SearchHistory(String searchId, String keyword, String userId) {
        this.searchId = searchId;
        this.keyword = keyword;
        this.userId = userId;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
