package com.lnyp.imgdots.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lnyp.imgdots.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SpacePageActivity extends Activity {

    private LinearLayout linnerLayout_spacepage;
    private RelativeLayout relativeLayout_spacepage;
    private Button btn_spacepage_back;
    private Button btn_spacepage_save;
    private static Boolean isTrue = false;

    private static String IMAGE_MIME_TYPE;

    private ImageView image_spacePage;
    private ProgressDialog dialog;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub 
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        setContentView(R.layout.spacepage);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        initHead();

        if (isNetworkAvailable()) {
            image_spacePage = (ImageView) findViewById(R.id.image_spacepage);
            btn_spacepage_save = (Button) findViewById(R.id.btn_spacepage_save);
            image_spacePage.setOnTouchListener(new MulctPointTouchListener(image_spacePage));
            image_spacePage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

//加载成功 
//            String urls = "http://epaper.yzdsb.com.cn/201201/04/yz2104_7.jpg";
//            bitmap = returnBitMap(urls);
//            if (null != bitmap) {
//                image_spacePage.setImageBitmap(bitmap);
//            }

        }
    }


    public Bitmap returnBitMap(String url) {

        if (null == url || "".equals(url)) {
            return null;
        }
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setConnectTimeout(2000);
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return bitmap;
    }

    public class MulctPointTouchListener implements View.OnTouchListener {

        Matrix matrix = new Matrix();
        Matrix savedMatrix = new Matrix();

        public ImageView image;
        static final int NONE = 0;
        static final int DRAG = 1;
        static final int ZOOM = 2;
        int mode = NONE;

        PointF start = new PointF();
        PointF mid = new PointF();
        float oldDist = 1f;


        public MulctPointTouchListener(ImageView image) {
            super();
            this.image = image;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            this.image.setScaleType(ImageView.ScaleType.MATRIX);

            ImageView view = (ImageView) v;
// dumpEvent(event); 

            switch (event.getAction() & MotionEvent.ACTION_MASK) {

                case MotionEvent.ACTION_DOWN:

                    Log.w("FLAG", "ACTION_DOWN");
                    matrix.set(view.getImageMatrix());
                    savedMatrix.set(matrix);
                    start.set(event.getX(), event.getY());
                    mode = DRAG;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    Log.w("FLAG", "ACTION_POINTER_DOWN");
                    oldDist = spacing(event);
                    if (oldDist > 10f) {
                        savedMatrix.set(matrix);
                        midPoint(mid, event);
                        mode = ZOOM;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    Log.w("FLAG", "ACTION_UP");
                case MotionEvent.ACTION_POINTER_UP:
                    Log.w("FLAG", "ACTION_POINTER_UP");
                    mode = NONE;
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.w("FLAG", "ACTION_MOVE");
                    if (mode == DRAG) {
                        matrix.set(savedMatrix);
                        matrix.postTranslate(event.getX() - start.x, event.getY()
                                - start.y);
                    } else if (mode == ZOOM) {
                        float newDist = spacing(event);
                        if (newDist > 10f) {
                            matrix.set(savedMatrix);
                            float scale = newDist / oldDist;
                            matrix.postScale(scale, scale, mid.x, mid.y);
                        }
                    }
                    break;
            }

            view.setImageMatrix(matrix);
            return true;
        }


        private float spacing(MotionEvent event) {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return FloatMath.sqrt(x * x + y * y);
        }

        private void midPoint(PointF point, MotionEvent event) {
            float x = event.getX(0) + event.getX(1);
            float y = event.getY(0) + event.getY(1);
            point.set(x / 2, y / 2);
        }
    }

    private void initHead() {
//        linnerLayout_spacepage = (LinearLayout) findViewById(R.id.linnerLayout_spacepage);
        relativeLayout_spacepage = (RelativeLayout) findViewById(R.id.relativeLayout_spacepage);
        btn_spacepage_back = (Button) findViewById(R.id.btn_spacepage_back);
        btn_spacepage_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub 

                finish();
            }
        });
        btn_spacepage_save = (Button) findViewById(R.id.btn_spacepage_save);

    }

    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Log.i("NetWorkState", "Unavailable ");
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        Log.i("NetWorkState", "Available");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void onStop() {
// TODO Auto-generated method stub 
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                bitmap.recycle(); //回收图片所占的内存
                bitmap = null;
                System.gc(); //提醒系统及时回收
            }
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
// TODO Auto-generated method stub 
        super.onDestroy();
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                bitmap.recycle(); //回收图片所占的内存
                bitmap = null;
                System.gc(); //提醒系统及时回收
            }
        }
    }
}