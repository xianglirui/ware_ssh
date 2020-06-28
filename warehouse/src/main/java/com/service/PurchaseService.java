package com.service;

import com.domain.Purchase;

import java.util.List;

public interface PurchaseService {
    void add(Purchase Purchase);
    List<Purchase> selectList();
    Purchase select(String name,String man,String time);
    List<Purchase> selectListByUid(int uid);
    void update(Purchase Purchase);
    void delete(Purchase Purchase);
}
