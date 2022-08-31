package shakh.billingsystem.services;

import shakh.billingsystem.entities.Roles;
import shakh.billingsystem.models.RegistrationDto;

public interface RoleService {
    Roles castToRole(RegistrationDto dto);
}
