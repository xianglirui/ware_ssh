package com.dao;

import com.domain.Purchase;

import java.util.List;

public interface PurchaseDao {
    void add(Purchase purchase);
    Purchase select(String name,String man,String time);
    List<Purchase> selectList();
    List<Purchase> selectListByUid(int uid);
    void update(Purchase purchase);
    void delete(Purchase purchase);
}
