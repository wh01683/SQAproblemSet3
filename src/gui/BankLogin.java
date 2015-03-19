package gui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
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
    private JButton loginButton;
    private JTextField testField;
    private JButton randomPasswordButton;
    private JTextField testTextField;
    private EmailValidator emailValidator = new EmailValidator();
    private Random r = new Random();
    PasswordChecker passwordChecker = new PasswordChecker();

    public BankLogin() {

        ListenForButton listenForButton = new ListenForButton();
        this.loginButton.addActionListener(listenForButton);
        this.randomPasswordButton.addActionListener(listenForButton);


    }

    public static void main(String[] args) {


        JFrame frame = new JFrame("bank_gui");
        frame.setContentPane(new BankLogin().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



        BankLogin bankLogin =  new BankLogin();

    }

    /*if the user would like to try multiple passwords very quickly, they may press the check random password button to
    * set method off
    */

    public void checkRandomPassword() {

        /*executes the method 1 million times.*/
        for (int k = 0; k < 1000000; k++){
            String testPass = ""; /*initializes the test password string*/

            /*generates random passwords between 0 and 25 characters in length. the boundary limits are 8 and 16 for the min
            * and max respectively. the password will consist of random ASCII chars ranging from decimal value 10 to 155,
            * where the value for valid ASCII chars ranges from 33 to 126.
            *
            * *Note: to produce only valid password sizes, use r.nextInt(8)+8
            * **Note: to produce only valid chars, use r.nextInt(93)+33*/
            for (int i = 0; i < (r.nextInt(1) + 25); i++) {
                testPass += (char) (r.nextInt(145) + 10);
            }

            /*sets both the password field and the test text field to the same password. because the tester cannot
            * see the values typed into the password field, I add the exact same string to the visible text field for
            * for verification*/
            testField.setText(testPass);
            passwordField.setText(testPass);
            //System.out.println("text in text field: " + testField.getText());

            /*obtaining verification results for strings in both fields using the same methods (though different parameters)*/
            boolean textFieldStringResult = passwordChecker.checkStringPassWithASCIIValues(testField.getText());
            boolean passwordFieldCharArrayResult = passwordChecker.checkPasswordWithASCIIValues(passwordField.getPassword());
        /*System.out.println("password field result (same string): "+);
        System.out.println("text field result: "+);*/

            /*System.out.printf("String: %16s\nValid: %17s\nMatching results: %6s\n", testField.getText(), textFieldStringResult && passwordFieldCharArrayResult,
                    !(textFieldStringResult ^ passwordFieldCharArrayResult));*/

            /*if, during 1 million test cases the results do NOT match, a notification will be printed to the console
            * and the system will exit. so far, this has no happened.*/
            if(textFieldStringResult ^ passwordFieldCharArrayResult){
                System.out.println("CLASH: RESULTS DO NOT MATCH");
                System.exit(1);
            }

        }

        System.out.println("Done.");
    }

    public void checkPassword(){

        System.out.println(passwordChecker.checkPasswordWithASCIIValues(passwordField.getPassword()));

    }


    private class ListenForButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == loginButton){
                checkPassword();
            }
            if(actionEvent.getSource() == randomPasswordButton){
                checkRandomPassword();
            }
        }
    }


}
