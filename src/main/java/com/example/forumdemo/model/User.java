package com.example.forumdemo.model;

public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private long gmt_create;
    private long gmt_modified;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public long getGmt_create() {
        return gmt_create;
    }

    public long getGmt_modified() {
        return gmt_modified;
    }

    public String getAccoutId() {
        return accountId;
    }

    public String getToken() {
        return token;
    }

    public void setAccoutId(String accoutId) {
        this.accountId = accoutId;
    }

    public void setGmt_create(long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public void setGmt_modified(long gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
