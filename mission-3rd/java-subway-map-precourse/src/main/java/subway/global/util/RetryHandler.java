package subway.global.util;

import java.util.function.Supplier;
import subway.global.exception.BusinessException;

public class RetryHandler {

    public static <T> T retryUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (BusinessException e) {
                System.out.println(e.getErrorCode().getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
