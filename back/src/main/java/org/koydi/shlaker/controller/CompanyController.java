package org.koydi.shlaker.controller;

import org.koydi.shlaker.dto.CompanyDto;
import org.koydi.shlaker.entity.Company;
import org.koydi.shlaker.mapper.CompanyMapper;
import org.koydi.shlaker.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/list")
    public List<CompanyDto> getAllCompanies() {
        Set<Company> companies = companyService.getAllCompanies();
        return companyMapper.toShortCompanyDtos(companies);
    }
}
