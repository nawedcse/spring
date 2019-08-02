/*
 *
 */
package com.example.spring.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import de.bonprix.dto.NamedEntity;
import de.bonprix.global.businesspartner.dto.Company;
import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * The Class PaperBrand.
 *
 * @author n.alam
 */
@GeneratePojoBuilder(
    intoPackage = "*.builder")
@JsonIgnoreProperties(
    ignoreUnknown = true)
public class PaperBrand /* extends NamedEntity */ {

//	NamedEntity set String name and setter/getter and constructor
    /**
     *
     */
    private static final long serialVersionUID = -7499085540152689389L;

    /** The company id. */

    private Long companyId;

    /** The activate. */
    private Boolean isDeactived;

//    private PaperQuality paperQuality;  Dependency to other ENTITY

//    private PaperCertificate paperCertificate;        Dependency to other ENTITY

    private Set<Grammage> grammages;

    public Boolean getIsDeactived() {
        return this.isDeactived;
    }

    public void setIsDeactived(Boolean isDeactived) {
        this.isDeactived = isDeactived;
    }

    private Double paperVolume;

//    private Company company;     Dependency to other ENTITY

    private List<Paper> paperList;

    public List<Paper> getPaperList() {
        return this.paperList;
    }

    public void setPaperList(List<Paper> paperList) {
        this.paperList = paperList;
    }

//    public PaperQuality getPaperQuality() {
//        return this.paperQuality;
//    }

//    public void setPaperQuality(PaperQuality paperQuality) {
//        this.paperQuality = paperQuality;
//    }

//    public PaperCertificate getPaperCertificate() {
//        return this.paperCertificate;
//    }

//    public void setPaperCertificate(PaperCertificate paperCertificate) {
//        this.paperCertificate = paperCertificate;
//    }

    public Set<Grammage> getGrammages() {
        return this.grammages;
    }

    public void setGrammages(Set<Grammage> grammages) {
        this.grammages = grammages;
    }

    public Double getPaperVolume() {
        return this.paperVolume;
    }

    public void setPaperVolume(Double paperVolume) {
        this.paperVolume = paperVolume;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

//    public Company getCompany() {
//        return this.company;
//    }

//    public void setCompany(Company company) {
//        this.company = company;
//    }

}
