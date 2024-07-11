package main.java.app.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class HelloController {

    @FXML
    private Label listLabel;

    @FXML
    private ListView<String> listView1;

    @FXML
    private ListView<String> listView2;

    @FXML
    private TabPane TestTabPane;

    private ObservableList<String> items1;
    private ObservableList<String> items2;
    private int currentIndex = 0;
    private final int itemsPerPage = 10;

    public void initialize() {
        listLabel.setText("Top View");

        // リストに表示する全データを用意する
        items1 = FXCollections.observableArrayList(
                "Item 1", "Item 2", "Item 3", "Item 4", "Item 5",
                "Item 6", "Item 7", "Item 8", "Item 9", "Item 10",
                "Item 11", "Item 12", "Item 13", "Item 14", "Item 15",
                "Item 16", "Item 17", "Item 18", "Item 19", "Item 20",
                "Item 21", "Item 22", "Item 23", "Item 24", "Item 25",
                "Item 26", "Item 27", "Item 28", "Item 29", "Item 30"
        );

        items2 = FXCollections.observableArrayList(
                "Item A", "Item B", "Item C", "Item D", "Item E",
                "Item F", "Item G", "Item H", "Item I", "Item J",
                "Item K", "Item L", "Item M", "Item N", "Item O",
                "Item P", "Item Q", "Item R", "Item S", "Item T",
                "Item U", "Item V", "Item W", "Item X", "Item Y",
                "Item Z", "Item AA", "Item AB", "Item AC", "Item AD"
        );

        TestTabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            currentIndex = 0;
            updateListView(currentIndex);
        });

        updateListView(currentIndex);
    }

    @FXML
    private void handleNextButtonClick() {
        if (currentIndex + itemsPerPage < getCurrentItems().size()) {
            currentIndex += itemsPerPage;
            updateListView(currentIndex);
        }
    }

    @FXML
    private void handlePreviousButtonClick() {
        if (currentIndex - itemsPerPage >= 0) {
            currentIndex -= itemsPerPage;
            updateListView(currentIndex);
        }
    }

    private void updateListView(int startIndex) {
        // 現在のページのアイテムを抽出して表示する
        int endIndex = Math.min(startIndex + itemsPerPage, getCurrentItems().size());
        ObservableList<String> sublist = FXCollections.observableArrayList(getCurrentItems().subList(startIndex, endIndex));

        if (TestTabPane.getSelectionModel().getSelectedIndex() == 0) {
            listView1.setItems(sublist);
        } else {
            listView2.setItems(sublist);
        }
    }

    private ObservableList<String> getCurrentItems() {
        if (TestTabPane.getSelectionModel().getSelectedIndex() == 0) {
            return items1;
        } else {
            return items2;
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Parent secondaryView = FXMLLoader.load(getClass().getResource("/fxml/scroll.fxml"));
        Scene secondaryScene = new Scene(secondaryView, 1000, 600);

        // 現在のステージ（ウィンドウ）を取得
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // 新しいシーンをセット
        window.setScene(secondaryScene);
        window.show();

    }
}
