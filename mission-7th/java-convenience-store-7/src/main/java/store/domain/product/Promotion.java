package store.domain.product;

import java.time.LocalDate;
import store.global.util.Validator;

public class Promotion {
    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate start;
    private final LocalDate end;

    public Promotion(String name, int buy, int get, LocalDate start, LocalDate end) {
        Validator.requireNonNullOrBlank(name, "promotionname");
        Validator.requireNonNull(start, "promotionstartdate");
        Validator.requireNonNull(end, "promotionenddate");

        this.name = name;
        this.buy = buy;
        this.get = get;
        this.start = start;
        this.end = end;
    }
}
