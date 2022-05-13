package ru.web.service;

import ru.web.model.Role;
import ru.web.model.User;

import java.util.List;

public interface UserService {

    /**
     * Метод, сохраняющий объект типа юзер в базе данных
     * @param user содержит объект типа юзер
     * @return объект типа юзер
     */
    User saveUser(User user);

    /**
     * Метод, сохраняющий роль в базе данных
     * @param role содержит роль
     * @return Role
     */
    Role saveRole(Role role);

    /**
     * Метод, добавляющий роль юзеру
     * @param username содержит имя пользователя
     * @param roleName содержит роль
     */
    void addRoleToUser(String username, String roleName);

    /**
     * Метод, поулчающий объект юзер по его имени
     * @param username содержит имя пользователя
     * @return объект типа юзер
     */
    User getUser(String username);

    /**
     * Метод, возвращающий список объектов типа User
     * @return список объектов типа User
     */
    List<User> getUsers();

    /**
     * Метод, возвращающий пользователя из базы данных по его ID
     * @param id индентификатор пользователя
     * @return объект типа User
     */
    User getUserById(Long id);

    /**
     * Метод, обновляющий поля пользователя в базе данных
     * @param user объект типа User
     */
    void updateUser(User user);

    /**
     * Метод, удаляющий пользователя в базе данных по его id
     * @param id содержит идентификатор пользователя
     */
    void deleteUserById(Long id);
}
