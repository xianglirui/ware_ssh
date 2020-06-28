package com.dao;

import com.domain.Inware;
import com.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository("InWareDao")
@Transactional
public class InWareImpl implements InWareDao{
    //spring注入
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    @Override
    public void add(Inware inware) {
        // 使用getCurrentSession获取session
        Session session = sessionFactory.getCurrentSession();
        session.save(inware);
    }

    @Override
    public List<Inware> selectList() {
        String hql = "from Inware ";
        Query<?> query = sessionFactory.getCurrentSession().createQuery(hql);
        @SuppressWarnings("unchecked")
        List<Inware> list = (List<Inware>) query.getResultList();
        return list;
    }

    @Override
    public List<Inware> selectListByUid(int uid) {
        String hql = "from Inware where uid=?";
        Query<?> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, uid);
        @SuppressWarnings("unchecked")
        List<Inware> list = (List<Inware>) query.getResultList();
        return list;
    }

    @Override
    public void update(Inware inware) {
        sessionFactory.getCurrentSession().update(inware);
    }

    @Override
    public void delete(Inware inware) {
        sessionFactory.getCurrentSession().delete(inware);
    }
}
