package com.service;

import com.dao.PurchaseDao;
import com.domain.Purchase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    @Resource
    private PurchaseDao purchaseDao;

    //注入事务管理
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})

    @Override
    public void add(Purchase purchase) {
        purchaseDao.add(purchase);
    }

    @Override
    public List<Purchase> selectList() {
        return purchaseDao.selectList();
    }

    @Override
    public Purchase select(String name, String man, String time) {
        return purchaseDao.select(name,man,time);
    }

    @Override
    public List<Purchase> selectListByUid(int uid) {
        return purchaseDao.selectListByUid(uid);
    }

    @Override
    public void update(Purchase purchase) {
        purchaseDao.update(purchase);
    }

    @Override
    public void delete(Purchase purchase) {
        purchaseDao.delete(purchase);
    }
}
