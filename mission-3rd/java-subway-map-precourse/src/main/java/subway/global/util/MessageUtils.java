package subway.global.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageUtils {
    private static final String BUNDLE_NAME = "exceptionMessages";
    private static final ResourceBundle resourceBundle = loadBundle();

    private static ResourceBundle loadBundle() {
        try {
            return ResourceBundle.getBundle(BUNDLE_NAME);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    public static String getMessage(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            return key;
        }
    }

    public static String getMessage(String key, Object... args) {
        final String pattern = getMessage(key);
        return MessageFormat.format(pattern, args);
    }
}
