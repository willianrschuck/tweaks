package com.willianschuck.events;

import com.willianschuck.events.listeners.Listener;

import java.util.List;

public abstract class Event<T extends Listener> {
    public abstract void fire(List<T> listeners);
    public abstract Class<T> getListenerType();
}
