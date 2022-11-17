package shakh.billingsystem.services.implemenations;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shakh.billingsystem.entities.Category;
import shakh.billingsystem.models.CustomResponseDto;
import shakh.billingsystem.repositories.CategoryRepository;
import shakh.billingsystem.services.CategoryService;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public CustomResponseDto<?> createNewCategory(String category) {

        if ( categoryRepository.findByCategory(category).isPresent())
            return CustomResponseDto.builder().isError(true)
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
        return CustomResponseDto.builder().isError(false)
                .message(category + " muvaffaqiyatli saqlandi")
                .build();
    }
}
