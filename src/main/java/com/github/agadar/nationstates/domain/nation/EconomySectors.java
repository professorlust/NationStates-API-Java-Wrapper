package com.github.agadar.nationstates.domain.nation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a nation's economy sectors. All values are percentages. All values
 * together should naturally equal 100.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SECTORS")
public class EconomySectors {

    /**
     * Percentage of the economy that is black market
     */
    @XmlElement(name = "BLACKMARKET")
    public double blackMarket;

    /**
     * Percentage of the economy that is government
     */
    @XmlElement(name = "GOVERNMENT")
    public double government;

    /**
     * Percentage of the economy that is privately owned
     */
    @XmlElement(name = "INDUSTRY")
    public double privateIndustry;

    /**
     * Percentage of the economy that is state-owned
     */
    @XmlElement(name = "PUBLIC")
    public double stateOwnedIndustry;
}
