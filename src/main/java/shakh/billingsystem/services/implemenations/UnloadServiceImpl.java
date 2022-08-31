package shakh.billingsystem.services.implemenations;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import shakh.billingsystem.entities.Products;
import shakh.billingsystem.entities.Unload;
import shakh.billingsystem.models.UnloadDto;
import shakh.billingsystem.repositories.UnloadRepository;
import shakh.billingsystem.services.ProductService;
import shakh.billingsystem.services.UnloadService;

import java.util.Collections;
import java.util.Date;

@Service
@AllArgsConstructor
public class UnloadServiceImpl implements UnloadService {
    private final ProductService productService;
    private final UnloadRepository unloadRepository;
    @Override
    public Unload unloadProduct(UnloadDto dto) {

        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        /**
         ********** TO DO **************
         *
         *
         * Should add admin who are saving this unload
         * it is not implemented
         * **/

        Products products = productService.findProductById(dto.getProductId());
        Unload unload = new Unload();
        unload.setAmount(dto.getAmount());
        unload.setCreatedTime(new Date());
        unload.setMeasureType(dto.getMeasureType());
        unload.setIsDeleted(false);
        unload.setPriceOfBuy(dto.getPriceOfBuy());
        unload.setPriceOfSell(dto.getPriceOfSell());

        products.setLastUpdatedTime(new Date());
        products.setPriceOfSell(dto.getPriceOfSell());
        products.setPriceOfBuy(dto.getPriceOfBuy());
        products.setUnloads(Collections.singletonList(unload));

        productService.save(products);

        unload.setProducts(products);
        unloadRepository.save(unload);

        return unload;
    }

}
