package shakh.billingsystem.services;

import shakh.billingsystem.entities.Debitors;
import shakh.billingsystem.models.DebitorsRegDto;

import java.util.Map;

public interface DebitorsService {
    Debitors registerDebitors(DebitorsRegDto dto);
    Map<String, Object> getDebitors(int page , int size);
}
