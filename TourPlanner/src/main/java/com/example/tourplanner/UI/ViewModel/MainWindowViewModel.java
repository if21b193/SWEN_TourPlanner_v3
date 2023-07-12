package com.example.tourplanner.UI.ViewModel;


public class MainWindowViewModel {
    private final TourListViewModel tourListViewModel;
    private final AddTourViewModel addTourViewModel;
    public MainWindowViewModel(TourListViewModel tourListViewModel, AddTourViewModel addTourViewModel) {
        this.tourListViewModel = tourListViewModel;
        this.addTourViewModel = addTourViewModel;
    }
}
