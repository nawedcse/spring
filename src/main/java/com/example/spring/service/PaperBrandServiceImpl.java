/*
 *
 */
package com.example.spring.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.dto.PaperBrand;
import com.example.spring.entity.PaperBrandEntity;
import com.example.spring.fetch.PaperBrandFetchOption;
import com.example.spring.filter.PaperBrandFilter;
import com.example.spring.jpa.PaperBrandRepository;

/**
 * The Class PaperBrandServiceImpl.
 *
 * @author n.alam
 */
@RestService
@Transactional
public class PaperBrandServiceImpl extends AbstractFetchService<PaperBrandEntity, PaperBrand, PaperBrandFilter, PaperBrandFetchOption, PaperBrandRepository>
        implements PaperBrandService {
    /** The paper brand repository. */
    @Resource
    private PaperBrandRepository paperBrandRepository;

    /**
     * Instantiates a new paper brand service impl.
     */
    public PaperBrandServiceImpl() {
        super(PaperBrandEntity.class, PaperBrand.class, PaperBrandFilter.class, PaperBrandFetchOption.class);
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.service.AbstractBasicService#getRepository()
     */
    @Override
    protected PaperBrandRepository getRepository() {
        return this.paperBrandRepository;
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.commerce.chain.service.PaperBrandService#findAll(de.bonprix.commerce.chain.filter.PaperBrandFilter)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_" + Roles.READ + "')")
    @Transactional
    public List<PaperBrand> findAll(final PaperBrandFilter filter, final PaperBrandFetchOption fetchOption) {
        return super.findAllDefault(filter, fetchOption);
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.commerce.chain.service.PaperBrandService#findById(java.lang.Long)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_" + Roles.READ + "')")
    public PaperBrand findById(final Long brandId, final PaperBrandFetchOption fetchOption) {
        return super.findByIdDefault(brandId, fetchOption);
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.commerce.chain.service.PaperBrandService#create(de.bonprix.commerce.chain.dto.PaperBrand)
     */
    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_" + Roles.WRITE + "')")
    public Long create(final PaperBrand paperBrand) {
        paperBrand.setIsDeactived(Boolean.FALSE);
        return super.createDefault(paperBrand);
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.commerce.chain.service.PaperBrandService#update(java.lang.Long, de.bonprix.commerce.chain.dto.PaperBrand)
     */
    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_" + Roles.WRITE + "')")
    public void update(final Long brandId, final PaperBrand paperBrand) {
        updateDefault(brandId, paperBrand);
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.commerce.chain.service.PaperBrandService#deleteById(java.lang.Long)
     */
    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_" + Roles.WRITE + "')")
    public void deleteById(final Long brandId) {
        super.deleteByIdDefault(brandId);
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.commerce.chain.service.PaperBrandService#activate(java.lang.Long)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_" + Roles.WRITE + "')")
    public void activate(final Long brandId) {
        final PaperBrand dto = super.findByIdDefault(brandId);
        dto.setIsDeactived(Boolean.FALSE);
        super.updateDefault(brandId, dto);
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.commerce.chain.service.PaperBrandService#deactivate(java.lang.Long)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_" + Roles.WRITE + "')")
    public void deactivate(final Long brandId) {

        final PaperBrand dto = super.findByIdDefault(brandId);
        dto.setIsDeactived(Boolean.TRUE);
        super.updateDefault(brandId, dto);

        /*
         * final PaperBrandEntity paperBrandEntity = this.paperBrandRepository.findOne(brandId); paperBrandEntity.setIsDeactived(Boolean.TRUE);
         * this.paperBrandRepository.save(paperBrandEntity);
         */

    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.commerce.chain.service.PaperBrandService#count(de.bonprix.commerce.chain.filter.PaperBrandFilter)
     */
    @Override
    @PreAuthorize("hasRole('ROLE_" + Roles.READ + "')")
    public int count(final PaperBrandFilter filter) {
        return getRepository().count(filter);
    }

    @Override
    protected PaperBrandEntity convertToEntity(final PaperBrandEntity paperBrandEntity, final PaperBrand paperBrand) {
        super.convertToEntity(paperBrandEntity, paperBrand);
        if (CollectionUtils.isNotEmpty(paperBrand.getPaperList())) {
            Set<PaperEntity> paperset = paperBrandEntity.getPaperEntities();
            if (paperset == null) {
                paperset = new HashSet<>();
                paperBrandEntity.setPaperEntities(paperset);
            }
            // paperset.clear();
            final Map<Long, PaperEntity> map = paperset.stream()
                .collect(Collectors.toMap(PaperEntity::getId, Function.identity()));
            for (final Paper paper : paperBrand.getPaperList()) {
                PaperEntity paperEntity = map.get(paper.getId());
                if (paperEntity == null) {
                    paperEntity = new PaperEntity();
                    paperEntity.setPaperBrandEntity(paperBrandEntity);
                    paperEntity.setGrammageEntity(new GrammageEntityBuilder().withId(paper.getGrammage()
                        .getId())
                        .build());
                    paperEntity.setPaperCertificateEntity(new PaperCertificateEntityBuilder().withId(paper.getPaperCertificate()
                        .getId())
                        .build());
                    paperEntity.setPaperQualityEntity(new PaperQualityEntityBuilder().withId(paper.getPaperQuality()
                        .getId())
                        .build());
                    paperEntity.setPaperVolume(paper.getPaperVolume());
                    paperset.add(paperEntity);
                }
                else {
                    DtoEntityConverterUtils.convertToEntity(paperEntity, paper);
                }
            }
            paperBrandEntity.setPaperEntities(paperset);
        }
        return paperBrandEntity;
    }
}
