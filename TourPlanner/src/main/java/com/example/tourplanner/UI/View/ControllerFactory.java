package com.example.tourplanner.UI.View;

import com.example.tourplanner.BL.service.ImplTourService;
import com.example.tourplanner.UI.ViewModel.AddTourViewModel;
import com.example.tourplanner.UI.ViewModel.TourListViewModel;
import com.example.tourplanner.UI.ViewModel.MainWindowViewModel;

public class ControllerFactory {
    private final TourListViewModel tourListViewModel;
    private final MainWindowViewModel mainWindowViewModel;
    private final AddTourViewModel addTourViewModel;

    public ControllerFactory(){
        ImplTourService implTourService = new ImplTourService();
        tourListViewModel = new TourListViewModel(implTourService);
        addTourViewModel = new AddTourViewModel();
        mainWindowViewModel = new MainWindowViewModel(tourListViewModel);

    }

    public Object create(Class<?> controllerClass){
        if(controllerClass == TourListController.class){
            return new TourListController(tourListViewModel);
        } if(controllerClass == MainWindowController.class){
            return new MainWindowController(mainWindowViewModel);
        } if(controllerClass == AddTourController.class){
            return new AddTourController(addTourViewModel);
        }
        throw new IllegalArgumentException("Unknown controller class: "+ controllerClass);
    }

    private static ControllerFactory instance;

    public static ControllerFactory getInstance(){
        if(instance == null){
            return new ControllerFactory();
        }
        return instance;
    }
}
