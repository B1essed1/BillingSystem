package shakh.billingsystem.services;

import shakh.billingsystem.entities.Company;
import shakh.billingsystem.models.ApiResponse;
import shakh.billingsystem.models.CompanyRegDto;

public interface CompanyService {
    ApiResponse<?> registerCompany(CompanyRegDto reg);
}
