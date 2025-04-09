module tracetech.tracetech {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens tracetech.tracetech to javafx.fxml;
    exports tracetech.tracetech;

    exports tracetech.tracetech.controller to javafx.fxml;
    opens tracetech.tracetech.controller to javafx.fxml;
}
