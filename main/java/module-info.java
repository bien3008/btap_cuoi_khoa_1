module org.example.btap_cuoi_khoa_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.example.btap_cuoi_khoa_1 to javafx.fxml;
    exports org.example.btap_cuoi_khoa_1;
}