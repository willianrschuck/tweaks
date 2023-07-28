package com.willianschuck.events;

import com.willianschuck.events.listeners.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EventManager {

    private static final Map<Class<? extends Listener>, List<? extends Listener>> listenersByType = new HashMap<>();

    public static <L extends Listener, E extends Event<L>> void fire(E event) {
        Class<L> type = event.getListenerType();

        @SuppressWarnings("unchecked")
        List<L> listeners = (List<L>) listenersByType.get(type);

        if (listeners == null || listeners.isEmpty()) {
            return;
        }

        event.fire(listeners);
    }

    public static <L extends Listener> void add(Class<L> type, L listener) {
        @SuppressWarnings("unchecked")
        List<L> listeners = (List<L>) listenersByType.computeIfAbsent(type, v -> new ArrayList<>());
        listeners.add(listener);
    }

    public static <L extends Listener> void remove(Class<L> type, L listener) {
        @SuppressWarnings("unchecked")
        List<L> listeners = (List<L>) listenersByType.get(type);
        if (listeners != null) {
            listeners.remove(listener);
        }
    }

}
