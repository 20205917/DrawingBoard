package MyComponent.myGraph;

import MyComponent.MyComponent;
import MyComponent.myLine.MyPoint;
import window.toolbox.ToolBox;

import java.awt.*;

public class JGraphFactory {
    public static JGraph creatJGraph(final ToolBox toolBox, MyGraphType type){
        return switch (type){
            case Rect,Triangle,Oval,Circle,Square, RoundRect,IsoscelesLadder -> new JGraph(type,toolBox);
            case Line -> new JLine(toolBox);
        };
    }

    public static JGraph creatJGraph(String graphicData){
        String[] info = graphicData.split("\n");

        String type = info[1];
        int rgb = Integer.parseInt(info[2].substring(info[2].indexOf(':') + 1));
        float stroke = Float.parseFloat(info[3].substring(info[3].indexOf(':') + 1));
        String temp = info[4].substring(info[4].indexOf(':') + 1);
        int x = Integer.parseInt(temp.substring(0, temp.indexOf(' ')));
        int y = Integer.parseInt(temp.substring(temp.indexOf(' ') + 1));
        temp = info[5].substring(info[5].indexOf(':') + 1);
        int width = Integer.parseInt(temp.substring(0, temp.indexOf(' ')));
        int height = Integer.parseInt(temp.substring(temp.indexOf(' ') + 1));
        boolean isHollow = Boolean.parseBoolean(info[6].substring(info[6].indexOf(':') + 1));
        ToolBox toolBox = new ToolBox();
        toolBox.setDrawLineStroke((int) stroke);
        toolBox.setDrawLineColor(new Color(rgb));
        toolBox.setHollow(isHollow);
        JGraph jGraph = JGraphFactory.creatJGraph(toolBox, MyGraphType.valueOf(type));

        jGraph.setBounds(x, y, width, height);
        if ("Line".equals(type)) {
            String direction = info[7];
            if (direction.equals("JLine-WN"))
                jGraph.resize(new MyPoint(x, y), new MyPoint(x + width, y + height));
            else
                jGraph.resize(new MyPoint(x, y + height), new MyPoint(x + width, y));
        }
        return jGraph;
    }
}
