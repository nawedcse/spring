/**
 *
 */
package com.example.spring.fetch;

import javax.ws.rs.QueryParam;

import de.bonprix.dto.FetchAllOptions;
import net.karneim.pojobuilder.GeneratePojoBuilder;

/**
 * @author p.maurya
 *
 */
@GeneratePojoBuilder(
    intoPackage = "*.builder")
public class PaperBrandFetchOption implements FetchAllOptions {

    @QueryParam("isPaper")
    private Boolean isPaper = Boolean.FALSE;

    @Override
    public void setFetchAll() {
        setIsPaper(true);
    }

    public Boolean getIsPaper() {
        return this.isPaper;
    }

    public void setIsPaper(Boolean isPaper) {
        this.isPaper = isPaper;
    }

}
