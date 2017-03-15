package Buzzword;

/**
 * Created by Phil on 11/26/2016.
 */
public class UserData {
    private String username;

    public int getThreeLetterWords() {
        return threeLetterWords;
    }

    public void setThreeLetterWords(int threeLetterWords) {
        this.threeLetterWords = threeLetterWords;
    }

    private int threeLetterWords;
    private int commonNames;
    private int dictionaryWords;

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    private String encryptedPassword;

    public String getUsername() {
        return username;
    }


    public int getCommonNames() {
        return commonNames;
    }

    public int getDictionaryWords() {
        return dictionaryWords;
    }



    public UserData(String username){
        this.username = username;
        threeLetterWords = 1;
        commonNames = 1;
        dictionaryWords = 1;
    }

    public void setCities(int i){
        if (i<=1) threeLetterWords = 1;
        else if (i>=8) threeLetterWords = 8;
        else threeLetterWords = i;
    }

    public void setCommonNames(int i){
        if (i<=1) commonNames = 1;
        else if (i>=8) commonNames = 8;
        else commonNames = i;
    }

    public void setDictionaryWords(int i){
        if (i<=1) dictionaryWords = 1;
        else if (i>=8) dictionaryWords = 8;
        else dictionaryWords = i;
    }

    public void setUsername(String username){ this.username = username; }

    public void setEncryptedPassword(String encryptedPassword) { this.encryptedPassword = encryptedPassword; }


}
