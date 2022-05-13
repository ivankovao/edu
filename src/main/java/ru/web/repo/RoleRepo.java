package ru.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.web.model.Role;


public interface RoleRepo extends JpaRepository<Role, Long> {
        /**
         * Метод, возвращающий объект типа роль по заданному полю - имя роли
         * @param name - содержит имя роли
         * @return роль
         */
        Role findByName(String name);
        }
