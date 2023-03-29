package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override

    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    public List<User> getAllUsersByCar(String model, int series) {
        List<User> users;
        Query<User> query = sessionFactory.getCurrentSession().createQuery("from User " +
                "where carId.series = :series and carId.model = :model", User.class);

        query.setParameter("series", series);
        query.setParameter("model", model);
        users = query.getResultList();

        return users.isEmpty() ? Collections.emptyList() : users;
    }

}
