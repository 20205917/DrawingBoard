package window.toolbox;

import java.util.EventListener;


public interface ToolBoxUpdateListenerInterface extends EventListener {
    void handleEvent(ToolBoxUpdateEvent serverUpdateEvent);
}


