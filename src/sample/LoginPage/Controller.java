package sample.LoginPage;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.ConnectionError;
import sample.Main;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class Controller extends Main implements Initializable {
    public Button LogInButton;
    public JFXTextField schoolIdTextField;
    public JFXTextField staffIdTextField;
    public JFXPasswordField passwordTextField;
    public Label loginError;
    public VBox vbox;
    public StackPane stackpane;
    public Circle c1;
    public Circle c2;
    public Circle c3;
    public Circle c4;

    public void LogInButtonEntered(){
        ScaleTrans(LogInButton);
    }
    public void LogInButtonExited(){
        RestoreScale(LogInButton);
    }
    public void LogInButtonClicked() throws IOException {
        goToDashBoard();
    }
    public void Rotate(Circle c,boolean reverse,int angle,int duration){
        RotateTransition rotateTransition=new RotateTransition(new Duration(duration),c);
        rotateTransition.setByAngle(angle);
        rotateTransition.setAutoReverse(reverse);
        rotateTransition.setDelay(new Duration(0));
        rotateTransition.setRate(2);
        rotateTransition.setCycleCount(rotateTransition.INDEFINITE);
        rotateTransition.play();
    }
    public void ScaleTrans(Button button){
        ScaleTransition scaleTransition=new ScaleTransition(new Duration(100));
        scaleTransition.setNode(button);
        scaleTransition.setFromX(1);
        scaleTransition.setToX(1.5);
        scaleTransition.setFromY(1);
        scaleTransition.setToY(1.5);
        scaleTransition.play();
        scaleTransition.setOnFinished(event -> {
            scaleTransition.stop();
        });

    }
    public void RestoreScale(Button button){
        ScaleTransition scaleTransition=new ScaleTransition(new Duration(100));
            scaleTransition.setNode(button);
        scaleTransition.setFromX(1.5);
        scaleTransition.setToX(1);
        scaleTransition.setFromY(1.5);
        scaleTransition.setToY(1);
            scaleTransition.play();
        scaleTransition.setOnFinished(event -> {
            scaleTransition.stop();
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

  public void goToDashBoard() throws IOException {
        String schoolid = schoolIdTextField.getText();
        String staffId=staffIdTextField.getText();
        String passwd = passwordTextField.getText();
        if (schoolid.isEmpty()||passwd.isEmpty()|| staffId.isEmpty()){
            new ConnectionError().Connection("One of the field is missing");
        }
        if (!schoolid.matches("^[A-Za-z0-9]*$")){
            new ConnectionError().Connection("Invalid email,check for invalid character");
        }
        if (!schoolid.isEmpty()&&!passwd.isEmpty()&&schoolid.matches("^[A-Za-z0-9]*$")&&staffId.matches("^[A-Za-z0-9]*$")){
            TranslateTransition hboxTransition=new TranslateTransition(new Duration(2000),vbox);
            hboxTransition.setToY(-50);
            ParallelTransition parallelTransition=new ParallelTransition(hboxTransition);
            parallelTransition.play();

            parallelTransition.setOnFinished((event -> {
                parallelTransition.stop();
                stackpane.setVisible(true);
                Rotate(c1,true,360,2000);
                Rotate(c2,true,270,1500);
                Rotate(c3,true,180,1800);
                Rotate(c4,true,145,2300);
                TranslateTransition stackpaneTransition=new TranslateTransition(new Duration(1000),stackpane);
                stackpaneTransition.setToY(-10);
                stackpaneTransition.play();
                stackpaneTransition.setOnFinished(et -> {
                    stackpaneTransition.stop();
                });
            }));
            new LogInModel(schoolid,staffId,passwd,loginError,vbox,stackpane,c1,c2,c3,c4).start();
        }

    }

    /////When configuration button is clicked
    public void onConfigureButtonClicked() throws IOException {
        Stage window=new Stage();
        window.setTitle("Configuration");
        window.centerOnScreen();
        window.setResizable(false);
        window.initModality(Modality.APPLICATION_MODAL);
        Parent root= FXMLLoader.load(getClass().getResource("../config.fxml"));
        Scene scene=new Scene(root,700,600);
        window.setScene(scene);
        window.show();
    }
}
