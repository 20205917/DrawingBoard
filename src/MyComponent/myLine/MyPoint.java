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
    //投影到一维界面x最大为5000，y最小为-1000
    public int toInt(){
        return px*5000+py+1000;
    }

    //返回以center为中心，2*path为长宽的正方形区域点点
    public static MyPoint[] getPointArea(MyPoint center,int path){
        int width = path*2+1;
        MyPoint[] area = new MyPoint[width*width];
        for(int i =-path;i<=path;i++)
            for (int j=-path;j<=path;j++)
                area[(i+path)*width+j+path] = new MyPoint(center.toInt()+i*5000+j);
        return area;
    }
}