module com.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires com.fasterxml.jackson.databind;
    requires org.apache.pdfbox;
    requires java.desktop;


    exports com.example.tourplanner.UI.View to javafx.fxml;
    opens com.example.tourplanner.UI.View to javafx.fxml;
    opens com.example.tourplanner to javafx.fxml;
    exports com.example.tourplanner;
    opens com.example.tourplanner.models to org.hibernate.orm.core;

}