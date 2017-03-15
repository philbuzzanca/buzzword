package Buzzword.Controller;

import Buzzword.Model.BoggleGraph;
import Buzzword.Model.BoggleVertex;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;
import java.util.Set;

import static Buzzword.Controller.Main.userData;

/**
 * Created by Philip Buzzanca on 11/14/2016.
 */
public class LevelSelectController {
    @FXML
        private Button usernameButton;
    @FXML
        private Button homeButton;
    @FXML
        private Label modeTitle;
    @FXML
        private Circle circleOne;
    @FXML
        private Circle circleTwo;
    @FXML
        private Circle circleThree;
    @FXML
        private Circle circleFour;
    @FXML
        private Circle circleFive;
    @FXML
        private Circle circleSix;
    @FXML
        private Circle circleSeven;
    @FXML
        private Circle circleEight;
    @FXML
            private Label label1;
    @FXML
            private Label label2;
    @FXML
            private Label label3;
    @FXML
            private Label label4;
    @FXML
            private Label label5;
    @FXML
            private Label label6;
    @FXML
            private Label label7;
    @FXML
            private Label label8;

    private String title = "Dictionary Words";
    private char[][] randomGrid = new char[4][4];
    private BoggleGraph randomGraph = new BoggleGraph();
    private Set<String> dictionary;


    void updateLevelData(String name, String title, Set<String> dictionary){
        this.dictionary = dictionary;
        this.title = title;
        usernameButton.setText(name);
        modeTitle.setText(title);
        System.out.println(title);
        System.out.println(userData.getThreeLetterWords());
        switch (title) {
            case "Three Letter Words":
                for (int i = 1; i <= userData.getThreeLetterWords(); i++) {
                    switch (i) {
                        case 1:
                            break;
                        case 2:
                            label2.setTextFill(Color.WHITE);
                            circleTwo.setFill(Paint.valueOf("#909090"));
                            break;
                        case 3:
                            label3.setTextFill(Color.WHITE);
                            circleThree.setFill(Paint.valueOf("#909090"));
                            break;
                        case 4:
                            label4.setTextFill(Color.WHITE);
                            circleFour.setFill(Paint.valueOf("#909090"));
                            break;
                        case 5:
                            label5.setTextFill(Color.WHITE);
                            circleFive.setFill(Paint.valueOf("#909090"));
                            break;
                        case 6:
                            label6.setTextFill(Color.WHITE);
                            circleSix.setFill(Paint.valueOf("#909090"));
                            break;
                        case 7:
                            label7.setTextFill(Color.WHITE);
                            circleSeven.setFill(Paint.valueOf("#909090"));
                            break;
                        case 8:
                            label8.setTextFill(Color.WHITE);
                            circleEight.setFill(Paint.valueOf("#909090"));
                            break;
                        default:
                            break;
                    }
                }
                break;
            case "Common Names":
                for (int i = 1; i <= userData.getCommonNames(); i++) {
                    switch (i) {
                        case 1:
                            break;
                        case 2:
                            label2.setTextFill(Color.WHITE);
                            circleTwo.setFill(Paint.valueOf("#909090"));
                            break;
                        case 3:
                            label3.setTextFill(Color.WHITE);
                            circleThree.setFill(Paint.valueOf("#909090"));
                            break;
                        case 4:
                            label4.setTextFill(Color.WHITE);
                            circleFour.setFill(Paint.valueOf("#909090"));
                            break;
                        case 5:
                            label5.setTextFill(Color.WHITE);
                            circleFive.setFill(Paint.valueOf("#909090"));
                            break;
                        case 6:
                            label6.setTextFill(Color.WHITE);
                            circleSix.setFill(Paint.valueOf("#909090"));
                            break;
                        case 7:
                            label7.setTextFill(Color.WHITE);
                            circleSeven.setFill(Paint.valueOf("#909090"));
                            break;
                        case 8:
                            label8.setTextFill(Color.WHITE);
                            circleEight.setFill(Paint.valueOf("#909090"));
                            break;
                        default:
                            break;
                    }
                }
                break;
            default:
                for (int i = 1; i <= userData.getDictionaryWords(); i++) {
                    switch (i) {
                        case 1:
                            break;
                        case 2:
                            label2.setTextFill(Color.WHITE);
                            circleTwo.setFill(Paint.valueOf("#909090"));
                            break;
                        case 3:
                            label3.setTextFill(Color.WHITE);
                            circleThree.setFill(Paint.valueOf("#909090"));
                            break;
                        case 4:
                            label4.setTextFill(Color.WHITE);
                            circleFour.setFill(Paint.valueOf("#909090"));
                            break;
                        case 5:
                            label5.setTextFill(Color.WHITE);
                            circleFive.setFill(Paint.valueOf("#909090"));
                            break;
                        case 6:
                            label6.setTextFill(Color.WHITE);
                            circleSix.setFill(Paint.valueOf("#909090"));
                            break;
                        case 7:
                            label7.setTextFill(Color.WHITE);
                            circleSeven.setFill(Paint.valueOf("#909090"));
                            break;
                        case 8:
                            label8.setTextFill(Color.WHITE);
                            circleEight.setFill(Paint.valueOf("#909090"));
                            break;
                        default:
                            break;
                    }
                }
                break;
        }
    }

    private int getLevel(){
        switch (title) {
            case "Three Letter Words":
                return (userData.getThreeLetterWords());
            case "Common Names":
                return (userData.getCommonNames());
            default:
                return userData.getDictionaryWords();
        }
    }



    public void homeButtonPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordhome.fxml"));
        Stage stage = (Stage) usernameButton.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        HomeController cont = loader.getController();
        cont.getLoginButton().setText(Main.userData.getUsername());
        cont.getCnpButton().setVisible(false);
        cont.getSelectModeMenu().setDisable(false);
        cont.getStartButton().setDisable(false);
        stage.show();
    }

    public void circleOnePressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("XML/buzzwordgameplay.fxml"));
        Stage stage = (Stage) circleOne.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        GameplayController cont = loader.getController();
        generateRandomGrid();
        cont.updateData(usernameButton.getText(), modeTitle.getText(), 1, randomGrid, dictionary);
        stage.show();
    }

    public void circleTwoPressed() throws IOException {
        System.out.println(getLevel());
        if(getLevel()>=2) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("XML/buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleTwo.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(), 2, randomGrid, dictionary);
            stage.show();
        }
    }

    public void circleThreePressed() throws IOException {
        if(getLevel()>=3) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("XML/buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleThree.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(), 3, randomGrid, dictionary);
            stage.show();
        }
    }

    public void circleFourPressed() throws IOException {
        if(getLevel()>=4) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("XML/buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleFour.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(), 4, randomGrid, dictionary);
            stage.show();
        }
    }

    public void circleFivePressed() throws IOException {
        if(getLevel()>=5) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("XML/buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleFive.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(), 5, randomGrid, dictionary);
            stage.show();
        }
    }

    public void circleSixPressed() throws IOException {
        if(getLevel()>=6) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("XML/buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleSix.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(), 6, randomGrid, dictionary);
            stage.show();
        }
    }

    public void circleSevenPressed() throws IOException {
        if(getLevel()>=7) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("XML/buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleSeven.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(), 7, randomGrid, dictionary);
            stage.show();
        }
    }

    public void circleEightPressed() throws IOException {
        if(getLevel()>=8) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("XML/buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleEight.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(),8, randomGrid, dictionary);
            stage.show();
        }
    }

    private BoggleGraph generateGraphFromArray(char[][] grid){
        BoggleGraph toReturn = new BoggleGraph();
        BoggleVertex[][] vertices = new BoggleVertex[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                BoggleVertex v = new BoggleVertex(grid[i][j]);
                vertices[i][j]=v;
                if((i-1)>=0 && (j-1)>=0){
                    v.addNeighbor(vertices[i][j]);
                }
                if(i-1>=0){
                    v.addNeighbor(vertices[i-1][j]);
                }
                if(j-1>=0){
                    v.addNeighbor(vertices[i][j-1]);
                }
                toReturn.addVertex(v);
            }
        }
        return toReturn;
    }

    private void generateRandomGrid(){
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                randomGrid[i][j] = (char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1));
            }
        }
        randomGraph = generateGraphFromArray(randomGrid);
        if(BoggleSolver.getMaxScore(BoggleSolver.solveGraph(randomGraph, this.dictionary)) < 30){
            generateRandomGrid();
        }
    }

    private boolean reroll(int chance){
        if (chance>=100) return true;
        Random r = new Random();
        int randomInt = r.nextInt(100)+1;
        if (randomInt>chance) return false;
        else return true;
    }


}
