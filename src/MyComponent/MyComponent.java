package MyComponent;

import MyComponent.myLine.MyPoint;

import java.awt.*;

public interface MyComponent {
    //边框判断间隔范围为，用于位移拉伸
    int gap = 5;
    void resize(MyPoint A, MyPoint B);
    default Cursor currentCursorStyle(int W,int H,int X,int Y){

        //靠左
        boolean OnLeft = X < W-X;
        //靠上
        boolean OnTop = Y < H-Y;

        int wgap = Math.min(X,W-X);
        int hgap = Math.min(Y,H-Y);
        if(wgap < gap && hgap <gap){
            if(OnLeft && OnTop ) return new Cursor(Cursor.NW_RESIZE_CURSOR);
            else if(OnLeft)return new Cursor(Cursor.SW_RESIZE_CURSOR);
            else if(OnTop)return new Cursor(Cursor.NE_RESIZE_CURSOR);
            else return new Cursor(Cursor.SE_RESIZE_CURSOR);
        }
        else if(wgap < gap && hgap >gap){
            if(OnLeft)
                return new Cursor(Cursor.W_RESIZE_CURSOR);
            else
                return new Cursor((Cursor.E_RESIZE_CURSOR));
        }
        else if(wgap > gap && hgap <gap) {
            if (OnTop)
                return new Cursor(Cursor.N_RESIZE_CURSOR);
            else
                return new Cursor((Cursor.S_RESIZE_CURSOR));
        }
        else
            return new Cursor(Cursor.MOVE_CURSOR);
    }
}
