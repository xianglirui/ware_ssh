package com.service;

import com.domain.Outware;

import java.util.List;

public interface OutWareService {
    void add(Outware outware);
    List<Outware> selectList();
    List<Outware> selectListByUid(int uid);
    void update(Outware outware);
    void delete(Outware outware);
}
