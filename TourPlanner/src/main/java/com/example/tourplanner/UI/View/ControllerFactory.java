package com.example.tourplanner.UI.View;

import com.example.tourplanner.BL.service.ImplTourLogService;
import com.example.tourplanner.BL.service.ImplTourService;
import com.example.tourplanner.UI.ViewModel.*;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.TourLogEventPublisher;

public class ControllerFactory {
    private final TourListViewModel tourListViewModel;
    private final MainWindowViewModel mainWindowViewModel;
    private final AddTourViewModel addTourViewModel;
    private final UpdateTourViewModel updateTourViewModel;

    private final EventPublisher eventPublisher;

    private final TourLogListViewModel tourLogListViewModel;
    private final AddTourLogViewModel addTourLogViewModel;

    public ControllerFactory(){
        ImplTourService implTourService = new ImplTourService();
        this.eventPublisher = new EventPublisher();
        TourLogEventPublisher tourLogEventPublisher = new TourLogEventPublisher();
        ImplTourLogService tourLogService = new ImplTourLogService();
        this.addTourViewModel = new AddTourViewModel(eventPublisher, implTourService);
        this.updateTourViewModel = new UpdateTourViewModel(eventPublisher, implTourService);
        this.tourListViewModel = new TourListViewModel(eventPublisher, implTourService, addTourViewModel);
        this.mainWindowViewModel = new MainWindowViewModel(tourListViewModel, addTourViewModel);
        this.addTourLogViewModel = new AddTourLogViewModel(tourLogEventPublisher, tourLogService);
        this.tourLogListViewModel = new TourLogListViewModel(tourLogEventPublisher, tourLogService, addTourLogViewModel);
    }

    public Object create(Class<?> controllerClass){
        if(controllerClass == TourListController.class){
            return new TourListController(this.tourListViewModel, eventPublisher);
        } if(controllerClass == MainWindowController.class){
            return new MainWindowController(this.mainWindowViewModel);
        } if(controllerClass == AddTourController.class){
            return new AddTourController(this.addTourViewModel);
        } if(controllerClass == UpdateTourController.class){
            return new UpdateTourController(this.updateTourViewModel);
        } if(controllerClass == TourLogListController.class){
            return new TourLogListController(this.tourLogListViewModel);
        } if(controllerClass == AddTourLogController.class){
            return new AddTourLogController(this.addTourLogViewModel);
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
