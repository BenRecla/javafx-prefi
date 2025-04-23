package aclcbukidnon.com.javafxactivity.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TodoController {

    @FXML
    private ListView<String> todoList;

    private ObservableList<String> todoItems;

    @FXML
    public void initialize(){
        // Initialize the list with some sample data
        todoItems = FXCollections.observableArrayList();
        todoList.setItems(todoItems);

        // Enable single item selection in the ListView
        todoList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Add a listener for item selection to enable editing
        todoList.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null){
                        onTodoListItemClick(newVal);
                    }
                }
        );
    }

    // Method triggered when a ListView item is clicked for editing
    private void onTodoListItemClick(String value){
        // Open a dialog to edit the selected todo item
        TextInputDialog dialog = new TextInputDialog(value);
        dialog.setTitle("Update Todo");
        dialog.setHeaderText("Edit your todo item");

        var result = dialog.showAndWait();
        result.ifPresent(text -> {
            // Update the item in the list if the text is different
            int selectedIndex = todoList.getSelectionModel().getSelectedIndex();
            todoItems.set(selectedIndex, text);
        });
    }

    // Method for creating a new todo item
    @FXML
    protected void onCreateClick(){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Create New Todo");
        dialog.setHeaderText("Enter a new todo item");

        var result = dialog.showAndWait();
        result.ifPresent(text -> {
            if (!text.trim().isEmpty()) {
                todoItems.add(text); // Add new task to the list
            }
        });
    }

    // Method to delete a todo item
    @FXML
    protected void onDeleteClick(){
        String selectedItem = todoList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Ask for confirmation before deleting
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Confirmation Dialog");
            confirm.setHeaderText("Are you sure you want to delete this todo?");
            confirm.setContentText("This action cannot be undone.");

            var result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                todoItems.remove(selectedItem); // Remove the item if confirmed
            }
        } else {
            showAlert("No Item Selected", "Please select a todo item to delete.");
        }
    }

    // Method to show an alert if necessary (for no item selected etc.)
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

}