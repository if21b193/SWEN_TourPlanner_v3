package com.example.tourplanner.UI.View;

import com.example.tourplanner.BL.service.ImplTourService;
import com.example.tourplanner.UI.ViewModel.AddTourViewModel;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.TourListViewModel;
import com.example.tourplanner.UI.ViewModel.MainWindowViewModel;
import com.example.tourplanner.UI.ViewModel.UpdateTourViewModel;

public class ControllerFactory {
    private final TourListViewModel tourListViewModel;
    private final MainWindowViewModel mainWindowViewModel;
    private final AddTourViewModel addTourViewModel;
    private final UpdateTourViewModel updateTourViewModel;

    public ControllerFactory(){
        ImplTourService implTourService = new ImplTourService();
        EventPublisher eventPublisher = new EventPublisher();
        this.addTourViewModel = new AddTourViewModel(eventPublisher, implTourService);
        this.updateTourViewModel = new UpdateTourViewModel(eventPublisher, implTourService);
        this.tourListViewModel = new TourListViewModel(eventPublisher, implTourService, addTourViewModel);
        this.mainWindowViewModel = new MainWindowViewModel(tourListViewModel, addTourViewModel);
    }

    public Object create(Class<?> controllerClass){
        if(controllerClass == TourListController.class){
            return new TourListController(this.tourListViewModel);
        } if(controllerClass == MainWindowController.class){
            return new MainWindowController(this.mainWindowViewModel);
        } if(controllerClass == AddTourController.class){
            return new AddTourController(this.addTourViewModel);
        } if(controllerClass == UpdateTourController.class){
            return new UpdateTourController(this.updateTourViewModel);
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
