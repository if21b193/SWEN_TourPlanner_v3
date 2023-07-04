package com.example.tourplanner.UI.View;

import com.example.tourplanner.BL.service.ImplTourService;
import com.example.tourplanner.UI.ViewModel.ListViewViewModel;
import com.example.tourplanner.UI.ViewModel.MainViewModel;

import java.util.List;

public class ControllerFactory {
    private final ListViewViewModel listViewViewModel;
    private final MainViewModel mainViewModel;

    public ControllerFactory(){
        ImplTourService implTourService = new ImplTourService();
        listViewViewModel = new ListViewViewModel(implTourService);
        mainViewModel = new MainViewModel(listViewViewModel);
    }

    public Object create(Class<?> controllerClass){
        if(controllerClass == ListViewController.class){
            return new ListViewController(listViewViewModel);
        } if(controllerClass == MainController.class){
            return new MainController(mainViewModel);
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
