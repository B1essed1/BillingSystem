package shakh.billingsystem.services;

import shakh.billingsystem.entities.Unload;
import shakh.billingsystem.models.UnloadDto;

public interface UnloadService {
    Unload unloadProduct(UnloadDto dto);
}
