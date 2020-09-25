package Domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    public static Boolean validateInformation(String name, String email, String picture, int age, String homeTown,String password){
        String regex = "[a-zA-Z]+";
        if(name.matches(regex)){
            if(email.equals(""))
                return false;
            else{
                String EMAIL_REGEX = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
                if(email.matches(EMAIL_REGEX)){
                    String regex_picture = ".";
                    if(picture.contains("."))
                    {
                        if(age>14){
                            return homeTown.matches(regex);
                        }
                        else
                            return false;
                    }
                    else
                        return false;
                }
                else
                    return false;
            }
        }
        else{
            return false;
        }
    }
}
