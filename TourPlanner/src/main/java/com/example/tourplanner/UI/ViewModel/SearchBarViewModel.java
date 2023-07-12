package com.example.tourplanner.UI.ViewModel;

public class SearchBarViewModel {
    private final TourListViewModel tourListViewModel;

    public SearchBarViewModel(TourListViewModel tourListViewModel){
        this.tourListViewModel = tourListViewModel;
    }
    public void searchFor(String text) {
        this.tourListViewModel.searchFor(text);
    }
}
