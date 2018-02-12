import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Application {

    String username, password;
    BufferedReader in;
    PrintWriter out;
    Socket socket;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void run() throws IOException{
    }

    private void ConnectIP(){
        Stage window = new Stage();
        GridPane grid = new GridPane();

        TextField ipTextField = new TextField();
        ipTextField.setPromptText("IP");
        TextField portTextField = new TextField();
        portTextField.setPromptText("PORT");
        Button btnOK = new Button();
        btnOK.setText("OK");

        btnOK.setOnAction(e->{
            try {
                socket = new Socket(ipTextField.getText(), Integer.parseInt(portTextField.getText()));
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                window.close();
                Authorization();
            }catch (Exception ex){
            }
        });

        grid.add(new Label("Enter an IP: "), 0, 0);
        grid.add(ipTextField, 1, 0);
        grid.add(new Label("Enter a port: "), 0, 1);
        grid.add(portTextField, 1, 1);
        grid.add(btnOK, 0, 2);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.show();
    }

    private void Authorization() {
        Stage window = new Stage();

        GridPane grid = new GridPane();

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        TextField rUsername = new TextField();
        username.setPromptText("Username");
        PasswordField rPassword = new PasswordField();
        password.setPromptText("Password");
        PasswordField rrPassword = new PasswordField();
        password.setPromptText("Repeat password");

        Button login = new Button();
        login.setText("Log in");
        login.setDisable(true);
        Button register = new Button();
        register.setText("Sign up");
        register.setDisable(true);

        username.textProperty().addListener(
                e-> login.setDisable(username.getText().length() == 0
                        || password.getText().length() == 0));

        password.textProperty().addListener(
                e-> login.setDisable(username.getText().length() == 0
                        || password.getText().length() == 0));

        rUsername.textProperty().addListener(
                e-> register.setDisable(!(rUsername.getText().length() > 0
                        && rrPassword.getText().length() > 0
                        && rrPassword.getText().equals(rPassword.getText()))
                ));

        rPassword.textProperty().addListener(
                e-> register.setDisable(!(rUsername.getText().length() > 0
                        && rrPassword.getText().length() > 0
                        && rrPassword.getText().equals(rPassword.getText()))
                ));

        rrPassword.textProperty().addListener(
                e-> register.setDisable(!(rUsername.getText().length() > 0
                        && rrPassword.getText().length() > 0
                        && rrPassword.getText().equals(rPassword.getText()))
                ));

        register.setOnAction(e->{
            try{
                while (true) {
                    out.println("REGISTER");
                    String line = in.readLine();
                    register.setText(line);
                    break;
                }
            }catch (Exception ex){

            }
        });

        grid.add(new Label("Log in: "), 1,0);
        grid.add(new Label("Username: "), 0,1);
        grid.add(username, 1, 1);
        grid.add(new Label("Password: "), 0,2);
        grid.add(password, 1, 2);
        grid.add(login, 1, 3);

        grid.add(new Label("Sign up: "), 3,0);
        grid.add(new Label("Username: "), 2,1);
        grid.add(rUsername, 3, 1);
        grid.add(new Label("Password: "), 2,2);
        grid.add(rPassword, 3, 2);
        grid.add(new Label("Confirm: "), 2,3);
        grid.add(rrPassword, 3, 3);
        grid.add(register, 3, 4);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.centerOnScreen();
        window.show();
    }

    private void SignUp(String username, String password){
    }

    private void Chat(){
        Stage window = new Stage();
        window.setTitle("Telegram 2");
        window.setWidth(350);
        window.setHeight(600);

        GridPane grid = new GridPane();

        TextField message = new TextField();
        message.setText("Your message");
        grid.add(new TextArea(), 0, 0);
        grid.add(message, 0, 1);

        Scene chatScene = new Scene(grid);
        window.setScene(chatScene);
        window.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConnectIP();
    }
}
