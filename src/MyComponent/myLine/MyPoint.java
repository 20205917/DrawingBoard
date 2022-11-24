package MyComponent.myLine;

public class MyPoint {
    public int px;
    public int py;
    public MyPoint(int x, int y){
        px = x;
        py = y;
    }
    //投影到一维界面正负最大为5000
    public int toInt(){
        return px*10000+py;
    }
}