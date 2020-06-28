package com.service;

import com.dao.InWareDao;
import com.dao.UserDao;
import com.domain.Inware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("InWareService")
public class InWareServiceImpl implements InWareService{
    //注入dao
    @Resource
    private InWareDao inWareDao;

    //注入事务管理
    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    @Override
    public void add(Inware inware) {
        inWareDao.add(inware);
    }

    @Override
    public List<Inware> selectList() {
        return inWareDao.selectList();
    }

    @Override
    public List<Inware> selectListByUid(int uid) {
        return inWareDao.selectListByUid(uid);
    }

    @Override
    public void update(Inware inware) {
        inWareDao.update(inware);
    }

    @Override
    public void delete(Inware inware) {
        inWareDao.delete(inware);
    }
}
