package shakh.billingsystem.services.implemenations;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shakh.billingsystem.entities.Category;
import shakh.billingsystem.models.ApiResponse;
import shakh.billingsystem.repositories.CategoryRepository;
import shakh.billingsystem.services.CategoryService;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public ApiResponse<?> createNewCategory(String category) {

        if ( categoryRepository.findByCategory(category).isPresent())
            return ApiResponse.builder().isError(true)
                    .message(category+ " categoriyasi malumotlar omborida mavjud , qaytadan saqlay olmaysiz")
                    .build();
        else {
            try {
                Category newCategory = new Category();
                newCategory.setCategory(category);
                categoryRepository.save(newCategory);
            } catch (Exception e){
                throw new RuntimeException("Xatolik"+e);
            }
        }
        return ApiResponse.builder().isError(false)
                .message(category + " muvaffaqiyatli saqlandi")
                .build();
    }
}
