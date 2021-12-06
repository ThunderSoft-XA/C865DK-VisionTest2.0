package com.thundercomm.eBox.VIew;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceHolder;

import androidx.annotation.Nullable;

public class GestureClassificationFragment extends PlayFragment {
    private static final String TAG = "MultiObjectDetectionFragment";
    protected Paint paint_Object;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    void initPaint() {
        super.initPaint();

        paint_Object = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint_Object.setColor(Color.CYAN);
        paint_Object.setShadowLayer(10f, 0, 0, Color.CYAN);
        paint_Object.setStyle(Paint.Style.STROKE);
        paint_Object.setStrokeWidth(4);
        paint_Object.setFilterBitmap(true);
    }

    public GestureClassificationFragment(int id) {
        super(id);
    }

    private void draw(final SurfaceHolder mHolder, String label) {
        Canvas canvas = null;
        if (mHolder != null) {
            try {
                if (paint_Txt == null || paint_Object == null) {
                    initPaint();
                }
                canvas = mHolder.lockCanvas();
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                int x = 400;
                int y = 150;
                int w = 1000;
                int h = 700;
                Rect rect = new Rect(x, y, x + w, y + h);
                drawRound(canvas, x, y, w, h, paint_Object);
                Point show_textPoint = get_show_coordinate(mFaceRectView.getWidth(), mFaceRectView.getHeight(), rect);
                canvas.drawText(label, show_textPoint.x, show_textPoint.y, paint_Txt);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != canvas) {
                    mHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
        hasDrawn = false;
    }


    public void onDraw(String label) {
        draw(mFaceViewHolder, label);
        hasDrawn = true;
    }

}
