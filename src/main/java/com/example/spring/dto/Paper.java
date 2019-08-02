/**
 *
 */
package com.example.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import de.bonprix.dto.Entity;
import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * @author p.maurya
 *
 */
@GeneratePojoBuilder(
    intoPackage = "*.builder")
@JsonIgnoreProperties(
    ignoreUnknown = true)
public class Paper /* extends Entity */ {

    /**
     *
     */
    private static final long serialVersionUID = -438527720516164989L;

    private PaperBrand paperBrand;

//    private PaperQuality paperQuality;

//    private PaperCertificate paperCertificate;

    private Grammage grammage;

    private Double paperVolume;

    public PaperBrand getPaperBrand() {
        return this.paperBrand;
    }

    public void setPaperBrand(PaperBrand paperBrand) {
        this.paperBrand = paperBrand;
    }

//    public PaperQuality getPaperQuality() {
//        return this.paperQuality;
//    }
//
//    public void setPaperQuality(PaperQuality paperQuality) {
//        this.paperQuality = paperQuality;
//    }
//
//    public PaperCertificate getPaperCertificate() {
//        return this.paperCertificate;
//    }
//
//    public void setPaperCertificate(PaperCertificate paperCertificate) {
//        this.paperCertificate = paperCertificate;
//    }

    public Grammage getGrammage() {
        return this.grammage;
    }

    public void setGrammage(Grammage grammage) {
        this.grammage = grammage;
    }

    public Double getPaperVolume() {
        return this.paperVolume;
    }

    public void setPaperVolume(Double paperVolume) {
        this.paperVolume = paperVolume;
    }

}
