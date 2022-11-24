package window.area;

import java.util.EventObject;

public class ToolBoxUpdateEvent extends EventObject {
    private static final long serialVersionUID = 1L;
    private Object obj;
    public ToolBoxUpdateEvent(Object source) {
        super(source);
    }
    public Object getObj() {
        return obj;
    }
}