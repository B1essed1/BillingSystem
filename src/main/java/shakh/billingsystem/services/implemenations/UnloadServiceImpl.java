package shakh.billingsystem.services.implemenations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shakh.billingsystem.entities.Admins;
import shakh.billingsystem.entities.Products;
import shakh.billingsystem.entities.Unload;
import shakh.billingsystem.models.UnloadDto;
import shakh.billingsystem.repositories.AdminRepository;
import shakh.billingsystem.repositories.UnloadRepository;
import shakh.billingsystem.services.ProductService;
import shakh.billingsystem.services.UnloadService;
import shakh.billingsystem.utilities.Constants;

import java.util.Collections;
import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class UnloadServiceImpl implements UnloadService {
    private final ProductService productService;
    private final AdminRepository adminRepository;
    private final UnloadRepository unloadRepository;
    @Override
    @Transactional
    public Unload unloadProduct(UnloadDto dto) {

        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Admins admin = adminRepository.findAdminsByUsername(user).get();

        //Admins admin = Constants.getCurrentLoggedInUser();
        Products products = productService.findProductById(dto.getProductId());
        Unload unload = new Unload();
        unload.setAmount(dto.getAmount());
        unload.setCreatedTime(new Date());
        unload.setMeasureType(dto.getMeasureType());
        unload.setIsDeleted(false);
        unload.setPriceOfBuy(dto.getPriceOfBuy());
        unload.setPriceOfSell(dto.getPriceOfSell());
        unload.setBarcode(products.getBarcode());

        products.setLastUpdatedTime(new Date());
        products.setPriceOfSell(dto.getPriceOfSell());
        products.setAmount(products.getAmount() + dto.getAmount());
        products.setPriceOfBuy(dto.getPriceOfBuy());

        productService.save(products);

        unload.setAdmin(admin);
        unload.setProducts(products);
        unloadRepository.save(unload);

        return unload;
    }

}
