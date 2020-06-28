package com.dao;

import com.domain.Outware;

import java.util.List;

public interface OutWareDao {
    void add(Outware outware);
    List<Outware> selectList();
    List<Outware> selectListByUid(int uid);
    void update(Outware outware);
    void delete(Outware outware);
}
