package com.dao;

import com.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao{
    //spring注入
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        // 使用getCurrentSession获取session
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public List<User> selectUser() {
        String hql = "from User";
        Query<?> query = sessionFactory.getCurrentSession().createQuery(hql);
        @SuppressWarnings("unchecked")
        List<User> list = (List<User>) query.getResultList();
        return list;
    }

    @Override
    public User selectByNikeName(String nikename) {
        String hql = "from User where nikename = ?";
        Query<?> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, nikename);
        return (User) query.uniqueResult();
    }

    @Override
    public User selectByNikeNameAndPwd(String nikename,String pwd) {
        String hql = "from User where nikename = ? and password=?";
        Query<?> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, nikename);
        query.setParameter(1, pwd);
        return (User) query.uniqueResult();
    }

    @Override
    public User selectByName(String name) {
        String hql = "from User where name=?";
        Query<?> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, name);
        return (User) query.uniqueResult();
    }

    @Override
    public void updateByName(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void deleteByName(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}
