package com.github.agadar.nationstates.happeningspecializer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import com.github.agadar.nationstates.NationStatesAPIException;
import com.github.agadar.nationstates.domain.common.happening.EmbassyHappening;
import com.github.agadar.nationstates.domain.common.happening.Happening;
import com.github.agadar.nationstates.enumerator.EmbassyHappeningType;

/**
 * Specializes generic Happenings to EmbassyHappenings.
 * 
 * @author Agadar (https://github.com/Agadar/)
 *
 */
public class EmbassyHappeningSpecializer implements IHappeningSpecializer<EmbassyHappening> {

    private final Map<EmbassyHappeningType, String[]> texts = new HashMap<>();
    private final Map<EmbassyHappeningType, BiFunction<Happening, EmbassyHappeningType, EmbassyHappening>> functions = new HashMap<>();

    public EmbassyHappeningSpecializer() {
	texts.put(EmbassyHappeningType.CONSTRUCTION_ABORTED, new String[] { "aborted construction of embassies between",
		"Construction of embassies aborted between" });
	texts.put(EmbassyHappeningType.AGREED_TO_CONSTRUCT, new String[] { "agreed to construct embassies between" });
	texts.put(EmbassyHappeningType.EMBASSY_ESTABLISHED, new String[] { "Embassy established between", "EO:" });
	texts.put(EmbassyHappeningType.ORDERED_CLOSURE, new String[] { "ordered the closure of embassies between" });
	texts.put(EmbassyHappeningType.PROPOSED_CONSTRUCTION,
		new String[] { "proposed constructing embassies between" });
	texts.put(EmbassyHappeningType.REJECTED_REQUEST, new String[] { "rejected a request from" });
	texts.put(EmbassyHappeningType.WITHDREW_REQUEST, new String[] { "withdrew a request for embassies between" });
	texts.put(EmbassyHappeningType.CANCELLED_CLOSURE,
		new String[] { "cancelled the closure of embassies between" });
	texts.put(EmbassyHappeningType.EMBASSY_CANCELLED, new String[] { "Embassy cancelled between", "EC:" });

	functions.put(EmbassyHappeningType.AGREED_TO_CONSTRUCT, this::happeningWithNation);
	functions.put(EmbassyHappeningType.ORDERED_CLOSURE, this::happeningWithNation);
	functions.put(EmbassyHappeningType.PROPOSED_CONSTRUCTION, this::happeningWithNation);
	functions.put(EmbassyHappeningType.REJECTED_REQUEST, this::happeningWithNation);
	functions.put(EmbassyHappeningType.WITHDREW_REQUEST, this::happeningWithNation);
	functions.put(EmbassyHappeningType.CANCELLED_CLOSURE, this::happeningWithNation);
	functions.put(EmbassyHappeningType.EMBASSY_CANCELLED, this::happeningFromRegionalHistory);
	functions.put(EmbassyHappeningType.EMBASSY_ESTABLISHED, this::happeningFromRegionalHistory);
	functions.put(EmbassyHappeningType.CONSTRUCTION_ABORTED, this::constructionAborted);
    }

    @Override
    public boolean isOfSpecializedType(Happening happening) {
	if (happening.description == null) {
	    return false;
	}
	return texts.values().stream()
		.anyMatch((texts) -> Stream.of(texts).anyMatch(text -> happening.description.contains(text)));
    }

    @Override
    public EmbassyHappening toSpecializedType(Happening happening) {
	return texts.entrySet().stream()
		.filter((entry) -> Stream.of(entry.getValue()).anyMatch(text -> happening.description.contains(text)))
		.map(entry -> functions.get(entry.getKey()).apply(happening, entry.getKey())).findAny().orElse(null);
    }

    /**
     * Generic handler for embassy happenings that may have come from the regional
     * history list, which do not contain a second region nor an acting nation.
     * 
     * @param happening
     * @param embassyHappeningType
     *            Should always be either EmbassyHappeningType.EMBASSY_CANCELLED or
     *            EmbassyHappeningType.EMBASSY_ESTABLISHED.
     * @return
     */
    private EmbassyHappening happeningFromRegionalHistory(Happening happening,
	    EmbassyHappeningType embassyHappeningType) {
	final String[] texts = this.texts.get(embassyHappeningType);

	if (happening.description.contains(texts[0])) {
	    return this.happeningWithoutNation(happening, embassyHappeningType);
	} else if (happening.description.contains(texts[1])) {
	    final String[] splitOnColon = happening.description.split(":");
	    final String region1 = splitOnColon[1].substring(0, splitOnColon[1].length() - 1);
	    return new EmbassyHappening(happening.id, happening.timestamp, happening.description, null, region1, null,
		    embassyHappeningType);
	}
	throw new NationStatesAPIException("Unsupported happening description for the embassy happening type");

    }

    /**
     * Special handler for 'construction aborted' happenings.
     * 
     * @param happening
     * @param embassyHappeningType
     *            Should always be of type
     *            EmbassyHappeningType.CONSTRUCTION_ABORTED.
     * @return
     */
    private EmbassyHappening constructionAborted(Happening happening, EmbassyHappeningType embassyHappeningType) {
	final String[] texts = this.texts.get(embassyHappeningType);
	if (happening.description.contains(texts[0])) {
	    return this.happeningWithNation(happening, embassyHappeningType);
	} else if (happening.description.contains(texts[1])) {
	    return this.happeningWithoutNation(happening, embassyHappeningType);
	}
	throw new NationStatesAPIException("Unsupported happening description for the embassy happening type");
    }

    /**
     * Generic handler for embassy happenings in which the acting nation is
     * mentioned.
     * 
     * @param happening
     * @param embassyHappeningType
     * @return
     */
    private EmbassyHappening happeningWithNation(Happening happening, EmbassyHappeningType embassyHappeningType) {
	final String[] splitOnAt = happening.description.split("@@");
	final String nation = splitOnAt[1];
	final String[] splitOnPercent = splitOnAt[2].split("%%");
	final String region1 = splitOnPercent[1];
	final String region2 = splitOnPercent[3];
	return new EmbassyHappening(happening.id, happening.timestamp, happening.description, nation, region1, region2,
		embassyHappeningType);
    }

    /**
     * Generic handler for embassy happenings in which the acting nation is NOT
     * mentioned or not applicable and thus absent.
     * 
     * @param happening
     * @param embassyHappeningType
     * @return
     */
    private EmbassyHappening happeningWithoutNation(Happening happening, EmbassyHappeningType embassyHappeningType) {
	final String[] splitOnPercent = happening.description.split("%%");
	final String region1 = splitOnPercent[1];
	final String region2 = splitOnPercent[3];
	return new EmbassyHappening(happening.id, happening.timestamp, happening.description, null, region1, region2,
		embassyHappeningType);
    }

}
