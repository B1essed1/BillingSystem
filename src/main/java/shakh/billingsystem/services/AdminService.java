package shakh.billingsystem.services;

import shakh.billingsystem.entities.Admins;
import shakh.billingsystem.entities.ReserveAdmin;
import shakh.billingsystem.models.RegistrationDto;

public interface AdminService {
    ReserveAdmin castToAdmin(RegistrationDto dto);
    void save(Admins admins);

    Admins castToAdmin(ReserveAdmin reserve)    ;
}
