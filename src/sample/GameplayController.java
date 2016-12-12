package sample;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.util.JSONPObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.*;
import java.util.List;

/**
 * Created by Philip Buzzanca on 11/14/2016.
 */
public class GameplayController {
    @FXML
        private Button usernameButton;
    @FXML
        private Label levelNumber;
    @FXML
        private Button homeButton;
    @FXML
        private Label modeTitle;
    @FXML
        private Label targetLabel;
    @FXML
        private Label label00;
    @FXML
        private Label label01;
    @FXML
        private Label label02;
    @FXML
        private Label label03;
    @FXML
        private Label label10;
    @FXML
        private Label label11;
    @FXML
        private Label label12;
    @FXML
        private Label label13;
    @FXML
        private Label label20;
    @FXML
        private Label label21;
    @FXML
        private Label label22;
    @FXML
        private Label label23;
    @FXML
        private Label label30;
    @FXML
        private Label label31;
    @FXML
        private Label label32;
    @FXML
        private Label label33;
    @FXML
        private Button playPauseButton;
    @FXML
        private Label timerLabel;
    @FXML
        private Circle button00;
    @FXML
        private Circle button01;
    @FXML
        private Circle button02;
    @FXML
        private Circle button03;
    @FXML
        private Circle button10;
    @FXML
        private Circle button11;
    @FXML
        private Circle button12;
    @FXML
        private Circle button13;
    @FXML
        private Circle button20;
    @FXML
        private Circle button21;
    @FXML
        private Circle button22;
    @FXML
        private Circle button23;
    @FXML
        private Circle button30;
    @FXML
        private Circle button31;
    @FXML
        private Circle button32;
    @FXML
        private Circle button33;
    @FXML
        private ListView guessedWords;
    @FXML
        private Label scoreLabel;
    @FXML
        private Button restartButton;
    @FXML
        private Button nextButton;
    @FXML
        private Canvas canvas;
    @FXML
        private BorderPane borderpane;


    private int score = 0;

    private static final Integer startTime = 180;
    private Set<String> dictionary;
    private Integer timeRemaining = startTime;
    private Timeline timeline;
    private int targetScore;
    private int level;
    List<String> wordTracker;
    List<Circle> buttonTracker = FXCollections.observableArrayList();
    private Set<String> allPossibleWords;
    Circle[] circles;


    public void playPauseButtonPressed(){
        if (playPauseButton.getText().equals("PAUSE")) {

            timeline.pause();
            playPauseButton.setText("PLAY");
            setAllLettersVisible(false);
            for (Circle c : circles) c.setFill(Paint.valueOf("#909090"));
            buttonTracker.clear();
        }
        else if (playPauseButton.getText().equals("START")) {
            setAllLettersVisible(true);
            playPauseButton.setText("PAUSE");
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1),
                            event -> {
                                timeRemaining--;
                                timerLabel.setText("Time Remaining: "+timeRemaining.toString()+" seconds");
                                if (timeRemaining<=0) {
                                    timeline.stop();
                                    AppMessageDialogSingleton gameOver = AppMessageDialogSingleton.getSingleton();
                                    if (score >= targetScore){

                                        Platform.runLater(() -> {
                                            gameOver.show("Game over", "You win");
                                        });
                                        try {
                                            updateLevelProgress();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    else {
                                        Platform.runLater(() -> {
                                            gameOver.show("Game over", "You lose");
                                        });
                                    }
                                    for(String word : allPossibleWords){
                                        if (!wordTracker.contains(word) && word.length()>2){
                                            guessedWords.getItems().add("Missed " + word + " - " + wordScore(word.length()));
                                        }
                                    }}
                            }
                    )
            );
            timeline.playFromStart();


        }
        else {
            playPauseButton.setText("PAUSE");
            setAllLettersVisible(true);
            if(timeRemaining>0) timeline.play();
        }
    }

    @FXML
    public void handleRestart() {
        timeline.stop();
        updateData(Main.userData.getUsername(), this.modeTitle.getText(), this.level, generateRandomGrid(), this.dictionary);
    }

    @FXML
    public void handleNext() {
        updateData(Main.userData.getUsername(), this.modeTitle.getText(), this.level+1, generateRandomGrid(), this.dictionary);
    }


    private char[][] generateRandomGrid(){
        char[][] randomGrid = new char[4][4];
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                randomGrid[i][j] = (char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1));
            }
        }
        if(BoggleSolver.getMaxScore(BoggleSolver.solve(randomGrid, this.dictionary)) < 30){
           return generateRandomGrid();
        }
        return randomGrid;
    }

    private void updateLevelProgress() throws IOException {
        if(this.level < 8) nextButton.setDisable(false);
        switch(modeTitle.getText()){
            case "Dictionary Words":
                if(this.level == Main.userData.getDictionaryWords()) Main.userData.setDictionaryWords(Main.userData.getDictionaryWords()+1);
                break;
            case "Cities":
                if(this.level == Main.userData.getCities()) Main.userData.setCities(Main.userData.getCities()+1);
                break;
            case "Common Names":
                if(this.level == Main.userData.getCommonNames()) Main.userData.setCommonNames(Main.userData.getCommonNames()+1);
                break;
            default:
                break;
        }
        JsonFactory jsonFactory = new JsonFactory();
        String fileToWrite = new String("users/"+Main.userData.getUsername());
        File file = new File(fileToWrite);
        try (OutputStream out = new FileOutputStream(file)) {
            JsonGenerator generator = jsonFactory.createGenerator(out);
            generator.writeStartObject();
            generator.writeStringField("Username", Main.userData.getUsername());
            generator.writeStringField("Encrypted password", Main.userData.getEncryptedPassword());
            generator.writeNumberField("Dictionary words",Main.userData.getDictionaryWords());
            generator.writeNumberField("Cities",Main.userData.getCities());
            generator.writeNumberField("Common Names",Main.userData.getCommonNames());
            generator.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    private void nodeDragged(MouseEvent event) {
        buttonTracker = FXCollections.observableArrayList();
        buttonTracker.clear();
        if(playPauseButton.getText().equals("PAUSE") && (timeRemaining>0)) {
            if (!buttonTracker.contains((Circle)event.getTarget())) {
                ((Circle) event.getTarget()).setFill(Color.BLUE);
                ((Circle) event.getTarget()).startFullDrag();
                buttonTracker.add((Circle) event.getTarget());
            }
        }
    }

    @FXML
    private void nodeEntered(MouseDragEvent event) {
        if(checkNodeAdjacency((Circle)event.getTarget(),buttonTracker.get(buttonTracker.size()-1))) {
            if (playPauseButton.getText().equals("PAUSE") && (timeRemaining > 0)) {
                if (!buttonTracker.contains(event.getTarget())) {
                    ((Circle) event.getTarget()).setFill(Color.BLUE);
                    buttonTracker.add((Circle) event.getTarget());
                }
            }
        }
    }
    private List<String> keyTracker = FXCollections.observableArrayList();
    private List<Circle> keyCircleTracker = FXCollections.observableArrayList();

    @FXML
    private void handleKeyPressed(KeyEvent key){
        int numOfLast = 0;
        StringBuilder word = new StringBuilder();
        if(playPauseButton.getText().equals("PAUSE")) {
            switch(key.getCode()){
                case ENTER:
                    for(Circle c: circles) c.setFill(Paint.valueOf("#909090"));
                    for(String s : keyTracker) word.append(s.toUpperCase());
                    if (!wordTracker.contains(word.toString()) && allPossibleWords.contains(word.toString())){
                        wordTracker.add(word.toString());
                        score += wordScore(word.toString().length());
                        scoreLabel.setText("Score: " + score + " Points");
                        if(word.length()>2) guessedWords.getItems().add(word.toString()+" - "+wordScore(word.length()));
                    }
                    keyTracker.clear();
                    break;
                default:
                    boolean letterIsPresent = false;
                    for(Circle c : circles){
                        if (c.getAccessibleText().equals(key.getCode().toString())) letterIsPresent = true;
                    }
                    if(letterIsPresent==false){
                        for(Circle c : circles){
                            c.setFill(Paint.valueOf("#909090"));
                        }
                        keyTracker.clear();
                    }
                    if(keyTracker.isEmpty()){
                        boolean letter = false;
                        for  (Circle c : circles){
                            if (c.getAccessibleText().equals(key.getCode().toString())) {
                                c.setFill(Color.BLUE);
                                keyCircleTracker.add(c);
                                letter=true;
                            }
                        }
                        if(letter) keyTracker.add(key.getCode().toString());
                    }
                    else {
                        for (Circle c : keyCircleTracker){
                            boolean hasAnAdjacent = false;
                            for(Circle d : circles){
                                if(checkNodeAdjacency(c,d) && d.getAccessibleText().equals(key.getCode().toString())){
                                    hasAnAdjacent = true;
                                    d.setFill(Color.BLUE);
                                   // keyCircleTracker.add(d);
                                }
                            }

                            if(hasAnAdjacent==false) {
                                c.setFill(Paint.valueOf("#909090"));
                            }
                        }
                        for(Circle c : circles){
                            if(c.getAccessibleText().equals(key.getCode().toString()) && c.getFill().equals(Color.BLUE)) {
                                keyCircleTracker.add(c);
                            }
                            else {
                                if (keyCircleTracker.contains(c)){
                                    keyCircleTracker.remove(c);
                                }
                            }
                        }
                        keyTracker.add(key.getCode().toString());
                        Iterator<Circle> iter = keyCircleTracker.iterator();
                        while(iter.hasNext()){
                            Circle c = iter.next();
                            if(!c.getAccessibleText().equals(key.getCode().toString())) iter.remove();
                        }
                    }
                    break;
            }
        }
    }

    @FXML
    private void handleNodeRelease(MouseDragEvent event) {
        StringBuilder word = new StringBuilder();
        for (Circle circle : buttonTracker) {
            word.append(circle.getAccessibleText());
            circle.setFill(Paint.valueOf("#909090"));
        }
        buttonTracker.clear();
        for (Circle circle : buttonTracker) {
            buttonTracker.remove(circle);
        }
        if (!wordTracker.contains(word.toString()) && dictionary.contains(word.toString())){
            wordTracker.add(word.toString());
            score += wordScore(word.toString().length());
            scoreLabel.setText("Score: " + score + " Points");
            if(word.length()>2) guessedWords.getItems().add(word.toString()+" - "+wordScore(word.length()));
        }

    }

    private int wordScore(int wordLength){
        if (wordLength<=2) return 0;
        switch(wordLength){
            case 3:
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 5;
            default:
                return 11;
        }
    }

    private boolean checkNodeAdjacency(Circle button1, Circle button2){
        if(button1.equals(button00) && (button2.equals(button01) || button2.equals(button11) || button2.equals(button10))) return true;
        else if(button1.equals(button03) && (button2.equals(button02) || button2.equals(button12) || button2.equals(button13))) return true;
        else if(button1.equals(button30) && (button2.equals(button20) || button2.equals(button21) || button2.equals(button31))) return true;
        else if(button1.equals(button33) && (button2.equals(button23) || button2.equals(button22) || button2.equals(button32))) return true;

        else if(button1.equals(button01) && (button2.equals(button00) || button2.equals(button10) || button2.equals(button11) || button2.equals(button12) || button2.equals(button02))) return true;
        else if(button1.equals(button02) && (button2.equals(button01) || button2.equals(button11) || button2.equals(button12) || button2.equals(button13) || button2.equals(button03))) return true;

        else if(button1.equals(button10) && (button2.equals(button00) || button2.equals(button01) || button2.equals(button11) || button2.equals(button21) || button2.equals(button20))) return true;
        else if(button1.equals(button20) && (button2.equals(button10) || button2.equals(button21) || button2.equals(button31) || button2.equals(button30) || button2.equals(button11))) return true;

        else if(button1.equals(button31) && (button2.equals(button30) || button2.equals(button20) || button2.equals(button21) || button2.equals(button22) || button2.equals(button32))) return true;
        else if(button1.equals(button32) && (button2.equals(button31) || button2.equals(button21) || button2.equals(button22) || button2.equals(button23) || button2.equals(button33))) return true;

        else if(button1.equals(button13) && (button2.equals(button03) || button2.equals(button02) || button2.equals(button12) || button2.equals(button22) || button2.equals(button23))) return true;
        else if(button1.equals(button23) && (button2.equals(button13) || button2.equals(button12) || button2.equals(button22) || button2.equals(button32) || button2.equals(button33))) return true;

        else if(button1.equals(button11) && (button2.equals(button00) || button2.equals(button01) || button2.equals(button02) || button2.equals(button12) ||
                button2.equals(button22) || button2.equals(button21) || button2.equals(button20) || button2.equals(button10))) return true;

        else if(button1.equals(button12) && (button2.equals(button01) || button2.equals(button02) || button2.equals(button03) || button2.equals(button13) ||
                button2.equals(button23) || button2.equals(button22) || button2.equals(button21) || button2.equals(button11))) return true;

        else if(button1.equals(button22) && (button2.equals(button11) || button2.equals(button12) || button2.equals(button13) || button2.equals(button23) ||
                button2.equals(button33) || button2.equals(button32) || button2.equals(button31) || button2.equals(button21))) return true;

        else if(button1.equals(button21) && (button2.equals(button10) || button2.equals(button11) || button2.equals(button12) || button2.equals(button22) ||
                button2.equals(button32) || button2.equals(button31) || button2.equals(button30) || button2.equals(button20))) return true;

        else return false;
    }


    public void homeButtonPressed() throws IOException {
        timeline.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordhome.fxml"));
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.setScene(new Scene((Pane)loader.load()));
        Controller cont = loader.<Controller>getController();
        cont.getLoginButton().setText(Main.userData.getUsername());
        cont.getCnpButton().setVisible(false);
        cont.getSelectModeMenu().setDisable(false);
        cont.getStartButton().setDisable(false);
        stage.show();
    }

    public void updateData(String name, String title, int level, char[][] randomGrid, Set<String> dictionary) {
        allPossibleWords = BoggleSolver.solve(randomGrid, dictionary);
        guessedWords.getItems().clear();
        playPauseButton.setText("START");
        nextButton.setDisable(true);
        timeRemaining = startTime;
        this.score = 0;
        this.dictionary = dictionary;
        this.level = level;
        int maxScore = BoggleSolver.getMaxScore(allPossibleWords);
        switch(level){
            case 1:
                this.targetScore = (int)Math.floor(maxScore * (0.1));
                break;
            case 2:
                this.targetScore = (int)Math.floor(maxScore * (0.2));
                break;
            case 3:
                this.targetScore = (int)Math.floor(maxScore * (0.3));
                break;
            case 4:
                this.targetScore = (int)Math.floor(maxScore * (0.4));
                break;
            case 5:
                this.targetScore = (int)Math.floor(maxScore * (0.5));
                break;
            case 6:
                this.targetScore = (int)Math.floor(maxScore * (0.6));
                break;
            case 7:
                this.targetScore = (int)Math.floor(maxScore * (0.7));
                break;
            default:
            case 8:
                this.targetScore = (int)Math.floor(maxScore * (0.8));
                break;
        }
        usernameButton.setText(name);
        modeTitle.setText(title);
        levelNumber.setText("Level "+level);
        targetLabel.setText("Target: " + this.targetScore + " points");
        timerLabel.setText("Time Remaining: "+startTime.toString()+" seconds");
        label00.setText(Character.toString(randomGrid[0][0]));
        button00.setAccessibleText(Character.toString(randomGrid[0][0]));
        label01.setText(Character.toString(randomGrid[0][1]));
        label02.setText(Character.toString(randomGrid[0][2]));
        label03.setText(Character.toString(randomGrid[0][3]));
        label10.setText(Character.toString(randomGrid[1][0]));
        label11.setText(Character.toString(randomGrid[1][1]));
        label12.setText(Character.toString(randomGrid[1][2]));
        label13.setText(Character.toString(randomGrid[1][3]));
        label20.setText(Character.toString(randomGrid[2][0]));
        label21.setText(Character.toString(randomGrid[2][1]));
        label22.setText(Character.toString(randomGrid[2][2]));
        label23.setText(Character.toString(randomGrid[2][3]));
        label30.setText(Character.toString(randomGrid[3][0]));
        label31.setText(Character.toString(randomGrid[3][1]));
        label32.setText(Character.toString(randomGrid[3][2]));
        label33.setText(Character.toString(randomGrid[3][3]));
        playPauseButton.setText("START");
        scoreLabel.setText("Score: 0 Points");
        setAllLettersVisible(false);

        wordTracker = FXCollections.observableArrayList();
        button00.setAccessibleText(Character.toString(randomGrid[0][0]));
        button01.setAccessibleText(Character.toString(randomGrid[0][1]));
        button02.setAccessibleText(Character.toString(randomGrid[0][2]));
        button03.setAccessibleText(Character.toString(randomGrid[0][3]));
        button10.setAccessibleText(Character.toString(randomGrid[1][0]));
        button11.setAccessibleText(Character.toString(randomGrid[1][1]));
        button12.setAccessibleText(Character.toString(randomGrid[1][2]));
        button13.setAccessibleText(Character.toString(randomGrid[1][3]));
        button20.setAccessibleText(Character.toString(randomGrid[2][0]));
        button21.setAccessibleText(Character.toString(randomGrid[2][1]));
        button22.setAccessibleText(Character.toString(randomGrid[2][2]));
        button23.setAccessibleText(Character.toString(randomGrid[2][3]));
        button30.setAccessibleText(Character.toString(randomGrid[3][0]));
        button31.setAccessibleText(Character.toString(randomGrid[3][1]));
        button32.setAccessibleText(Character.toString(randomGrid[3][2]));
        button33.setAccessibleText(Character.toString(randomGrid[3][3]));
        circles = new Circle[]{    button00, button01, button02, button03,
                button10, button11, button12, button13,
                button20, button21, button22, button23,
                button30, button31, button32, button33};
    }

    private void setAllLettersVisible(boolean b){
        label00.setVisible(b);
        label01.setVisible(b);
        label02.setVisible(b);
        label03.setVisible(b);
        label10.setVisible(b);
        label11.setVisible(b);
        label12.setVisible(b);
        label13.setVisible(b);
        label20.setVisible(b);
        label21.setVisible(b);
        label22.setVisible(b);
        label23.setVisible(b);
        label30.setVisible(b);
        label31.setVisible(b);
        label32.setVisible(b);
        label33.setVisible(b);
    }
}