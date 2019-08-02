package com.example.spring.filter;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(
    intoPackage = "*.builder")
public class StatusFilter extends Paged implements IdsFilter {

    @QueryParam("filterIds")
    private List<Long> ids;

    @QueryParam("isActive")
    private Boolean isActive;

    public StatusFilter() {
        super(Paged.DEFAULT_PAGE, Paged.DEFAULT_PAGESIZE);
    }

    public StatusFilter(final Integer page, final Integer pageSize) {
        super(page, pageSize);
    }

    @Override
    public void setIds(final List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getIds() {
        if (this.ids == null) {
            this.ids = new ArrayList<>();
        }
        return this.ids;
    }

    /**
     * @return the isActive
     */
    public Boolean getIsActive() {
        return this.isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

}
