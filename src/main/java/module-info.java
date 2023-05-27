module project.group {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires java.desktop;

    exports model;
    exports view;
    opens model to com.google.gson;
    opens view to javafx.fxml;
}