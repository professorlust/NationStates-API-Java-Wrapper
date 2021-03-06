package com.github.agadar.nationstates.enumerator;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Enumerator for the different statuses a regional message can have.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public enum RegionalMessageStatus {

    NULL(-1),
    VISIBLE(0),
    SUPPRESSED_BUT_VIEWABLE(1),
    DELETED_BY_AUTHOR(2),
    SUPPRESSED_BY_MODERATOR(9);

    /**
     * The integer representation of this RegionalMessageStatus.
     */
    private final int intValue;

    /**
     * Map for reverse look-up.
     */
    private final static Map<Integer, RegionalMessageStatus> INTS_TO_ENUMS;

    /**
     * Static 'constructor' for filling the reverse map.
     */
    static {
        INTS_TO_ENUMS = new HashMap<>();
        for (RegionalMessageStatus regionalMessageStatus : values()) {
            INTS_TO_ENUMS.put(regionalMessageStatus.intValue, regionalMessageStatus);
        }
    }

    /**
     * Returns the integer representation of this RegionalMessageStatus.
     *
     * @return the integer representation of this RegionalMessageStatus.
     */
    public int toInt() {
        return intValue;
    }

    /**
     * Returns the RegionalMessageStatus represented by the supplied integer.
     *
     * @param intValue the supplied integer.
     * @return the RegionalMessageStatus represented by the supplied integer.
     */
    public static RegionalMessageStatus fromInt(int intValue) {
        if (!INTS_TO_ENUMS.containsKey(intValue)) {
            return RegionalMessageStatus.NULL;
        }
        return INTS_TO_ENUMS.get(intValue);
    }

    /**
     * Instantiates a new RegionalMessageStatus, represented by the supplied
     * integer.
     *
     * @param intValue the supplied integer.
     */
    private RegionalMessageStatus(int intValue) {
        this.intValue = intValue;
    }

    /**
     * Converts an integer to a RegionalMessageStatus enum value and vice versa.
     */
    public static class Adapter extends XmlAdapter<Integer, RegionalMessageStatus> {

        @Override
        public RegionalMessageStatus unmarshal(Integer v) {
            return RegionalMessageStatus.fromInt(v);
        }

        @Override
        public Integer marshal(RegionalMessageStatus v) {
            return v.intValue;
        }
    }
}
