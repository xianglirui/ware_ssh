package com.dao;

import com.domain.Inware;
import com.domain.Outware;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Transactional
public class OutWareDaoImpl implements OutWareDao{
    //spring注入
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    @Override
    public void add(Outware outware) {
        Session session = sessionFactory.getCurrentSession();
        session.save(outware);
    }

    @Override
    public List<Outware> selectList() {
        String hql = "from Outware ";
        Query<?> query = sessionFactory.getCurrentSession().createQuery(hql);
        @SuppressWarnings("unchecked")
        List<Outware> list = (List<Outware>) query.getResultList();
        return list;
    }

    @Override
    public List<Outware> selectListByUid(int uid) {
        String hql = "from Outware where uid=?";
        Query<?> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, uid);
        @SuppressWarnings("unchecked")
        List<Outware> list = (List<Outware>) query.getResultList();
        return list;
    }

    @Override
    public void update(Outware outware) {
        sessionFactory.getCurrentSession().update(outware);
    }

    @Override
    public void delete(Outware outware) {
        sessionFactory.getCurrentSession().delete(outware);
    }
}
