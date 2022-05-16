package ru.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.web.model.User;



public interface UserRepo extends JpaRepository<User, Long> {

    /**
     * Метод, возвращающий объект типа @User по заданному @param username
     * @param username - содержит имя пользователя
     * @return объект типа @User
     */
    User findByUsername(String username);

    @Modifying
    @Query("update User m set m.name = ?1,  m.username = ?2, m.password = ?3 where m.id = ?4")
    void updateUser(String name, String username, String password, Long id);
}
