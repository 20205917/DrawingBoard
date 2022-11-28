package MyComponent.myGraph;

import window.toolbox.ToolBox;

public class JGraphFactory {
    public static JGraph creatJGraph(final ToolBox toolBox, MyGraphType type){
        return switch (type){
            case Rect,Triangle,Oval,Circle,Square, RoundRect,IsoscelesLadder -> new JGraph(type,toolBox);
            case Line -> new JLine(toolBox);
        };
    }
}
