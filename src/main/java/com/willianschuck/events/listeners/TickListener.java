package com.willianschuck.events.listeners;

import com.willianschuck.events.Event;

import java.util.List;

public interface TickListener extends Listener {

    void onTick();

    class TickEvent extends Event<TickListener> {

        public static TickEvent INSTANCE = new TickEvent();

        @Override
        public void fire(List<TickListener> listeners) {
            for (TickListener listener : listeners) {
                listener.onTick();
            }
        }

        @Override
        public Class<TickListener> getListenerType() {
            return TickListener.class;
        }

    }

}
