package gui;
import java.lang.Character;
import java.util.Hashtable;

/**
 * Created by robert on 3/18/2015.
 *
 * List of allowed special characters used in consideration for this checker..
 *
 *      ! 	" 	# 	$ 	% 	& 	' 	( 	) 	* 	+ 	, 	- 	. 	/ 	:
 *      ; 	< 	= 	> 	? 	@ 	[ 	\ 	] 	^ 	_ 	` 	{ 	| 	} 	~
 *
 *
 */


public class PasswordChecker {



PasswordChecker(){

}


    /*one idea for checking password: allowed password characters have ASCII values of 33-126 inclusive, check
    * each individual character for ASCII ranges outside of this.
    * ASCII special character ranges: 33-47, 58-64, 91-96, 123-126
    * ASCII Number ranges: 48-57
    * ASCII lowercase letter ranges: 97-122
    * ASCII uppercase letter ranges: 65-90
    *
    * */

public boolean checkPasswordWithASCIIValues(char[] userPassword){

    boolean accepted = false;
    int numberOfSpecialCharacters = 0;
    int numberOfLowerCaseLetters = 0;
    int numberOfUpperCaseLetters = 0;
    int numberOfNumbers = 0;

    if(userPassword.length>16 | userPassword.length<8){
        return false;
    }
    else{
        for(char c : userPassword){
            if((int)c>126 | (int)c<33){
                return false;
            }
            else if((int)c>47 && (int)c<58){
                numberOfNumbers++;
            }
            else if((int)c>96 && (int)c<123){
                numberOfLowerCaseLetters++;
            }
            else if((int)c>64 && (int)c<91){
                numberOfUpperCaseLetters++;
            }
            else if(((int)c>32 && (int)c<48) | ((int)c>57 && (int)c<65) | ((int)c>90 && (int)c<97) | ((int)c>122 && (int)c<127)){
                numberOfSpecialCharacters++;
            }
        }

        if(numberOfLowerCaseLetters>0 && numberOfNumbers>0 && numberOfSpecialCharacters>0 && numberOfUpperCaseLetters>0){
            return true;
        }
    }
    return accepted;
}



/*another idea: store the password in a hashtable where each character is placed once. check each one*/
 public boolean checkPassword(String userPassword){
 Hashtable<Integer, Character> currentPasswordHashtable = new Hashtable<Integer, Character>(userPassword.length());

    boolean accepted = false;

     for(char c : userPassword.toCharArray()){
        currentPasswordHashtable.putIfAbsent((int)c, c);
    }




    return accepted;
}

}
