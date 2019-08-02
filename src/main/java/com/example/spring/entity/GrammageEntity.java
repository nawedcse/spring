/*
 *
 */
package de.bonprix.commerce.chain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.spring.dto.Grammage;
import com.example.spring.utils.BasicTableDtoConverter;


/**
 * The Class GrammageEntity.
 *
 * @author n.alam
 */
@GeneratePojoBuilder(
    intoPackage = "*.builder")
@Entity
@Table(/*
		 * name = DchainConstants.PB_GRAMMAGE, schema = DchainConstants.SCHEMA
		 */)
@SequenceGenerator(/*
					 * schema = DchainConstants.SCHEMA, name = DchainConstants.SEQ_GRAMMAGE,
					 * sequenceName = DchainConstants.SEQ_GRAMMAGE, allocationSize = 1
					 */)
public class GrammageEntity implements BasicTableDtoConverter<Grammage> {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7940263830042447473L;

    /** The id. */
    @Id
    @GeneratedValue(
        generator = DchainConstants.SEQ_GRAMMAGE,
        strategy = GenerationType.SEQUENCE)
    @Column(
        name = "PB_GRAMMAGE_ID")
    private Long id;

    /** The grammage. */
    @Column(
        name = "GRAMMAGE")
    private Double grammage;

    /** The unit id. */
    @Column(
        name = "MD_UNIT_ID")
    private Long unitId;

    /** The is deactived. */
    @Column(
        name = "IS_DEACTIVATED")
    private Boolean isDeactived;

    public Double getGrammage() {
        return this.grammage;
    }

    public void setGrammage(final Double grammage) {
        this.grammage = grammage;
    }

    /**
     * Gets the unit id.
     *
     * @return the unit id
     */
    public Long getUnitId() {
        return this.unitId;
    }

    /**
     * Sets the unit id.
     *
     * @param unitId the new unit id
     */
    public void setUnitId(final Long unitId) {
        this.unitId = unitId;
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

    /*
     * (non-Javadoc)
     * @see de.bonprix.service.BasicTableDtoConverter#updateDto(de.bonprix.dto.HasId)
     */
    @Override
    public void updateDto(final Grammage dto) {
        dto.setGrammage(getGrammage());
        dto.setUnit(getUnitId() != null ? new UnitBuilder().withId(getUnitId())
            .build() : null);
        dto.setIsDeactived(getIsDeactived());
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.service.BasicTableDtoConverter#updateEntity(de.bonprix.dto.HasId)
     */
    @Override
    public void updateEntity(final Grammage dto) {
        setGrammage(dto.getGrammage());
        setUnitId(dto.getUnit() != null ? dto.getUnit()
            .getId() : null);
        // setIsDeactived(!dto.getIsDeactived());
        setIsDeactived(dto.getIsDeactived());
    }

}
