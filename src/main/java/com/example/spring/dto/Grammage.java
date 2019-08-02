/*
 *
 */
package com.example.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import de.bonprix.dto.Entity;
import de.bonprix.global.masterdata.dto.Unit;
import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * The Class Grammage.
 *
 * @author n.alam
 */
@GeneratePojoBuilder(
    intoPackage = "*.builder")
@JsonIgnoreProperties(
    ignoreUnknown = true)
public class Grammage /* extends Entity */ {

    /** The grammage. */
    private Double grammage;

//    private Unit unit;

    /** The activate. */
    private Boolean isDeactived;

    public Double getGrammage() {
        return this.grammage;
    }

    public void setGrammage(final Double grammage) {
        this.grammage = grammage;
    }

    public Boolean getIsDeactived() {
        return this.isDeactived;
    }

    public void setIsDeactived(Boolean isDeactived) {
        this.isDeactived = isDeactived;
    }

//    public Unit getUnit() {
//        return this.unit;
//    }
//
//    public void setUnit(final Unit unit) {
//        this.unit = unit;
//    }

}
