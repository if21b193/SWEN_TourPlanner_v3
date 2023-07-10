package com.example.tourplanner.UI.View;

import com.example.tourplanner.BL.report.ReportService;
import com.example.tourplanner.BL.report.TourCSVExport;
import com.example.tourplanner.BL.report.TourCSVImportService;
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

    private final TourLogListViewModel tourLogListViewModel;
    private final AddTourLogViewModel addTourLogViewModel;
    private final TourMapViewModel tourMapViewModel;
    private final ReportService reportService;
    private final MapQuestStaticImageAPI mapQuestStaticImageAPI;
    private final TourDetailsViewModel tourDetailsViewModel;
    private final TourLogDao tourLogDao;
    private final TourCSVExportController tourCSVExportController;
    private final TourCSVImportController tourCSVImportController;
    private final TourLogTableViewModel tourLogTableViewModel;


    public ControllerFactory(){
        ImplTourService implTourService = new ImplTourService();
        this.eventPublisher = new EventPublisher();
        TourLogEventPublisher tourLogEventPublisher = new TourLogEventPublisher();
        ImplTourLogService tourLogService = new ImplTourLogService();
        this.addTourViewModel = new AddTourViewModel(implTourService);
        this.updateTourViewModel = new UpdateTourViewModel(eventPublisher, implTourService);
        this.tourListViewModel = new TourListViewModel(eventPublisher, implTourService, tourLogService, addTourViewModel);
        this.mainWindowViewModel = new MainWindowViewModel(tourListViewModel, addTourViewModel);
        this.addTourLogViewModel = new AddTourLogViewModel(eventPublisher, tourLogEventPublisher, tourLogService);
        this.tourLogListViewModel = new TourLogListViewModel(tourLogEventPublisher, tourLogService, addTourLogViewModel);
        this.tourMapViewModel = new TourMapViewModel(eventPublisher);
        this.mapQuestStaticImageAPI = new MapQuestStaticImageAPI();
        this.reportService = new ReportService(mapQuestStaticImageAPI);
        this.tourDetailsViewModel = new TourDetailsViewModel(eventPublisher);
        this.tourLogDao = new TourLogDao();
        this.tourCSVExportController = new TourCSVExportController();
        this.tourCSVImportController = new TourCSVImportController();
        this.tourLogTableViewModel = new TourLogTableViewModel(eventPublisher);
    }

    public Object create(Class<?> controllerClass){
        if(controllerClass == TourListController.class){
            return new TourListController(this.tourListViewModel, eventPublisher, reportService, tourLogDao);
        } if(controllerClass == MainWindowController.class){
            return new MainWindowController(this.mainWindowViewModel, this.tourLogListViewModel, this.tourListViewModel);
        } if(controllerClass == AddTourController.class){
            return new AddTourController(this.addTourViewModel);
        } if(controllerClass == UpdateTourController.class){
            return new UpdateTourController(this.updateTourViewModel);
        } if(controllerClass == TourLogListController.class){
            return new TourLogListController(this.tourLogListViewModel, this.tourListViewModel, eventPublisher);
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
            return new TourLogTableController(tourLogTableViewModel);
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
