package bridge.domain;

import bridge.service.ResultDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final List<String> bridge;
    private final List<RoundResult> bridgeResult;

    public BridgeGame(List<String> bridge) {
        this.bridge = bridge;
        bridgeResult = new ArrayList<>();
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(String userSelect) {
        if (bridgeResult.size() == bridge.size()) {

        }

        final int cursor = bridgeResult.size() - 1;

        final String currentBlock = bridge.get(cursor);

        if (userSelect == "U") {
            putResult(userSelect, currentBlock, 1);
        }

        if (userSelect == "D") {
            putResult(userSelect, currentBlock, 0);
        }
    }

    private void putResult(String userSelect, String currentBlock, int upDown) {
        if (currentBlock == userSelect) {
            bridgeResult.add(new RoundResult(upDown, "O"));
        }

        if (currentBlock != userSelect) {
            bridgeResult.add(new RoundResult(upDown, "X"));
        }
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry(String userSelect) {
        if (userSelect == "R") {
            bridgeResult.removeLast();
        }
    }

    public List<String> getBridge() {
        return bridge;
    }

    public List<RoundResult> getBridgeResult() {
        return bridgeResult;
    }
}
