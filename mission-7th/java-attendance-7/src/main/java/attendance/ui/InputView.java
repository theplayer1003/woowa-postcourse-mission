package attendance.ui;

import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class InputView {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM월 dd일 EEEE", Locale.KOREA);

    public String getUserInput() {
        return Console.readLine();
    }

    public void printWelcomMessage(LocalDateTime now) {
        final String format = now.format(FORMATTER);

        System.out.println("오늘은 " + format + "입니다. 기능을 선택해 주세요.");
    }

    public String getMenuSelect(LocalDateTime now) {
        printWelcomMessage(now);

        System.out.print("""
                1. 출석 확인
                2. 출석 수정
                3. 크루별 출석 기록 확인
                4. 제적 위험자 확인
                Q. 종료
                """);

        return getUserInput();
    }

    public String getNickName() {
        System.out.println("닉네임을 입력해 주세요.");

        return getUserInput();
    }

    public String getAttendanceTime() {
        System.out.println("등교 시간을 입력해 주세요.");

        return getUserInput();
    }

    public String getNickNameForChange() {
        System.out.println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.");

        return getUserInput();
    }

    public String getDayForChange() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");

        return getUserInput();
    }

    public String getTimeForChange() {
        System.out.println("언제로 변경하시겠습니까?");

        return getUserInput();
    }
}
