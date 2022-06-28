package model;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;


public class MainController implements Initializable {
    @FXML
    private TextArea txtAreaBF;

    @FXML
    private TextArea txtAreaResult,txtAreaRule;

    @FXML
    private ComboBox<String> typeCombo,sizeCombo,nbwCombo,nbdCombo,motorCombo;
    protected static BooleanRuleBase vehicles;
    protected static RuleBase currentRuleBase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeCombo.getItems().addAll("null","cycle","automobile");
        typeCombo.setValue("null");
        sizeCombo.getItems().addAll("null","small", "medium", "large");
        sizeCombo.setValue("medium");
        nbwCombo.getItems().addAll("null","2","3","4");
        nbwCombo.setValue("4");
        nbdCombo.getItems().addAll("null","2","3","4");
        nbdCombo.setValue("3");
        motorCombo.getItems().addAll("null","yes","no");
        motorCombo.setValue("yes");

    }

    public void onClickValider() {
        vehicles = new BooleanRuleBase("Vehicles Rule Base");

        VehicleRuleBase.init(vehicles);
        currentRuleBase = vehicles;
        demoVehiclesFC((BooleanRuleBase) currentRuleBase);
        ArrayList<String> resultats=currentRuleBase.forwardChain();
//        txtAreaResult.setWrapText(true);
//        String res="";
//        for (String c : resultats){
//            res+=c+"\n";
//        }
        txtAreaRule.setWrapText(true);
        txtAreaRule.setText(((BooleanRuleBase) currentRuleBase).processDetails);
        txtAreaResult.setText("Le vehicule est un "+vehicles.variableList.get("vehicle").value);
    }

    public void demoVehiclesFC (BooleanRuleBase rb) {
        String BF="";
        boolean nulle=false;
        rb.setVariableValue(VariableNames.vehicle, null);
        if(typeCombo.getSelectionModel().getSelectedIndex()==0&&sizeCombo.getSelectionModel().getSelectedIndex()==0&&nbwCombo.getSelectionModel().getSelectedIndex()==0
        &&nbdCombo.getSelectionModel().getSelectedIndex()==0&&motorCombo.getSelectionModel().getSelectedIndex()==0){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "pour vous informer qu'il faut au moin sélectioner un fait", ButtonType.OK);
            a.show();
            return;
        }
        switch (typeCombo.getSelectionModel().getSelectedItem()){
            case "null": rb.setVariableValue(VariableNames.vehicleType, null);
                nulle=true;
                break;
            case "cycle": rb.setVariableValue(VariableNames.vehicleType, VariableNames.cycle);
                break;
            case "automobile": rb.setVariableValue(VariableNames.vehicleType, VariableNames.automobile);
                break;
        }
        if(!nulle){
            BF+="le vehicule est une "+typeCombo.getSelectionModel().getSelectedItem()+"\n";
        }
        nulle=false;
        switch (sizeCombo.getSelectionModel().getSelectedItem()){
            case "null": rb.setVariableValue(VariableNames.size, null);
                nulle=true;
                break;
            case "small": rb.setVariableValue(VariableNames.size, VariableNames.small);
                break;
            case "medium": rb.setVariableValue(VariableNames.size, VariableNames.medium);
                break;
            case "large": rb.setVariableValue(VariableNames.size, VariableNames.large);
                break;
        }
        if(!nulle){
            BF+="le vehicule est "+sizeCombo.getSelectionModel().getSelectedItem()+"\n";
        }
        nulle=false;
        switch (nbwCombo.getSelectionModel().getSelectedItem()){
            case "null": rb.setVariableValue(VariableNames.numWheels, null);
                nulle=true;
                break;
            case "2": rb.setVariableValue(VariableNames.numWheels, "2");
                break;
            case "3": rb.setVariableValue(VariableNames.numWheels, "3");
                break;
            case "4": rb.setVariableValue(VariableNames.numWheels, "4");
                break;
        }
        if(!nulle){
            BF+="le vehicule a "+nbwCombo.getSelectionModel().getSelectedItem()+" routs\n";
        }
        nulle=false;
        switch (nbdCombo.getSelectionModel().getSelectedItem()){
            case "null": rb.setVariableValue(VariableNames.numDoors, null);
                nulle=true;
                break;
            case "2": rb.setVariableValue(VariableNames.numDoors, "2");
                break;
            case "3": rb.setVariableValue(VariableNames.numDoors, "3");
                break;
            case "4": rb.setVariableValue(VariableNames.numDoors, "4");
                break;
        }
        if(!nulle){
            BF+="le vehicule a "+nbdCombo.getSelectionModel().getSelectedItem()+" portes\n";
        }
        nulle=false;
        switch (motorCombo.getSelectionModel().getSelectedItem()){
            case "null": rb.setVariableValue(VariableNames.motor, null);
                nulle=true;
                break;
            case "no": rb.setVariableValue(VariableNames.motor, VariableNames.no);
                break;
            case "yes": rb.setVariableValue(VariableNames.motor, VariableNames.yes);
                break;
        }
        if(!nulle){
            BF+="le vehicule a un moteur "+motorCombo.getSelectionModel().getSelectedItem()+" \n";
        }
        txtAreaBF.setText(BF);
    }

    public void basculerEX(Event e) throws IOException {
        Parent root = load(getClass().getResource("SecondExView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Console Expert");
    }
}