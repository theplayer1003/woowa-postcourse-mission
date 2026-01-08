package attendance.ui;

import attendance.domain.crew.Crews;
import attendance.global.util.CsvReader;
import attendance.service.dto.AttendanceLogDto;
import attendance.service.dto.AttendanceRegistResponseDto;
import attendance.service.AttendanceService;
import attendance.service.dto.ChangeLogDto;
import attendance.service.dto.CrewConditionDto;
import attendance.service.dto.CrewRequestDto;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final CsvReader csvReader;

    private AttendanceService attendanceService;

    public Controller(InputView inputView, OutputView outputView, CsvReader csvReader) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.csvReader = csvReader;
    }

    public void run() {
        init();

        while (true) {
            final String command = inputView.getMenuSelect(DateTimes.now());
            if ("Q".equals(command)) {
                return;
            }

            processCommand(command);
        }
    }

    private void processCommand(String command) {
        if (command.equals("1")) {
            attendanceCheck();
            return;
        }
        if (command.equals("2")) {
            attendanceUpdate();
            return;
        }
        if (command.equals("3")) {
            allCrewLog();
            return;
        }
        if (command.equals("4")) {
            expulsionCheck();
            return;
        }

        throw new IllegalArgumentException("[ERROR] 올바르지 않은 입력입니다.");
    }

    private void expulsionCheck() {
        final List<CrewConditionDto> crewConditionDtos = attendanceService.checkAllCrewStatus();

        outputView.printAllCrewCondition(crewConditionDtos);
    }

    private void allCrewLog() {
        final String nickName = inputView.getNickName();

        final AttendanceLogDto attendanceInfoByName = attendanceService.findAttendanceInfoByName(nickName);

        outputView.printAttendanceLog(attendanceInfoByName);
    }

    private void attendanceUpdate() {
        final String nickName = inputView.getNickNameForChange();
        final String dayString = inputView.getDayForChange();
        final String timeString = inputView.getTimeForChange();

        int day = parseDay(dayString);
        final LocalDate targetDate = DateTimes.now().withDayOfMonth(day).toLocalDate();
        final LocalTime targetTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm", Locale.KOREA));
        final LocalDateTime localDateTime = LocalDateTime.of(targetDate, targetTime);

        final ChangeLogDto changeLogDto = attendanceService.changeAttendance(nickName, localDateTime);

        outputView.printAttendanceChange(changeLogDto);
    }

    private int parseDay(String dayString) {
        try {
            return Integer.parseInt(dayString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] ");
        }
    }

    private void attendanceCheck() {
        attendanceService.isCorrectDate(DateTimes.now());

        final String nickName = inputView.getNickName();
        attendanceService.checkCrewExist(nickName);

        final String attendanceTime = inputView.getAttendanceTime();

        LocalTime time = parseTime(attendanceTime);
        final LocalDate today = DateTimes.now().toLocalDate();
        final LocalDateTime localDateTime = today.atTime(time);
        //final LocalDateTime dateTime = LocalDateTime.from(time);

        attendanceService.checkOperationTime(localDateTime);

        final AttendanceRegistResponseDto result = attendanceService.registAttendance(nickName,
                localDateTime);

        outputView.printAttendanceRegist(result);
    }

    private LocalTime parseTime(String attendanceTime) {
        try {
            return LocalTime.parse(attendanceTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    private void init() {
        final List<CrewRequestDto> requestDto = csvReader.read("attendances.csv", line -> {
            final String[] parts = line.split(",");
            String name = parts[0];
            final LocalDateTime time = LocalDateTime.parse(
                    parts[1],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            );
            return new CrewRequestDto(name, time);
        });

        Crews crews = Crews.from(requestDto);

        this.attendanceService = new AttendanceService(crews);
    }
}
