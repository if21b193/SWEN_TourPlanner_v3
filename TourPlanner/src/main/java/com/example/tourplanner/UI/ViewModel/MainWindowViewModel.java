package com.example.tourplanner.UI.ViewModel;


public class MainWindowViewModel {
    private TourListViewModel tourListViewModel;
    private AddTourViewModel addTourViewModel;
    public MainWindowViewModel(TourListViewModel tourListViewModel, AddTourViewModel addTourViewModel) {
        this.tourListViewModel = tourListViewModel;
        this.addTourViewModel = addTourViewModel;
    }
}
