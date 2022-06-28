package model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class SecondExController implements Initializable {
    @FXML
    private ComboBox<String> typeCombo,priceCombo,qualityCombo,screenCombo,storageCombo,nbpCombo,sizeCombo;

    @FXML
    private TextArea txtAreaBF,txtAreaResult,txtAreaRule;
    static BooleanRuleBase consoles;
    static RuleBase currentRuleBase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeCombo.getItems().addAll("null","gaming","all");
        typeCombo.setValue("gaming");
        sizeCombo.getItems().addAll("null","small", "medium", "large");
        sizeCombo.setValue("null");
        priceCombo.getItems().addAll("null","200","500","1000");
        priceCombo.setValue("500");
        qualityCombo.getItems().addAll("null","low", "medium", "high");
        qualityCombo.setValue("medium");
        screenCombo.getItems().addAll("null","yes","no");
        screenCombo.setValue("no");
        storageCombo.getItems().addAll("null","0", "32", "120", "160", "250", "320", "500", "1000","1500");
        storageCombo.setValue("32");
        nbpCombo.getItems().addAll("null","few","average","more");
        nbpCombo.setValue("few");

    }

    public void onClickValider(ActionEvent actionEvent) {
        consoles = new BooleanRuleBase("consoles Rule Base");
        ConsoleRuleBase.init(consoles);
        currentRuleBase = consoles;
        demoConsoleFC((BooleanRuleBase) currentRuleBase);
        ArrayList<String> resultats=currentRuleBase.forwardChain();
//        txtAreaResult.setWrapText(true);
//        String res="";
//        for (String c : resultats){
//            res+=c+"\n";
//        }
        txtAreaRule.setWrapText(true);
        txtAreaRule.setText(((BooleanRuleBase) currentRuleBase).processDetails);
        txtAreaResult.setText("Le console est un "+consoles.variableList.get("type").value);
    }

    public void demoConsoleFC (BooleanRuleBase rb) {
        String BF="";
        boolean nulle=false;
        if(typeCombo.getSelectionModel().getSelectedIndex()==0&&priceCombo.getSelectionModel().getSelectedIndex()==0&&qualityCombo.getSelectionModel().getSelectedIndex()==0&&
            screenCombo.getSelectionModel().getSelectedIndex()==0&&storageCombo.getSelectionModel().getSelectedIndex()==0&&nbpCombo.getSelectionModel().getSelectedIndex()==0&&
            sizeCombo.getSelectionModel().getSelectedIndex()==0){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "pour vous informer qu'il faut au moin sélectioner un fait", ButtonType.OK);
            a.show();
            return;
        }
        switch (typeCombo.getSelectionModel().getSelectedItem()){
            case "null": rb.setVariableValue(VN2.usageType, null);
                nulle=true;
                break;
            case "gaming": rb.setVariableValue(VN2.usageType,VN2.gaming);
                break;
            case "all": rb.setVariableValue(VN2.usageType, VN2.all);
                break;
        }
        if(!nulle){
            BF+="le console est pour "+typeCombo.getSelectionModel().getSelectedItem()+"\n";
        }
        nulle=false;
        switch (screenCombo.getSelectionModel().getSelectedItem()){
            case "null": rb.setVariableValue(VN2.screen, null);
                nulle=true;
                break;
            case "no": rb.setVariableValue(VN2.screen, VN2.no);
                break;
            case "yes": rb.setVariableValue(VN2.screen, VN2.yes);
                break;
        }
        if(!nulle){
            BF+="le console a besoin d'un écran "+screenCombo.getSelectionModel().getSelectedItem()+" \n";
        }
        nulle=false;
        switch (sizeCombo.getSelectionModel().getSelectedItem()){
            case "null": rb.setVariableValue(VN2.size, null);
                nulle=true;
                break;
            case "small": rb.setVariableValue(VN2.size, VN2.small);
                break;
            case "medium": rb.setVariableValue(VN2.size, VN2.medium);
                break;
            case "large": rb.setVariableValue(VN2.size, VN2.large);
                break;
        }
        if(!nulle){
            BF+="le console est "+sizeCombo.getSelectionModel().getSelectedItem()+"\n";
        }
        nulle=false;
        if (storageCombo.getSelectionModel().getSelectedIndex()==0){
            rb.setVariableValue(VN2.storage, null);
        }else {
            rb.setVariableValue(VN2.storage, storageCombo.getSelectionModel().getSelectedItem());
            BF+="le vehicule a "+storageCombo.getSelectionModel().getSelectedItem()+" de stockage\n";
        }
        if (priceCombo.getSelectionModel().getSelectedIndex()==0){
            rb.setVariableValue(VN2.budget, null);
        }else {
            rb.setVariableValue(VN2.budget, priceCombo.getSelectionModel().getSelectedItem());
            BF+="le prix de console  est plus de "+priceCombo.getSelectionModel().getSelectedItem()+"\n";
        }
        switch (qualityCombo.getSelectionModel().getSelectedItem()){
            case "null": rb.setVariableValue(VN2.playingQuality, null);
                nulle=true;
                break;
            case "low": rb.setVariableValue(VN2.playingQuality, VN2.low);
                break;
            case "medium": rb.setVariableValue(VN2.playingQuality, VN2.medium);
                break;
            case "high": rb.setVariableValue(VN2.playingQuality, VN2.high);
                break;
        }
        if(!nulle){
            BF+="le console a une "+qualityCombo.getSelectionModel().getSelectedItem()+" qualité\n";
        }
        nulle=false;
        switch (nbpCombo.getSelectionModel().getSelectedItem()){
            case "null": rb.setVariableValue(VN2.nbGames, null);
                nulle=true;
                break;
            case "few": rb.setVariableValue(VN2.nbGames, VN2.few);
                break;
            case "average": rb.setVariableValue(VN2.nbGames, VN2.average);
                break;
            case "more": rb.setVariableValue(VN2.nbGames, VN2.more);
                break;
        }
        if(!nulle){
            BF+="le console support "+nbpCombo.getSelectionModel().getSelectedItem()+" parties\n";
        }
        txtAreaBF.setText(BF);
    }

    public void basculerEX(ActionEvent actionEvent) throws IOException {
        Parent root = load(getClass().getResource("mainView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Vehicle Expert");
    }


}
