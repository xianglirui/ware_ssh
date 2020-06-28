package com.service;

import com.dao.InWareDao;
import com.dao.OutWareDao;
import com.domain.Outware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OutWareServiceImpl implements OutWareService{
    //注入dao
    @Resource
    private OutWareDao outWareDao;

    //注入事务管理
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    @Override
    public void add(Outware outware) {
        outWareDao.add(outware);
    }

    @Override
    public List<Outware> selectList() {
        return outWareDao.selectList();
    }

    @Override
    public List<Outware> selectListByUid(int uid) {
        return outWareDao.selectListByUid(uid);
    }

    @Override
    public void update(Outware outware) {
        outWareDao.update(outware);
    }

    @Override
    public void delete(Outware outware) {
        outWareDao.delete(outware);
    }
}
