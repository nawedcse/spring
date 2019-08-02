/**
 *
 */
package com.example.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.spring.dto.Paper;
import com.example.spring.utils.BasicTableDtoConverter;

import de.bonprix.commerce.chain.model.PaperBrandEntity;



/**
 * @author p.maurya
 *
 */
@GeneratePojoBuilder(
    intoPackage = "*.builder")
@Entity
@Table(/*
		 * name = DchainConstants.PB_PAPER, schema = DchainConstants.SCHEMA
		 */)
@SequenceGenerator(/*
					 * schema = DchainConstants.SCHEMA, name = DchainConstants.SEQ_PAPER,
					 * sequenceName = DchainConstants.SEQ_PAPER, allocationSize = 1
					 */)
public class PaperEntity implements BasicTableDtoConverter<Paper> {

    /**
     *
     */
    private static final long serialVersionUID = 2318997792885820911L;

    @Id
    @GeneratedValue(
        generator = DchainConstants.SEQ_PAPER,
        strategy = GenerationType.SEQUENCE)
    @Column(
        name = "PB_PAPER")
    private Long id;

//    @OneToOne(
//        fetch = FetchType.LAZY)
//    @JoinColumn(
//        name = "PB_PAPERQUALITY_ID")
//    private PaperQualityEntity paperQualityEntity;

//    @OneToOne(
//        fetch = FetchType.LAZY)
//    @JoinColumn(
//        name = "PB_PAPERCERTIFICATE_ID")
//    private PaperCertificateEntity paperCertificateEntity;

//    @OneToOne(
//        fetch = FetchType.LAZY)
//    @JoinColumn(
//        name = "PB_GRAMMAGE_ID")
//    private GrammageEntity grammageEntity;

    @Column(
        name = "PAPER_VOLUME")
    private Double paperVolume;

    @ManyToOne(
        fetch = FetchType.LAZY)
    @JoinColumn(
        name = "PB_PAPERBRAND_ID")
    private PaperBrandEntity paperBrandEntity;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public PaperBrandEntity getPaperBrandEntity() {
        return this.paperBrandEntity;
    }

    public void setPaperBrandEntity(PaperBrandEntity paperBrandEntity) {
        this.paperBrandEntity = paperBrandEntity;
    }

//    public PaperQualityEntity getPaperQualityEntity() {
//        return this.paperQualityEntity;
//    }

//    public void setPaperQualityEntity(PaperQualityEntity paperQualityEntity) {
//        this.paperQualityEntity = paperQualityEntity;
//    }

//    public PaperCertificateEntity getPaperCertificateEntity() {
//        return this.paperCertificateEntity;
//    }

//    public void setPaperCertificateEntity(PaperCertificateEntity paperCertificateEntity) {
//        this.paperCertificateEntity = paperCertificateEntity;
//    }

//    public GrammageEntity getGrammageEntity() {
//        return this.grammageEntity;
//    }

//    public void setGrammageEntity(GrammageEntity grammageEntity) {
//        this.grammageEntity = grammageEntity;
//    }

    public Double getPaperVolume() {
        return this.paperVolume;
    }

    public void setPaperVolume(Double paperVolume) {
        this.paperVolume = paperVolume;
    }

    @Override
    public void updateDto(Paper dto) {
//        dto.setGrammage(getGrammageEntity() != null ? new GrammageBuilder().withId(getGrammageEntity().getId())
//            .build() : null);
        dto.setPaperBrand(getPaperBrandEntity() != null ? new PaperBrandBuilder().withId(getPaperBrandEntity().getId())
            .build() : null);
//        dto.setPaperQuality(getPaperQualityEntity() != null ? new PaperQualityBuilder().withId(getPaperQualityEntity().getId())
//            .build() : null);
//        dto.setPaperCertificate(getPaperCertificateEntity() != null ? new PaperCertificateBuilder().withId(getPaperCertificateEntity().getId())
//            .build() : null);
        dto.setPaperVolume(getPaperVolume());

    }

    @Override
    public void updateEntity(Paper dto) {
//        setGrammageEntity(dto != null && dto.getGrammage() != null ? new GrammageEntityBuilder().withId(dto.getGrammage()
//            .getId())
//            .build() : null);
//        setPaperQualityEntity(dto != null && dto.getPaperQuality() != null ? new PaperQualityEntityBuilder().withId(dto.getPaperQuality()
//            .getId())
//            .build() : null);
//        setPaperCertificateEntity(dto != null && dto.getPaperCertificate() != null ? new PaperCertificateEntityBuilder().withId(dto.getPaperCertificate()
//            .getId())
//            .build() : null);
        setPaperVolume(dto != null && dto.getPaperVolume() != null ? dto.getPaperVolume() : null);
    }

}
