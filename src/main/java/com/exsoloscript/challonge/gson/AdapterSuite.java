package com.exsoloscript.challonge.gson;

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
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class AdapterSuite {

    private GsonBuilder gsonBuilder;
    private Map<Class, GsonAdapter> adapters;
    private Class[] excluded;

    private AdapterSuite(Class... excluded) {
        this.excluded = excluded;
        this.gsonBuilder = new GsonBuilder();

        this.adapters = createTypeAdapters();

        for (Map.Entry<Class, GsonAdapter> adapter : adapters.entrySet())
            gsonBuilder.registerTypeAdapter(adapter.getKey(), adapter.getValue());
    }

    public static Gson createGson(Class... excluded) {
        return new AdapterSuite(excluded).create();
    }

    private Gson create() {
        return gsonBuilder.create();
    }

    private Map<Class, Class> typeMappings() {
        Map<Class, Class> classes = new HashMap<>();

        // pojo
        classes.put(Tournament.class, TournamentAdapter.class);
        // enum
        classes.put(GrandFinalsModifier.class, GrandFinalsModifierAdapter.class);
        classes.put(RankedBy.class, RankedByAdapter.class);
        classes.put(TournamentState.class, TournamentStateAdapter.class);
        classes.put(TournamentType.class, TournamentTypeAdapter.class);
        classes.put(TournamentQueryState.class, TournamentQueryStateAdapter.class);
        // other
        classes.put(ZonedDateTime.class, ZonedDateTimeAdapter.class);

        return classes;
    }

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
            throw new IllegalStateException("Detected Gson adapter with no public default constructor: " + clazz.getName());
        }

        return adapter;
    }
}
