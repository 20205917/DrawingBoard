package window.part;

import java.util.EventListener;

public interface BoardUpdateListenerInterface extends EventListener {
    void handleEvent(BoardUpdateEvent serverUpdateEvent);
}
