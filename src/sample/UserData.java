package sample;

/**
 * Created by Phil on 11/26/2016.
 */
public class UserData {
    private String username;
    private int animals;
    private int celebrities;
    private int dictionaryWords;

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    private String encryptedPassword;

    public String getUsername() {
        return username;
    }

    public int getAnimals() {
        return animals;
    }

    public int getCelebrities() {
        return celebrities;
    }

    public int getDictionaryWords() {
        return dictionaryWords;
    }



    public UserData(String username){
        this.username = username;
        animals = 1;
        celebrities = 1;
        dictionaryWords = 1;
    }

    public void setAnimals(int i){
        if (i<=1) animals = 1;
        else if (i>=8) animals = 8;
        else animals = i;
    }

    public void setCelebrities(int i){
        if (i<=1) celebrities = 1;
        else if (i>=8) celebrities = 8;
        else celebrities = i;
    }

    public void setDictionaryWords(int i){
        if (i<=1) dictionaryWords = 1;
        else if (i>=8) dictionaryWords = 8;
        else dictionaryWords = i;
    }

    public void setUsername(String username){ this.username = username; }

    public void setEncryptedPassword(String encryptedPassword) { this.encryptedPassword = encryptedPassword; }


}
