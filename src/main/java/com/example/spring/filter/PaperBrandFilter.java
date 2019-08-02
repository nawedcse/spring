/*
 *
 */
package com.example.spring.filter;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import de.bonprix.dto.IdsFilter;
import de.bonprix.model.Paged;
import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * The Class PaperBrandFilter.
 *
 * @author n.alam
 */
@GeneratePojoBuilder(
    intoPackage = "*.builder")
@JsonIgnoreProperties(
    ignoreUnknown = true)
public class PaperBrandFilter extends Paged implements IdsFilter {

    /** The paper brand ids. */
    @QueryParam("paperBrandIds")
    private List<Long> paperBrandIds;

    /** The is deactivated. */
    @QueryParam("isDeactivated")
    private Boolean isDeactivated;

    @QueryParam("name")
    private String name;

    public PaperBrandFilter() {
        super(Paged.DEFAULT_PAGE, Paged.DEFAULT_PAGESIZE);
    }

    /*
     * (non-Javadoc)
     * @see de.bonprix.dto.IdsFilter#setIds(java.util.List)
     */
    @Override
    public void setIds(final List<Long> ids) {
        setPaperBrandIds(ids);
    }

    /**
     * Gets the paper brand ids.
     *
     * @return the paper brand ids
     */
    public List<Long> getPaperBrandIds() {

        if (this.paperBrandIds == null) {
            this.paperBrandIds = new ArrayList<>();
        }
        return this.paperBrandIds;
    }

    /**
     * Sets the paper brand ids.
     *
     * @param paperBrandIds the new paper brand ids
     */
    public void setPaperBrandIds(final List<Long> paperBrandIds) {
        this.paperBrandIds = paperBrandIds;
    }

    /**
     * Gets the checks if is deactivated.
     *
     * @return the checks if is deactivated
     */
    public Boolean getIsDeactivated() {
        return this.isDeactivated;
    }

    /**
     * Sets the checks if is deactivated.
     *
     * @param isDeactivated the new checks if is deactivated
     */
    public void setIsDeactivated(final Boolean isDeactivated) {
        this.isDeactivated = isDeactivated;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
