package window.area.part;

import window.area.ToolBoxUpdateEvent;

import java.util.EventListener;

public interface BoardUpdateListenerInterface extends EventListener {
    void handleEvent(BoardUpdateEvent serverUpdateEvent);
}
