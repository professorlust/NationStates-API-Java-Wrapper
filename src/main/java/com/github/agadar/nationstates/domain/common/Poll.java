package com.github.agadar.nationstates.domain.common;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A regional poll.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "POLL")
public class Poll {

    /**
     * The poll's id.
     */
    @XmlAttribute(name = "id")
    public int id;

    /**
     * The poll's title.
     */
    @XmlElement(name = "TITLE")
    public String title;

    /**
     * The poll's description.
     */
    @XmlElement(name = "TEXT")
    public String description;

    /**
     * The name of the region the poll is in.
     */
    @XmlElement(name = "REGION")
    public String region;

    /**
     * UNIX timestamp on which the poll opened.
     */
    @XmlElement(name = "START")
    public long openedOn;

    /**
     * UNIX timestamp on which the poll will close.
     */
    @XmlElement(name = "STOP")
    public long closingOn;

    /**
     * Name of the nation that opened the poll.
     */
    @XmlElement(name = "AUTHOR")
    public String authorName;

    /**
     * The possible options for this poll.
     */
    @XmlElementWrapper(name = "OPTIONS")
    @XmlElement(name = "OPTION")
    public Set<PollOption> options;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
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
        final Poll other = (Poll) obj;
        return this.id == other.id;
    }

}
