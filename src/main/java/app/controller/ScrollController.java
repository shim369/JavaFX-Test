package main.java.app.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

public class ScrollController {

    @FXML
    private Label listLabel;

    @FXML
    private ListView<String> listView1;

    @FXML
    private ListView<String> listView2;

    @FXML
    private ScrollPane ScrollPane1;

    @FXML
    private ScrollPane ScrollPane2;

    @FXML
    private TabPane TestTabPane;

    private ObservableList<String> items1;
    private ObservableList<String> items2;

    public void initialize() {
        listLabel.setText("Scroll View");

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

        updateListView();

        TestTabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            updateListView();
        });
    }

    @FXML
    private void handleNextButtonClick() {
        ScrollPane scrollPane = getCurrentScrollPane();
        if (scrollPane != null) {
            double scrollAmount = 0.5; // スクロール量を設定（調整可能）
            double newScrollPos = scrollPane.getVvalue() - scrollAmount;
            newScrollPos = Math.max(newScrollPos, 0.0); // 最小値を0に設定
            scrollPane.setVvalue(newScrollPos); // スクロール位置を更新
        }
    }

    @FXML
    private void handlePreviousButtonClick() {
        ScrollPane scrollPane = getCurrentScrollPane();
        if (scrollPane != null) {
            double scrollAmount = 0.5; // スクロール量を設定（調整可能）
            double newScrollPos = scrollPane.getVvalue() + scrollAmount;
            newScrollPos = Math.min(newScrollPos, 1.0); // 最大値を1に設定
            scrollPane.setVvalue(newScrollPos); // スクロール位置を更新
        }
    }

    private void updateListView() {
        ObservableList<String> currentItems = getCurrentItems();

        if (TestTabPane.getSelectionModel().getSelectedIndex() == 0) {
            listView1.setItems(currentItems);
        } else {
            listView2.setItems(currentItems);
        }
    }

    private ObservableList<String> getCurrentItems() {
        if (TestTabPane.getSelectionModel().getSelectedIndex() == 0) {
            return items1;
        } else {
            return items2;
        }
    }

    private ScrollPane getCurrentScrollPane() {
        if (TestTabPane.getSelectionModel().getSelectedIndex() == 0) {
            return ScrollPane1;
        } else {
            return ScrollPane2;
        }
    }

    @FXML
    private void handleButtonTopAction(ActionEvent event) throws IOException {
        Parent topView = FXMLLoader.load(getClass().getResource("/fxml/hello.fxml"));
        Scene topScene = new Scene(topView, 1000, 600);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(topScene);
        window.show();
    }
}
