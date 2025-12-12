package subway.domain.exception;

import subway.global.exception.ErrorCode;
import subway.global.util.MessageUtils;

public enum SubWayErrorCode implements ErrorCode {
    STATION_NAME_IS_DUPLICATE("error.station.name.duplicate"),
    STATION_NAME_LENGTH_INVALID("error.station.name.length"),
    LINE_NAME_LENGTH_INVALID("error.line.name.length"),
    LINE_ADD_INVALID_INDEX("error.line.addStation.index"),
    LINE_REMOVE_NOTFOUND("error.line.removeStation.notfound"),
    STATION_ALREADY_EXIST("error.station.find.exist"),
    LINE_ALREADY_EXIST("error.line.find.exist"),
    LINE_IS_NOT_EXIST("error.line.addStation.notfound"),
    STATION_IS_NOT_EXIST("error.station.isexist.notfound"),
    LINE_CANNOT_DELETE_MIN_SIZE("error.line.removeStation.minsize");

    private final String messageKey;

    SubWayErrorCode(String messageKey) {
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
