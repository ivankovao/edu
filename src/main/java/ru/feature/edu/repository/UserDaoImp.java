package ru.feature.edu.repository;

import java.util.List;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.feature.edu.model.User;

@Repository
public class UserDaoImp implements UserDao {

    private EntityManager entityManager;

    public UserDaoImp() {
    }

    @Autowired
    public UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<User> getAllUsers() {
        return this.entityManager.createQuery("SELECT c FROM User c", User.class).getResultList();
    }
    @Transactional
    public void createUser(User user) {
        this.entityManager.persist(user);
        this.entityManager.flush();
    }
    @Transactional
    public void updateUser(User user) {
        this.entityManager.merge(user);
        this.entityManager.flush();
    }
    @Transactional
    public User getUserById(long id) {
        return (User)this.entityManager.find(User.class, id);
    }
    @Transactional
    public void deleteUser(long id) throws NullPointerException {
        User user = this.getUserById(id);
        if (user == null) {
            throw new NullPointerException("User not found");
        } else {
            this.entityManager.remove(user);
            this.entityManager.flush();
        }
    }
}
