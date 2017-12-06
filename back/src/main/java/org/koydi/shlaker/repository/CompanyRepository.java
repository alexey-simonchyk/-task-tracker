package org.koydi.shlaker.repository;

import org.koydi.shlaker.entity.Company;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

    @EntityGraph(value = "withDevelopers", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select company from Company company where company.id = :companyId")
    Company getCompanyWithDevelopers(@Param("companyId") String companyId);

    @Query("select company from Company company")
    Set<Company> getAllCompanies();
}
