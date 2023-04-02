package shakh.billingsystem.services;

import shakh.billingsystem.entities.Admins;
import shakh.billingsystem.entities.ReserveAdmin;
import shakh.billingsystem.models.ApiResponse;
import shakh.billingsystem.models.ConfirmDto;
import shakh.billingsystem.models.RegistrationDto;

public interface AdminService {
    ApiResponse register(RegistrationDto dto);
    void save(Admins admins);

    Admins castToAdmin(RegistrationDto reserve);

    ApiResponse confirm(ConfirmDto dto);
}
