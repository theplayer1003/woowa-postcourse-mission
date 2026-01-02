package oncall.global.util;

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
            throw new ExceptionInInitializerError(
                    "필수 설정 파일 '" + BUNDLE_NAME + ".properties'를 찾을 수 없습니다. " +
                            "resources 폴더를 확인해주세요."
            );
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
