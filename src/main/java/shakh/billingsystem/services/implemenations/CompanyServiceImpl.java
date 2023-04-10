package shakh.billingsystem.services.implemenations;

import org.springframework.stereotype.Service;
import shakh.billingsystem.entities.Company;
import shakh.billingsystem.models.ApiResponse;
import shakh.billingsystem.models.CompanyRegDto;
import shakh.billingsystem.repositories.CompanyRepository;
import shakh.billingsystem.services.CompanyService;

import java.util.Date;
import java.util.Objects;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public ApiResponse<?> registerCompany(CompanyRegDto reg) {

        if (repository.findByName(reg.getName()).isPresent()) {
            return ApiResponse.builder()
                    .message("This Company is already exists")
                    .isError(true)
                    .build();
        }
        if (reg.getSalt().isEmpty())
            return ApiResponse.builder()
                    .message("Salt key is empty")
                    .isError(true)
                    .build();

        Company company = new Company();
        company.setName(reg.getName());
        company.setSalt(reg.getSalt());
        company.setCreatedTime(new Date());

        if (Objects.isNull(reg.getIsPayed()))
            company.setIsPayed(false);
        else {
            company.setIsPayed(true);
            company.setPaymentTime(new Date());
        }

        if (Objects.isNull(reg.getIsActive()))
            company.setIsActive(false);
        else
            company.setIsActive(true);

        return ApiResponse.builder()
                .message("success")
                .isError(false)
                .data(company)
                .build();
    }
}
