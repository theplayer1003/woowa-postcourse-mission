package oncall.domain;

import oncall.domain.exception.OncallErrorCode;
import oncall.global.exception.BusinessException;
import oncall.global.util.Validator;

public record StaffName(String name) {
    public StaffName {
        Validator.requireNonNullOrBlank(name, "staffName");
        validateLength(name);
    }

    private void validateLength(String name) {
        if (name.length() > 5) {
            throw new BusinessException(OncallErrorCode.STAFFNAME_VALIDATE_MAXLENGTH, name.length());
        }
    }
}
