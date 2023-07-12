package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.DAL.dal.Repository.MapQuestStaticImageAPI;
import com.example.tourplanner.UI.ViewModel.ShareData.EventListener;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.SharedTourEvent;
import com.example.tourplanner.models.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;

public class TourMapViewModel implements EventListener {
    private StringProperty imageURL = new SimpleStringProperty();

    private final EventPublisher publisher;
    private static Tour tour;

    public StringProperty imageURLProperty() {
        return imageURL;
    }

    public TourMapViewModel(EventPublisher eventPublisher){
        publisher = eventPublisher;
        publisher.addEventListener(this);
    }

    @Override
    public void updateFromDb(SharedTourEvent event) {
       tour = event.returnTour();
       if(tour != null){
           imageURL.set(new MapQuestStaticImageAPI().getStaticImage(tour.getFrom(), tour.getTo()));
       }
    }
}
