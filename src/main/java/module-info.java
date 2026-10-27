module edu.utsa.cs3443.replate {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.utsa.cs3443.replate to javafx.fxml;
    exports edu.utsa.cs3443.replate;
}