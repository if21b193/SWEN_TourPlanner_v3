package com.example.tourplanner.UI.ViewModel.ShareData;

import com.example.tourplanner.models.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourLogEventPublisher {

        private final List<ITourLogEventListener> listeners;

        public TourLogEventPublisher() {
            listeners = new ArrayList<>();
        }

        public void addEventListener(ITourLogEventListener listener) {
            listeners.add(listener);
        }

        public void removeTourLogEventListener(ITourLogEventListener listener) {
            listeners.remove(listener);
        }

        public void publishEvent(SharedTourLogEvent event) {
            for (ITourLogEventListener listener : listeners) {
                listener.updateTourLog(event);
            }
        }

        public void publishToSingle(SharedTourLogEvent event, String listener) {
            for(ITourLogEventListener listener1 : listeners) {
                if(listener1.getClass().getName().endsWith(listener)){
                    listener1.updateTourLog(event);
                }
            }
        }
    }

