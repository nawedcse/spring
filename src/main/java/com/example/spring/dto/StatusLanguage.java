package de.bonprix.product.producthub.dto;

import de.bonprix.dto.AbstractI18NLanguageElement;
import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(
    intoPackage = "*.builder")
public class StatusLanguage extends AbstractI18NLanguageElement {

    private static final long serialVersionUID = 3022180432697441758L;

    private String description;

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

}
