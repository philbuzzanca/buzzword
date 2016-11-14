package sample;

public class Controller {
    public void loginButtonClicked(){
        Main.showLoginScreen(LoginWindow.getLogin());
        LoginWindow.getLogin().getLoginField().clear();
        LoginWindow.getLogin().getPasswordField().clear();
    }
}
