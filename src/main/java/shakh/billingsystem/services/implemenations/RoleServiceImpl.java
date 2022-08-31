package shakh.billingsystem.services.implemenations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shakh.billingsystem.entities.Roles;
import shakh.billingsystem.models.RegistrationDto;
import shakh.billingsystem.repositories.RoleRepository;
import shakh.billingsystem.services.RoleService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Roles castToRole(RegistrationDto dto) {
        Roles role;
        Optional<Roles> ifExist = roleRepository.findRolesByName(dto.getRoleName());
        if (ifExist.isPresent()) role = ifExist.get();
           else {
            role = new Roles();
            role.setName(dto.getRoleName());
        }
        return role;
    }
}
