package ru.web.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.web.model.User;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImp() {
    }

    public List<User> getAllUsers() {
        return this.entityManager.createQuery("SELECT c FROM User c", User.class).getResultList();
    }

    public void createUser(User user) {
        this.entityManager.persist(user);
        this.entityManager.flush();
    }

    public void updateUser(User user) {
        this.entityManager.merge(user);
        this.entityManager.flush();
    }

    public User getUserById(long id) {
        return (User)this.entityManager.find(User.class, id);
    }

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
