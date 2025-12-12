package subway.config;

import subway.service.LineService;
import subway.service.StationService;
import subway.service.dto.request.AddStationInLineDto;
import subway.service.dto.request.CreateLineDto;

public class DataInitializer {
    private final StationService stationService;
    private final LineService lineService;

    public DataInitializer(StationService stationService, LineService lineService) {
        this.stationService = stationService;
        this.lineService = lineService;
    }

    public void init() {
        initStations();
        initLines();
    }

    private void initStations() {
        stationService.createStationByName("교대역");
        stationService.createStationByName("강남역");
        stationService.createStationByName("역삼역");
        stationService.createStationByName("남부터미널역");
        stationService.createStationByName("양재역");
        stationService.createStationByName("양재시민의숲역");
        stationService.createStationByName("매봉역");
    }

    private void initLines() {
        lineService.createLine(new CreateLineDto("2호선", "역삼역", "교대역"));
        lineService.createLine(new CreateLineDto("3호선", "매봉역", "교대역"));
        lineService.createLine(new CreateLineDto("신분당선", "양재시민의숲역", "강남역"));

        lineService.addStationInLine(new AddStationInLineDto("2호선", "강남역", 2));
        lineService.addStationInLine(new AddStationInLineDto("3호선", "남부터미널역", 2));
        lineService.addStationInLine(new AddStationInLineDto("3호선", "양재역", 3));
        lineService.addStationInLine(new AddStationInLineDto("신분당선", "양재역", 2));
    }
}
