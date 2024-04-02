module com.example.sysexpert {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.sysexpert to javafx.fxml;
    exports com.example.sysexpert;
}