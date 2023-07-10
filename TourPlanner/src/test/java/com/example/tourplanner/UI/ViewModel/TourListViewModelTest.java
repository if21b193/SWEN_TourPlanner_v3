package com.example.tourplanner.UI.ViewModel;

import com.example.tourplanner.BL.service.TourLogService;
import com.example.tourplanner.BL.service.TourService;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.models.Tour;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TourListViewModelTest {
    private TourListViewModel tourListViewModel;
    private List<Tour> tours;

    @Mock
    private EventPublisher mockEventPublisher;
    @Mock
    private TourService mockTourService;
    @Mock
    private AddTourViewModel mockAddTourViewModel;
    @Mock
    private ObservableList<Tour> mockObservableList;
    @Mock
    private TourLogService mockTourLogService;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        tourListViewModel = new TourListViewModel(mockEventPublisher, mockTourService, mockTourLogService, mockAddTourViewModel);
        tours = Arrays.asList(new Tour(), new Tour(), new Tour());
    }

    @Test
    public void testSetUpToursWorks(){
        tourListViewModel.setTours(tours);
        assertEquals(tours.size(), tourListViewModel.getObservableTours().size());
        assertTrue(tourListViewModel.getObservableTours().containsAll(tours));
    }

    @Test
    public void testSetUpToursWithEmptyToursDoesntThrowFit(){
        tourListViewModel.setTours(null);
        assertEquals(0, tourListViewModel.getObservableTours().size());
    }


}