package shakh.billingsystem.services;

import shakh.billingsystem.entities.Category;
import shakh.billingsystem.models.ApiResponse;

public interface CategoryService {
    ApiResponse createNewCategory(String category);
}
