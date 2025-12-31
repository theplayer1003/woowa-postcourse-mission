package christmas.global.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CsvReader {


    public <T> List<T> read(String fileName, Function<String, T> mapper, boolean skipHeader) {
        final InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("파일을 찾을 수 없습니다 " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return generateList(reader, mapper, skipHeader);
        } catch (IOException e) {
            throw new IllegalStateException("읽기 중 오류 발생 " + fileName, e);
        }
    }

    public <T> List<T> read(String fileName, Function<String, T> mapper) {
        return read(fileName, mapper, true);
    }

    private <T> List<T> generateList(BufferedReader reader, Function<String, T> mapper, boolean skipHeader)
            throws IOException {
        List<T> results = new ArrayList<>();
        String line;
        boolean isFirstLine = true;

        while ((line = reader.readLine()) != null) {
            if (line.isBlank() || line.startsWith("#")) {
                continue;
            }

            if (isFirstLine) {
                isFirstLine = false;
                if (skipHeader) {
                    continue;
                }
            }

            final T object = mapper.apply(line);
            results.add(object);
        }

        return results;
    }
}

