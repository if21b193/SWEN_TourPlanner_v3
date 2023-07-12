package com.example.tourplanner.UI.View;

import com.example.tourplanner.BL.report.ReportService;
import com.example.tourplanner.BL.service.ImplTourLogService;
import com.example.tourplanner.BL.service.ImplTourService;
import com.example.tourplanner.DAL.dal.Repository.MapQuestStaticImageAPI;
import com.example.tourplanner.DAL.dal.dao.TourLogDao;
import com.example.tourplanner.UI.ViewModel.*;
import com.example.tourplanner.UI.ViewModel.ShareData.EventPublisher;
import com.example.tourplanner.UI.ViewModel.ShareData.TourLogEventPublisher;

public class ControllerFactory {
    private final TourListViewModel tourListViewModel;
    private final MainWindowViewModel mainWindowViewModel;
    private final AddTourViewModel addTourViewModel;
    private final UpdateTourViewModel updateTourViewModel;
    private final EventPublisher eventPublisher;
    private final AddTourLogViewModel addTourLogViewModel;
    private final TourMapViewModel tourMapViewModel;
    private final ReportService reportService;
    private final TourDetailsViewModel tourDetailsViewModel;
    private final TourLogTableViewModel tourLogTableViewModel;
    private final UpdateTourLogViewModel updateTourLogViewModel;
    private final TourLogEventPublisher tourLogEventPublisher;
    private final SearchBarViewModel searchBarViewModel;


    public ControllerFactory(){
        ImplTourService implTourService = new ImplTourService();
        this.eventPublisher = new EventPublisher();
        this.tourLogEventPublisher = new TourLogEventPublisher();
        ImplTourLogService tourLogService = new ImplTourLogService();
        this.addTourViewModel = new AddTourViewModel(implTourService);
        this.tourLogTableViewModel = new TourLogTableViewModel(eventPublisher, tourLogService);
        this.updateTourViewModel = new UpdateTourViewModel(eventPublisher, implTourService);
        this.tourListViewModel = new TourListViewModel(eventPublisher, implTourService, tourLogService, addTourViewModel);
        this.mainWindowViewModel = new MainWindowViewModel(tourListViewModel, addTourViewModel);
        this.addTourLogViewModel = new AddTourLogViewModel(eventPublisher, tourLogService);
        this.tourMapViewModel = new TourMapViewModel(eventPublisher);
        MapQuestStaticImageAPI mapQuestStaticImageAPI = new MapQuestStaticImageAPI();
        this.reportService = new ReportService(mapQuestStaticImageAPI);
        this.tourDetailsViewModel = new TourDetailsViewModel(eventPublisher);
        TourLogDao tourLogDao = new TourLogDao();
        this.updateTourLogViewModel = new UpdateTourLogViewModel(tourLogEventPublisher, tourLogService);
        this.searchBarViewModel = new SearchBarViewModel(tourListViewModel);
    }

    public Object create(Class<?> controllerClass){
        if(controllerClass == TourListController.class){
            return new TourListController(this.tourListViewModel, eventPublisher, reportService);
        } if(controllerClass == MainWindowController.class){
            return new MainWindowController(this.mainWindowViewModel, this.tourListViewModel);
        } if(controllerClass == AddTourController.class){
            return new AddTourController(this.addTourViewModel);
        } if(controllerClass == UpdateTourController.class){
            return new UpdateTourController(this.updateTourViewModel);
        } if(controllerClass == TourLogListController.class){
            return new TourLogListController();
        } if(controllerClass == AddTourLogController.class) {
            return new AddTourLogController(this.addTourLogViewModel);
        } if(controllerClass == TourDetailsController.class){
            return new TourDetailsController(tourDetailsViewModel);
        } if(controllerClass == TourMapController.class){
            return new TourMapController(tourMapViewModel);
        } if(controllerClass == DetailsController.class){
            return new DetailsController();
        } if(controllerClass == TourCSVExportController.class){
            return new TourCSVExportController();
        } if(controllerClass == TourCSVImportController.class){
            return new TourCSVImportController();
        } if(controllerClass == TourLogTableController.class){
            return new TourLogTableController(tourLogTableViewModel, tourLogEventPublisher);
        } if(controllerClass == UpdateTourLogController.class){
            return new UpdateTourLogController(updateTourLogViewModel);
        } if(controllerClass == SearchBarController.class){
            return new SearchBarController(searchBarViewModel);
        }
        throw new IllegalArgumentException("Unknown controller class: "+ controllerClass);
    }

    private static ControllerFactory instance;

    public static ControllerFactory getInstance(){
        if(instance == null){
            instance =  new ControllerFactory();
        }
        return instance;
    }
}
