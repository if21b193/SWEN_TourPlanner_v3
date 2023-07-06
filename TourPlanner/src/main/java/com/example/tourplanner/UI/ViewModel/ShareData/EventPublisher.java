package com.example.tourplanner.UI.ViewModel.ShareData;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {
    private final List<EventListener> listeners;

    public EventPublisher() {
        listeners = new ArrayList<>();
    }

    public void addEventListener(EventListener listener) {
        listeners.add(listener);
    }

    public void removeEventListener(EventListener listener) {
        listeners.remove(listener);
    }

    public void publishEvent(SharedTourEvent event) {
        for (EventListener listener : listeners) {
            listener.updateFromDb(event);
        }
    }

    public void publishToSingle(SharedTourEvent event, String listener) {
        for(EventListener listener1 : listeners) {
            if(listener1.getClass().getName().endsWith(listener)){
                listener1.updateFromDb(event);
            }
        }
    }
}
