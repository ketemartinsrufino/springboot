package cade.application.generateprepositions;

/**
 * Created by kete on 17/08/17.
 */
public class PrepositionProcessor {

    public static String join(String preposition, String article ) {
        String result = "";
        if(preposition.equals("de")) {
            result = "d" + article;
        } else if(preposition.equals("em")) {
            result = "n" + article;
        } else if(preposition.equals("a")) {
            if (article.equals("a")) {
                result = "à";
            } else {
                result = "a" + article;
            }
        }

        return result;
    }
}
