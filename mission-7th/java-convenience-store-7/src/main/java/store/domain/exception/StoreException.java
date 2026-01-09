package store.domain.exception;

import store.global.exception.ErrorCode;
import store.global.util.MessageUtils;

public enum StoreException implements ErrorCode {
    PRODUCT_NOT_EXIST("error.inventory.items.notexist"),
    PRODUCT_NOT_ENOUGH("error.service.requestQty.notenough");


    private final String messageKey;

    StoreException(String messageKey) {
        this.messageKey = messageKey;
    }

    @Override
    public String getMessage() {
        return MessageUtils.getMessage(messageKey);
    }

    @Override
    public String getMessage(Object... args) {
        return MessageUtils.getMessage(messageKey, args);
    }
}
