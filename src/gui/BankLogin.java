package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Robert Howerton
 * 03/18/2015
 *
 * Regular Expression used for e-mail validation:
 *
 * ^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$
 *
 *
 *
 */



public class BankLogin {


    private Pattern pattern;
    private Matcher matcher;

    private JPanel panel1;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton createNewAccountButton;
    private JButton loginButton;
    private JTextField testField;
    private JTextField testTextField;
    private EmailValidator emailValidator = new EmailValidator();
    PasswordChecker passwordChecker = new PasswordChecker();

    public BankLogin() {

        ListenForButton listenForButton = new ListenForButton();
        this.loginButton.addActionListener(listenForButton);

    }

    public static void main(String[] args) {


        JFrame frame = new JFrame("bank_gui");
        frame.setContentPane(new BankLogin().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



        BankLogin bankLogin =  new BankLogin();

    }

    public void attemptLogin(){

        checkPassword(passwordField.getPassword());
    }

    public void checkPassword(char[] password){


        System.out.println(testField.getText());
        System.out.println(passwordChecker.checkPasswordWithASCIIValues(testField.getText()));

    }


    private class ListenForButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == loginButton){
                checkPassword(passwordField.getPassword());
            }
        }
    }


}
