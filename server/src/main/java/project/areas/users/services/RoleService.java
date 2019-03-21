package project.areas.users.services;

import project.areas.users.entities.Role;

public interface RoleService {
    Role getRoleByName(final String roleName);
}
