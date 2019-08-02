package com.example.spring.dto;

import de.bonprix.dto.AbstractI18NLanguageContainer;
import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(
	    intoPackage = "*.builder")
	@JsonIgnoreProperties(
	    ignoreUnknown = true)
public class Status extends Entity  {

    /**
     *
     */
    private static final long serialVersionUID = 6317237946661863405L;

    private Boolean isDeactivated;

    /**
     * @return the isDeactivated
     */
    public Boolean getIsDeactivated() {
        return this.isDeactivated;
    }

    /**
     * @param isDeactivated the isDeactivated to set
     */
    public void setIsDeactivated(final Boolean isDeactivated) {
        this.isDeactivated = isDeactivated;
    }

}