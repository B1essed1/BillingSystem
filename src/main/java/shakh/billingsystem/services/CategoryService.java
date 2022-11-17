package shakh.billingsystem.services;

import shakh.billingsystem.entities.Category;
import shakh.billingsystem.models.CustomResponseDto;

public interface CategoryService {
    CustomResponseDto  createNewCategory(String category);
}
