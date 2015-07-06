package demo.controller;




/**
 * Created by Nguyen Hoang on 06-Jul-15.
 */
public class Check {
    public static boolean Check(String string){
        return !string.contains(" ") && !string.isEmpty() && string!=null;
    }
}
