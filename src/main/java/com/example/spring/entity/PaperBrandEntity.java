/*
 *
 */
package com.example.spring.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.util.CollectionUtils;

import com.example.spring.dto.Paper;
import com.example.spring.dto.PaperBrand;
import com.example.spring.fetch.PaperBrandFetchOption;
import com.example.spring.utils.FetchTableDtoConverter;

/**
 * The Class PaperBrandEntity.
 *
 * @author n.alam
 */
@GeneratePojoBuilder(
    intoPackage = "*.builder")
@Entity
@Table(
    name = DchainConstants.PB_PAPER_BRAND,
    schema = DchainConstants.SCHEMA)
@SequenceGenerator(
    schema = DchainConstants.SCHEMA,
    name = DchainConstants.SEQ_PAPER_BRAND,
    sequenceName = DchainConstants.SEQ_PAPER_BRAND,
    allocationSize = 1)
public class PaperBrandEntity implements FetchTableDtoConverter<PaperBrand, PaperBrandFetchOption> {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5782955388555656818L;

    /** The id. */
    @Id
    @GeneratedValue(
        generator = DchainConstants.SEQ_PAPER_BRAND,
        strategy = GenerationType.SEQUENCE)
    @Column(
        name = "PB_PAPERBRAND_ID")
    private Long id;

    /** The company id. */
    @Column(
        name = "BP_COMPANY_ID")
    private Long companyId;

    /** The name. */
    @Column(
        name = "NAME")
    private String name;

    /** The is deactived. */
    @Column(
        name = "IS_DEACTIVATED")
    private Boolean isDeactived;

    @OneToMany(
        mappedBy = "paperBrandEntity",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true)

    private Set<PaperEntity> paperEntities;

    /**
     * Gets the company id.
     *
     * @return the company id
     */
    public Long getCompanyId() {
        return this.companyId;
    }

    /**
     * Sets the company id.
     *
     * @param companyId the new company id
     */
    public void setCompanyId(final Long companyId) {
        this.companyId = companyId;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the checks if is deactived.
     *
     * @return the checks if is deactived
     */
    public Boolean getIsDeactived() {
        return this.isDeactived;
    }

    /**
     * Sets the checks if is deactived.
     *
     * @param isDeactived the new checks if is deactived
     */
    public void setIsDeactived(final Boolean isDeactived) {
        this.isDeactived = isDeactived;
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.dto.HasId#setId(java.lang.Long)
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.dto.Identifiable#getId()
     */
    @Override
    public Long getId() {
        return this.id;
    }

    public Set<PaperEntity> getPaperEntities() {
        return this.paperEntities;
    }

    public void setPaperEntities(Set<PaperEntity> paperEntities) {
        this.paperEntities = paperEntities;
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.service.BasicTableDtoConverter#updateDto(de.bonprix.dto.HasId)
     */
    @Override
    public void updateDto(final PaperBrand dto) {
        updateDto(dto, new PaperBrandFetchOption());
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.service.BasicTableDtoConverter#updateEntity(de.bonprix.dto.HasId)
     */
    @Override
    public void updateEntity(final PaperBrand dto) {
        setCompanyId(dto.getCompanyId());
        setName(dto.getName());
        setIsDeactived(dto.getIsDeactived());
    }

    @Override
    public void updateDto(PaperBrand dto, PaperBrandFetchOption fetchOptions) {
        dto.setCompanyId(getCompanyId());
        dto.setName(getName());
        dto.setIsDeactived(getIsDeactived());
        if (fetchOptions.getIsPaper()) {
            dto.setPaperList(getPapers());
        }
    }

    private List<Paper> getPapers() {
        List<Paper> papers = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(getPaperEntities())) {
            papers = getPaperEntities().stream()
                .map(e -> DtoEntityConverterUtils.convertToDto(e, Paper.class))
                .collect(Collectors.toList());
        }
        return papers;
    }

}
