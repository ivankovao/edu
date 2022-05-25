package ru.web.app.repo;

import org.springframework.data.repository.CrudRepository;
import ru.web.app.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
