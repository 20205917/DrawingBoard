package window.area;

import java.util.EventListener;


public interface ToolBoxUpdateListenerInterface extends EventListener {
    void handleEvent(ToolBoxUpdateEvent serverUpdateEvent);
}


