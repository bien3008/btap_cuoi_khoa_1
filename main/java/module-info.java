module org.example.btap_cuoi_khoa_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens org.example.btap_cuoi_khoa_1 to javafx.fxml;
    exports org.example.btap_cuoi_khoa_1.manager;
    opens org.example.btap_cuoi_khoa_1.manager to javafx.fxml;
    exports org.example.btap_cuoi_khoa_1;
}