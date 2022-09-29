package shakh.billingsystem.services;

import shakh.billingsystem.entities.Debitors;
import shakh.billingsystem.models.DebitorsRegDto;

public interface DebitorsService {
    Debitors registerDebitors(DebitorsRegDto dto);
}
