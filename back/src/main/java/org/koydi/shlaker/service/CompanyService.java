package org.koydi.shlaker.service;

import lombok.val;
import org.koydi.shlaker.entity.Company;
import org.koydi.shlaker.exception.BadRequestException;
import org.koydi.shlaker.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

class CompanyNotFound extends BadRequestException {

    CompanyNotFound(String message) {
        super(message);
    }
}

@Service
@Transactional
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Set<Company> getAllCompanies() {
        val companies = companyRepository.getAllCompanies();
        return companies;
    }
}
