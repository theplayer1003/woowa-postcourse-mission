package bridge.service;

import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;
import bridge.domain.BridgeNumberGenerator;
import bridge.domain.BridgeRandomNumberGenerator;
import bridge.domain.RoundResult;
import java.util.ArrayList;
import java.util.List;

public class BridgeGameService {

    public BridgeGame getBridgeGame(int length) {
        final BridgeNumberGenerator bridgeRandomNumberGenerator = new BridgeRandomNumberGenerator();

        final BridgeMaker bridgeMaker = new BridgeMaker(bridgeRandomNumberGenerator);

        final List<String> bridge = bridgeMaker.makeBridge(length);

        final BridgeGame bridgeGame = new BridgeGame(bridge);

        return bridgeGame;
    }

    public List<ResultDto> playOneStep(BridgeGame bridgeGame, String userSelect) {
        List<ResultDto> resultDtos = new ArrayList<>();

        bridgeGame.move(userSelect);

        final List<RoundResult> bridgeResult = bridgeGame.getBridgeResult();

        final ResultDto resultDto = ResultDto.from(bridgeResult);

        resultDtos.add(resultDto);

        return resultDtos;
    }
}
