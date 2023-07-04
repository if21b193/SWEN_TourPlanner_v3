package com.example.tourplanner.UI.View;
import com.example.tourplanner.UI.ViewModel.ListViewViewModel;
import com.example.tourplanner.models.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;

public class ListViewController {

    @FXML
    private ListView<Tour> listView;

    private final ListViewViewModel listViewViewModel;

    public ListViewController(ListViewViewModel listViewViewModel){
        this.listViewViewModel = listViewViewModel;
    }

    public ListViewViewModel getListViewViewModel(){
        return this.listViewViewModel;
    }

    //private ObservableList<String> tours;

    @FXML
    public void initialize() {
        /*tours = FXCollections.observableArrayList("Tour 1", "Tour 2", "Tour 3");
        listView.setItems(tours);*/
        listView.setItems(listViewViewModel.getObservableTours());
        listView.setCellFactory(param -> new ListCell<Tour>() {
            @Override
            protected void updateItem(Tour tour, boolean empty) {
                super.updateItem(tour, empty);
                if (empty || tour == null) {
                    setText(null);
                } else {
                    setText(tour.getName());
                }
            }
        });
    }
}
