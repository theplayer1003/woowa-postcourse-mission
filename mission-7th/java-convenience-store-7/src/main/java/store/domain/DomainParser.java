package store.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import store.service.dto.request.OrderRequestDto;

public class DomainParser {

    public static Map<String, Integer> orderToMap(OrderRequestDto dto) {
        final String orderString = dto.order();

        return Arrays.stream(orderString.split(","))
                .map(s -> s.replace("[", "")
                        .replace("]", "")
                        .trim())
                .map(s -> s.split("-"))
                .collect(Collectors.toMap(
                        arr -> arr[0].trim(),
                        arr -> Integer.parseInt(arr[1].trim())
                ));
    }
}
