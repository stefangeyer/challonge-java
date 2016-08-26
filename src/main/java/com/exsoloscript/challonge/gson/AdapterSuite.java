package com.exsoloscript.challonge.gson;

import com.exsoloscript.challonge.model.Attachment;
import com.exsoloscript.challonge.model.Match;
import com.exsoloscript.challonge.model.Participant;
import com.exsoloscript.challonge.model.Tournament;
import com.exsoloscript.challonge.model.enumeration.RankedBy;
import com.exsoloscript.challonge.model.enumeration.TournamentState;
import com.exsoloscript.challonge.model.enumeration.TournamentType;
import com.exsoloscript.challonge.model.enumeration.query.GrandFinalsModifier;
import com.exsoloscript.challonge.model.enumeration.query.TournamentQueryState;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Collects the type adapters and creates a Gson object with them
 *
 * @author EXSolo
 * @version 20160819.1
 */
public class AdapterSuite {

    private GsonBuilder gsonBuilder;
    private Map<Class, GsonAdapter> adapters;
    private Class[] excluded;

    /**
     * Collect the adapters
     *
     * @param excluded Exclude a type adapter that was registered in the {@link #typeMappings()} method
     */
    private AdapterSuite(Class... excluded) {
        this.excluded = excluded;
        this.gsonBuilder = new GsonBuilder();

        this.adapters = createTypeAdapters();

        for (Map.Entry<Class, GsonAdapter> adapter : adapters.entrySet())
            gsonBuilder.registerTypeAdapter(adapter.getKey(), adapter.getValue());
    }

    /**
     * Creates the Gson object with the registered type adapters
     *
     * @param excluded Classes that will be given to the constructor {@link AdapterSuite#AdapterSuite(Class[])}
     * @return Gson
     */
    public static Gson createGson(Class... excluded) {
        return new AdapterSuite(excluded).create();
    }

    /**
     * Creates a Gson object from the builder
     *
     * @return Gson
     */
    private Gson create() {
        return gsonBuilder.create();
    }

    /**
     * Type mappings for the Builder. Each mapping contains the class of the object that should be created and the class of the adapter
     *
     * @return mappings
     */
    private Map<Class, Class> typeMappings() {
        Map<Class, Class> classes = new HashMap<>();

        // pojo
        classes.put(Tournament.class, TournamentAdapter.class);
        classes.put(Participant.class, ParticipantAdapter.class);
        classes.put(Match.class, MatchAdapter.class);
        classes.put(Attachment.class, AttachmentAdapter.class);
        // enum
        classes.put(GrandFinalsModifier.class, GrandFinalsModifierAdapter.class);
        classes.put(RankedBy.class, RankedByAdapter.class);
        classes.put(TournamentState.class, TournamentStateAdapter.class);
        classes.put(TournamentType.class, TournamentTypeAdapter.class);
        classes.put(TournamentQueryState.class, TournamentQueryStateAdapter.class);
        // other
        classes.put(OffsetDateTime.class, OffsetDateTimeAdapter.class);

        return classes;
    }

    /**
     * Initialize the type adapters. By initializing the type adapters late, the {@link AdapterSuite} cal be used in the adapters.
     *
     * @return initialized type adapters
     */
    private Map<Class, GsonAdapter> createTypeAdapters() {
        // use class values so constructor isnt called before excluding --> no stack overflow
        Map<Class, Class> classes = typeMappings();
        Map<Class, GsonAdapter> adapters = new HashMap<>();

        for (Class anExcluded : excluded) {
            classes.remove(anExcluded);
        }

        for (Map.Entry<Class, Class> entry : classes.entrySet()) {
            adapters.put(entry.getKey(), createDefaultInstance(entry.getValue()));
        }

        return adapters;
    }

    /**
     * Call default constructor of given class and return the instance.
     *
     * @param clazz Class
     * @return GsonAdapter
     * @throws IllegalStateException No default constructor found
     */
    private GsonAdapter createDefaultInstance(Class clazz) {
        Constructor[] ctors = clazz.getDeclaredConstructors();
        Constructor ctor = null;

        for (Constructor ctor1 : ctors) {
            ctor = ctor1;
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }

        GsonAdapter adapter;

        try {
            adapter = (GsonAdapter) ctor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalStateException("Detected Gson adapter with no public default constructor: " + clazz.getName());
        }

        return adapter;
    }
}
