package MyComponent.myLine;

public class MyPoint {
    public int px;
    public int py;
    public MyPoint(int x, int y){
        px = x;
        py = y;
    }
    public MyPoint(int x){
        px = x/5000;
        py = x%5000-1000;
        if(py<0)
            py =0;
    }
    //投影到一维界面最大为5000
    public int toInt(){
        return px*5000+py+1000;
    }
}