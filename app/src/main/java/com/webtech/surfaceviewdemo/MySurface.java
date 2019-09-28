package com.webtech.surfaceviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurface extends SurfaceView {
    private boolean playing= true;

    public MySurface(Context context) {
        super(context);
        new Anim().start();
    }


    private class Anim extends Thread{
        int count=0;

        @Override
        public void run() {
            long lastupdated=0;
            long delay=100;
            int images[] ={
                    R.drawable.walk_1,
                    R.drawable.walk_2,
                    R.drawable.walk_3,
                    R.drawable.walk_4,
                    R.drawable.walk_5,
                    R.drawable.walk_6,
                    R.drawable.walk_7,
                    R.drawable.walk_8,
                    R.drawable.walk_9,
                    R.drawable.walk_10
            };

            while (true){
                if(playing){
                    long current_time=System.currentTimeMillis();
                    if(current_time>lastupdated+delay){
                        if(count>=10){
                            count=0;
                        }
                        draw(images[count]);
                        lastupdated=current_time;
                        count++;
                    }
                }
            }
        }

        private void draw(int images){
            SurfaceHolder holder = getHolder();
            Canvas canvas = holder.lockCanvas();
            if(canvas!=null){
                canvas.drawColor(Color.WHITE);
                Paint paint = new Paint();
                Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(),images);
                canvas.drawBitmap(bitmap,100,100,paint);
                holder.unlockCanvasAndPost(canvas);

            }
        }

    }
}
