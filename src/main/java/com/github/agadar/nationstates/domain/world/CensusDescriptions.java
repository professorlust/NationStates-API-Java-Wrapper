package com.github.agadar.nationstates.domain.world;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A world's descriptions of the current or selected census.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CENSUSDESC")
public class CensusDescriptions {

    /**
     * Id of the census to which these descriptions belong.
     */
    @XmlAttribute(name = "id")
    public int id;

    /**
     * Description for nations, e.g. 'The following nations have...'
     */
    @XmlElement(name = "NDESC")
    public String descriptionForNations;

    /**
     * Description for regions, e.g. 'The following regions have...'
     */
    @XmlElement(name = "RDESC")
    public String descriptionForRegions;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CensusDescriptions other = (CensusDescriptions) obj;
        return this.id == other.id;
    }

}
