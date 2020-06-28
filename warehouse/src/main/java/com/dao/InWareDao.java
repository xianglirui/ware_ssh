package com.dao;

import com.domain.Inware;

import java.util.List;

public interface InWareDao {
    void add(Inware inware);
    List<Inware> selectList();
    List<Inware> selectListByUid(int uid);
    void update(Inware inware);
    void delete(Inware inware);
}
