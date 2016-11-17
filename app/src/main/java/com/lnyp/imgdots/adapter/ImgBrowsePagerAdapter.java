package com.lnyp.imgdots.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.leiyang.csumap.Dijkstra;
import com.leiyang.csumap.Vertex;
import com.lnyp.imgdots.R;
import com.lnyp.imgdots.bean.ImgSimple;
import com.lnyp.imgdots.bean.PointSimple;
import com.lnyp.imgdots.utils.Utils;
import com.lnyp.imgdots.view.ImageLayout;

import java.util.ArrayList;
import java.util.List;

public class ImgBrowsePagerAdapter extends PagerAdapter {

    List<ImgSimple> imgSimples;

    List<View> views;

    Activity mContext;



    private int width;

    public ImgBrowsePagerAdapter(Activity context, List<ImgSimple> imgSimples) {

        this.mContext = context;
        this.imgSimples = imgSimples;
        Utils.dijkstras = new Dijkstra[getCount()];

        this.views = new ArrayList<>();

        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);

        width = dm.widthPixels;
    }

    @Override
    public int getCount() { // 获得size
        return imgSimples.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_browse, null);
        ImageLayout layoutContent = (ImageLayout) view.findViewById(R.id.layoutContent);

        try {

            int srcID = imgSimples.get(position).srcID;
            float scale = imgSimples.get(position).scale;
            ArrayList<PointSimple> pointSimples = imgSimples.get(position).pointSimples;

            layoutContent.setPoints(pointSimples);

            Dijkstra dijkstra = addPointsSamplesToGraph(pointSimples);
            dijkstra.setEdges(imgSimples.get(position).edges);
            Utils.dijkstras[position] = dijkstra;

            int height = (int) (width * scale);

            layoutContent.setImgBg(width, height, srcID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ((ViewPager) container).addView(view);

        return view;
    }

    //添加一个Graph
    private Dijkstra addPointsSamplesToGraph(ArrayList<PointSimple> pointSimples) {
        Dijkstra dijkstra = new Dijkstra();
        Vertex[] vertex = new Vertex[pointSimples.size()];
        for (int i= 0;i<pointSimples.size();i++){
            /**
             * vertex[i]一定要先实例化
             */
            vertex[i] = new Vertex(i);
            vertex[i].setPointInfo(pointSimples.get(i).getPointInfo());

        }
        dijkstra.setVertex_list(vertex);
        return dijkstra;
    }
}