package com.lnyp.imgdots.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lnyp.imgdots.R;
import com.lnyp.imgdots.activity.DetailsActivity;
import com.lnyp.imgdots.activity.PoupWindowDemoActivity;
import com.lnyp.imgdots.activity.ShowPathActivity;
import com.lnyp.imgdots.adapter.GroupAdapter;
import com.lnyp.imgdots.bean.PointInfo;
import com.lnyp.imgdots.bean.PointSimple;
import com.lnyp.imgdots.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ImageLayout extends FrameLayout implements View.OnClickListener {

    ArrayList<PointSimple> points;

    FrameLayout layouPoints;




    private PopupWindow popupWindow;

    private View view;

    private ListView lv_group;

    private List<String> groups;

    //景点名称
    private String mPlace;
    private String mStartPlace;
    private String mEndPlace;
//    private String mStartPlace;


    ImageView imgBg;

    Context mContext;

    public ImageLayout(Context context) {
        this(context, null);
    }

    public ImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);

    }


    private void initView(Context context, AttributeSet attrs) {

        mContext = context;

        View imgPointLayout = inflate(context, R.layout.layout_imgview_point, this);

        imgBg = (ImageView) imgPointLayout.findViewById(R.id.imgBg);
        layouPoints = (FrameLayout) imgPointLayout.findViewById(R.id.layouPoints);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setImgBg(int width, int height, int srcID) {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),srcID);
        imgBg.setImageBitmap(bitmap);

        ViewGroup.LayoutParams lp = imgBg.getLayoutParams();
        lp.width = width;
        lp.height = height;

        imgBg.setLayoutParams(lp);

        ViewGroup.LayoutParams lp1 = layouPoints.getLayoutParams();
        lp1.width = width;
        lp1.height = height;

        layouPoints.setLayoutParams(lp1);

//        Glide.with(mContext).load(imgUrl).asBitmap().into(imgBg);

        addPoints(width, height);

    }

    public void setPoints(ArrayList<PointSimple> points) {

        this.points = points;
    }

    private void addPoints(int width, int height) {

        layouPoints.removeAllViews();
        PointInfo pointInformation;

        for (int i = 0; i < points.size(); i++) {

            double width_scale = points.get(i).width_scale;
            double height_scale = points.get(i).height_scale;
            pointInformation = points.get(i).pointInfo;


            LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_point, this, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.imgPoint);
            imageView.setTag(pointInformation);

            AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();

            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

            layoutParams.leftMargin = (int) (width * width_scale);
            layoutParams.topMargin = (int) (height * height_scale);


            imageView.setOnClickListener(this);

            layouPoints.addView(view, layoutParams);
        }
    }



    PointInfo pointInfoTemp;

    @Override
    public void onClick(View view) {
        pointInfoTemp = (PointInfo) view.getTag();
//        Toast.makeText(getContext(), "pos : " + pos.getPointName(), Toast.LENGTH_SHORT).show();
        showWindow(view);

    }


    private void showWindow(View parent) {

        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = layoutInflater.inflate(R.layout.group_list, null);

            lv_group = (ListView) view.findViewById(R.id.lvGroup);
            // 加载数据
            groups = new ArrayList<String>();
            groups.add("景点名称");
            groups.add("详细信息");
            groups.add("设为起点");
            groups.add("设为终点");

            GroupAdapter groupAdapter = new GroupAdapter(mContext, groups);
            lv_group.setAdapter(groupAdapter);
            // 创建一个PopuWidow对象
            popupWindow = new PopupWindow(view,300, LayoutParams.WRAP_CONTENT);
        }

        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
        int xPos = windowManager.getDefaultDisplay().getWidth() / 2
                - popupWindow.getWidth() / 2;
        Log.i("coder", "xPos:" + xPos);

        popupWindow.showAsDropDown(parent);
//        popupWindow.showAsDropDown(parent, xPos, 0);

        lv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @SuppressLint("ShowToast")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,int position, long id) {
                String itemStr = groups.get(position);
                if (popupWindow != null) {
                    popupWindow.dismiss();
                    parseItemStr(itemStr);

                }
            }
        });
    }




    private void parseItemStr(String itemStr) {


        if (itemStr == null){
            Toast.makeText(getContext(), "Item为空", Toast.LENGTH_SHORT).show();
        } else if (itemStr.equals("景点名称")){
            Toast.makeText(getContext(), pointInfoTemp.getPointName(), Toast.LENGTH_SHORT).show();

        }else if (itemStr.equals("详细信息")){
            //TODO 跳转至详细信息Activity
            Intent intent = new Intent(getContext(),DetailsActivity.class);
            intent.putExtra("pointInfo",pointInfoTemp);
//            intent.putExtra("ming",pointInfoTemp.getPointName());
//            intent.putExtra("info",pointInfoTemp.getPointSimpleInfo());
//            intent.putExtra("num",pointInfoTemp.getPointNum());

            getContext().startActivity(intent);
        } else if (itemStr.equals("设为起点")){
            mStartPlace = pointInfoTemp.getPointName();
            Toast.makeText(getContext(), "已将该景点设为起点", Toast.LENGTH_SHORT).show();

        }  else if (itemStr.equals("设为终点")) {
            //TODO Dialog提示是否进行相关操作
            if (mStartPlace == null){
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

                dialogBuilder.setTitle("提示");
                dialogBuilder.setMessage("请先设置起点");
                dialogBuilder.create().show();

            } else{
                mEndPlace = pointInfoTemp.getPointName();
                Intent intent = new Intent(getContext(),ShowPathActivity.class);
                intent.putExtra("mStartPlace",mStartPlace);
                intent.putExtra("mEndPlace",mEndPlace);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);

            }
        }
    }


}
