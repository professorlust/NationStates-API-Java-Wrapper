package com.github.agadar.nationstates.domain.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Contains a nation's or region's statistics of the current or last zombie
 * event.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ZOMBIE")
public class ZombieInfo {

    /**
     * The action the nation undertook. Not used for regions.
     */
    @XmlElement(name = "ZACTION")
    public String action;

    /**
     * The number of survivors
     */
    @XmlElement(name = "SURVIVORS")
    public int survivors;

    /**
     * The number of zombies
     */
    @XmlElement(name = "ZOMBIES")
    public int zombies;

    /**
     * The number of dead people
     */
    @XmlElement(name = "DEAD")
    public int dead;
}
