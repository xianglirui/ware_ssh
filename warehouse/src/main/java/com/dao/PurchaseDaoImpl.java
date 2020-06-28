package com.dao;

import com.domain.Purchase;
import com.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Transactional
public class PurchaseDaoImpl implements PurchaseDao{
    //spring注入
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(Purchase purchase) {
        Session session = sessionFactory.getCurrentSession();
        session.save(purchase);
    }

    @Override
    public Purchase select(String name, String man, String time) {
        String hql = "from Purchase where name = ? and man=? and time=?";
        Query<?> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, name);
        query.setParameter(1, man);
        query.setParameter(2, time);
        return (Purchase) query.uniqueResult();
    }

    @Override
    public List<Purchase> selectList() {
        String hql = "from Purchase where status='申请中'";
        Query<?> query = sessionFactory.getCurrentSession().createQuery(hql);
        @SuppressWarnings("unchecked")
        List<Purchase> list = (List<Purchase>) query.getResultList();
        return list;
    }

    @Override
    public List<Purchase> selectListByUid(int uid) {
        String hql = "from Purchase where uid = ?";
        Query<?> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, uid);
        @SuppressWarnings("unchecked")
        List<Purchase> list = (List<Purchase>) query.getResultList();
        return list;
    }

    @Override
    public void update(Purchase purchase) {
        sessionFactory.getCurrentSession().update(purchase);
    }

    @Override
    public void delete(Purchase purchase) {
        sessionFactory.getCurrentSession().delete(purchase);
    }
}
