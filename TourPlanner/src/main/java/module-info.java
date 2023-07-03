module com.example.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    //requires lombok;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;


    opens com.example.tourplanner to javafx.fxml;
    exports com.example.tourplanner;
    opens com.example.tourplanner.models to org.hibernate.orm.core;

}