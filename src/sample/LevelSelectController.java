package sample;

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

import static sample.Main.userData;

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


    void updateLevelData(String name, String title){
        this.title = title;
        usernameButton.setText(name);
        modeTitle.setText(title);
        System.out.println(title);
        System.out.println(userData.getAnimals());
        switch (title) {
            case "Animals":
                for (int i = 1; i <= userData.getAnimals(); i++) {
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
            case "Celebrities":
                for (int i = 1; i <= userData.getCelebrities(); i++) {
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
            case "Animals":
                return (userData.getAnimals());
            case "Celebrities":
                return (userData.getCelebrities());
            default:
                return userData.getDictionaryWords();
        }
    }



    public void homeButtonPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordhome.fxml"));
        Stage stage = (Stage) usernameButton.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        Controller cont = loader.getController();
        cont.getLoginButton().setText(Main.userData.getUsername());
        cont.getCnpButton().setVisible(false);
        cont.getSelectModeMenu().setDisable(false);
        cont.getStartButton().setDisable(false);
        stage.show();
    }

    public void circleOnePressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordgameplay.fxml"));
        Stage stage = (Stage) circleOne.getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        GameplayController cont = loader.getController();
        generateRandomGrid();
        cont.updateData(usernameButton.getText(), modeTitle.getText(), 1,"Target: 10 points", randomGrid, 10);
        stage.show();
    }

    public void circleTwoPressed() throws IOException {
        System.out.println(getLevel());
        if(getLevel()>=2) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleTwo.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(), 2, "Target: 15 points", randomGrid, 15);
            stage.show();
        }
    }

    public void circleThreePressed() throws IOException {
        if(getLevel()>=3) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleThree.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(), 3, "Target: 20 points", randomGrid, 20);
            stage.show();
        }
    }

    public void circleFourPressed() throws IOException {
        if(getLevel()>=4) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleFour.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(), 4, "Target: 25 points", randomGrid, 25);
            stage.show();
        }
    }

    public void circleFivePressed() throws IOException {
        if(getLevel()>=5) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleFive.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(), 5, "Target: 30 points", randomGrid, 30);
            stage.show();
        }
    }

    public void circleSixPressed() throws IOException {
        if(getLevel()>=6) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleSix.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(), 6, "Target: 35 points", randomGrid, 35);
            stage.show();
        }
    }

    public void circleSevenPressed() throws IOException {
        if(getLevel()>=7) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleSeven.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(), 7, "Target: 40 points", randomGrid, 40);
            stage.show();
        }
    }

    public void circleEightPressed() throws IOException {
        if(getLevel()>=8) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("buzzwordgameplay.fxml"));
            Stage stage = (Stage) circleEight.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            GameplayController cont = loader.getController();
            generateRandomGrid();
            cont.updateData(usernameButton.getText(), modeTitle.getText(), 8, "Target: 50 points", randomGrid, 50);
            stage.show();
        }
    }

    private void generateRandomGrid(){
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                randomGrid[i][j] = (char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1));
                if ((!(randomGrid[i][j] == 'A' || randomGrid[i][j] == 'E' || randomGrid[i][j] == 'I' || randomGrid[i][j] == 'O' || randomGrid[i][j] == 'U')) && reroll(33)==true){
                    randomGrid[i][j] = generateRandomVowel(); // 33% chance to reroll non-vowels to guaranteed vowels

                }
                if ((randomGrid[i][j] == 'Z' || randomGrid[i][j] == 'Q' || randomGrid[i][j] == 'X' || randomGrid[i][j] == 'J' || randomGrid[i][j] == 'K' || randomGrid[i][j] == 'V') && reroll(200)==true){
                    randomGrid[i][j] = (char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1)); // 75% chance to reroll on uncommon characters
                }
            }
        }
    }

    private char generateRandomVowel(){
        Random r = new Random();
        int randomInt = r.nextInt(5)+1;
        switch(randomInt){
            case 1:
                return 'A';
            case 2:
                return 'E';
            case 3:
                return 'I';
            case 4:
                return 'O';
            case 5:
                return 'U';
            default:
                return 'E';
        }
    }

    private boolean reroll(int chance){
        if (chance>=100) return true;
        Random r = new Random();
        int randomInt = r.nextInt(100)+1;
        if (randomInt>chance) return false;
        else return true;
    }


    /*
    private char[] generateFourLetterWord(){

        String word="word";
        URL wordsResource = getClass().getClassLoader().getResource("common words.txt");
        assert wordsResource != null;
        int toSkip = new Random().nextInt(995);
        try (Stream<String> lines = Files.lines(Paths.get(wordsResource.toURI()))){
            //noinspection OptionalGetWithoutIsPresent
            word = lines.skip(toSkip).findFirst().get();
        } catch (Exception e){
            e.printStackTrace();
        }
        if (word.length()==4)
                return word.toUpperCase().toCharArray();
        else {
           return generateFourLetterWord();
        }
    }
    */

}
