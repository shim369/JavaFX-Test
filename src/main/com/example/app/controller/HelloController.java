package main.com.example.app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HelloController {

    @FXML
    private Label listLabel;

    @FXML
    private ListView<String> listView;
    
    private ObservableList<String> items;
    private int currentIndex = 0;
    private final int itemsPerPage = 10;

    public void initialize() {
        listLabel.setText("List Label");
        // リストに表示する全データを用意する
        items = FXCollections.observableArrayList(
                "Item 1", "Item 2", "Item 3", "Item 4", "Item 5",
                "Item 6", "Item 7", "Item 8", "Item 9", "Item 10",
                "Item 11", "Item 12", "Item 13", "Item 14", "Item 15"
        );
        updateListView(currentIndex);

    }

    @FXML
    private void handleNextButtonClick() {
        if (currentIndex + itemsPerPage < items.size()) {
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
        int endIndex = Math.min(startIndex + itemsPerPage, items.size());
        listView.setItems(FXCollections.observableArrayList(items.subList(startIndex, endIndex)));
    }
}
