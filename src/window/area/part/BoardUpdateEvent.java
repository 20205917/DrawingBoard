package window.area.part;

import java.util.EventObject;

public class BoardUpdateEvent extends EventObject {
    private static final long serialVersionUID = 1L;
    private Object obj;
    public BoardUpdateEvent(Object source) {
        super(source);
    }
    public Object getObj() {
        return obj;
    }
}
