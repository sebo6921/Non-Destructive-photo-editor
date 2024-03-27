package cosc202.andie;

import java.util.*;
import java.util.prefs.Preferences;

public class LanguageTest {

    // Hello world app, but with internationalisation
    public static void main(String[] args) {
        Preferences prefs = Preferences.userNodeForPackage(LanguageTest.class);

        Locale.setDefault(new Locale(prefs.get("language", "en"), 
                prefs.get("country", "NZ")));

        ResourceBundle bundle = ResourceBundle.getBundle("MessageBundle");
        System.out.println(bundle.getString("greeting"));
        
        Scanner sc = new Scanner(System.in);
        System.out.println(bundle.getString("GaussianBlurDesc"));
        int language = sc.nextInt();
        
        switch (language){
            case 1:
                prefs.put("language", "en");
                prefs.put("country", "NZ");
                break;
            case 2:
                prefs.put("language", "es");
                prefs.put("country", "ES");
                break;
            default:
                System.out.println("Invalid Choice. Defaulting to English");
                prefs.put("language", "en");
                prefs.put("country", "NZ");
                break;
        }
        
        
        sc.close();
        
    }
}
