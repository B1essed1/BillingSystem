package shakh.billingsystem.services.implemenations;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import shakh.billingsystem.entities.Category;
import shakh.billingsystem.entities.Products;
import shakh.billingsystem.models.ProductCreateDto;
import shakh.billingsystem.repositories.CategoryRepository;
import shakh.billingsystem.repositories.ProductRepository;
import shakh.billingsystem.services.ProductService;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Products findProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Products save(Products products) {
        return productRepository.save(products);
    }

    @Override
    public Products convertToProduct(ProductCreateDto dto) throws NotFoundException {

        Category category = categoryRepository.findByCategory(dto.getCategory());

        if (category == null) throw new  NotFoundException("Bunday turdagi categoriya bazada mavjud emas, iltimos categoriyani qaytadan tekshirib, to'g'riligiga ishonch hosil qilib qaytadan urunib ki'ring");

        Products products = new Products();
        products.setPriceOfBuy(dto.getPriceOfBuy());
        products.setPriceOfSell(dto.getPriceOfSell());
        products.setProductName(dto.getName());
        products.setBarcode(dto.getBarcode());
        products.setCreatedTime(new Date());
        products.setCategory(category);
        products.setMeasureType(dto.getMeasureType());
        products.setAmount(0.);
        return products;
    }
}
