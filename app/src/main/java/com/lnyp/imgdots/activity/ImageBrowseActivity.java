package com.lnyp.imgdots.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.leiyang.csumap.Edge;
import com.lnyp.imgdots.R;
import com.lnyp.imgdots.adapter.ImgBrowsePagerAdapter;
import com.lnyp.imgdots.bean.ImgSimple;
import com.lnyp.imgdots.bean.PointInfo;
import com.lnyp.imgdots.bean.PointSimple;
import com.lnyp.imgdots.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class ImageBrowseActivity extends AppCompatActivity {

    private ViewPager viewPagerImgs;

    private List<ImgSimple> imgSimples;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_browse);


        viewPagerImgs = (ViewPager) this.findViewById(R.id.viewPagerImgs);
        viewPagerImgs.setOffscreenPageLimit(4);

        initData();

        PagerAdapter adapter = new ImgBrowsePagerAdapter(this, imgSimples);
        viewPagerImgs.setAdapter(adapter);


    }


    private void initData() {

        imgSimples = new ArrayList<>();


        ImgSimple imgSimple = new ImgSimple();
//        imgSimple4.url = "http://o79w6dswy.bkt.clouddn.com/421428.jpg";
        imgSimple.srcID = R.drawable.xiaobenbu;
        imgSimple.scale = 0.75f;

        ArrayList<PointSimple> pointSimples = new ArrayList<>();

        PointInfo pointInfo0 = new PointInfo();
        pointInfo0.setPointName("升华公寓");
        pointInfo0.setPointNum("201603");
        pointInfo0.setPointSimpleInfo("中南大学最大的学生宿舍区");

        PointSimple pointSimple = new PointSimple();
        pointSimple.width_scale = 0.1f;
        pointSimple.height_scale = 0.3f;
        pointSimple.label = 0;
        pointSimple.pointInfo = pointInfo0;


        PointInfo pointInfo1 = new PointInfo();
        PointSimple pointSimple1 = new PointSimple();
        pointSimple1.width_scale = 0.3f;
        pointSimple1.height_scale = 0.5f;
        pointInfo1.setPointName("新校区");
        pointInfo1.setPointNum("201602");
        pointInfo1.setPointSimpleInfo("中南大学教学区");
        pointSimple1.pointInfo = pointInfo1;
        pointSimple1.label = 1;


        PointInfo pointInfo2 = new PointInfo();
        PointSimple pointSimple2 = new PointSimple();
        pointSimple2.width_scale = 0.5f;
        pointSimple2.height_scale = 0.8f;
        pointInfo2.setPointName("二食堂");
        pointInfo2.setPointNum("201601");
        pointInfo2.setPointSimpleInfo("中南大学南校区二食堂，中南大学最新的食堂");
        pointSimple2.pointInfo = pointInfo2;
        pointSimple2.label = 2;



        PointInfo pointInfo3 = new PointInfo();
        pointInfo3.setPointName("升华公寓");
        pointInfo3.setPointNum("201603");
        pointInfo3.setPointSimpleInfo("中南大学最大的学生宿舍区");

        PointSimple pointSimple3 = new PointSimple();
        pointSimple3.width_scale = 0.36f;
        pointSimple3.height_scale = 0.75f;
        pointSimple3.pointInfo = pointInfo3;
        pointSimple3.label = 3;



        PointInfo pointInfo4 = new PointInfo();
        pointInfo4.setPointName("升华公寓");
        pointInfo4.setPointNum("201603");
        pointInfo4.setPointSimpleInfo("中南大学最大的学生宿舍区");

        PointSimple pointSimple4 = new PointSimple();
        pointSimple4.width_scale = 0.64f;
        pointSimple4.height_scale = 0.5f;
        pointSimple4.pointInfo = pointInfo4;
        pointSimple4.label = 4;


        PointInfo pointInfo5 = new PointInfo();
        pointInfo5.setPointName("升华公寓");
        pointInfo5.setPointNum("201603");
        pointInfo5.setPointSimpleInfo("中南大学最大的学生宿舍区");

        PointSimple pointSimple5 = new PointSimple();
        pointSimple5.width_scale = 0.276f;
        pointSimple5.height_scale = 0.764f;
        pointSimple5.pointInfo = pointInfo5;
        pointSimple5.label = 5;

        pointSimples.add(pointSimple);
        pointSimples.add(pointSimple1);
        pointSimples.add(pointSimple2);
        pointSimples.add(pointSimple3);
        pointSimples.add(pointSimple4);
        pointSimples.add(pointSimple5);



        imgSimple.pointSimples = pointSimples;


        Edge edge = new Edge(0,1,6);
        Edge edge1 = new Edge(0,5,12);
        Edge edge2 = new Edge(0,4,10);
        Edge edge3 = new Edge(1,5,8);
        Edge edge9 = new Edge(1,3,5);
        Edge edge4 = new Edge(1,2,3);
        Edge edge5 = new Edge(2,3,7);
        Edge edge6 = new Edge(3,4,9);
        Edge edge7 = new Edge(3,5,11);
        Edge edge8 = new Edge(4,5,16);
        imgSimple.edges.add(edge);
        imgSimple.edges.add(edge1);
        imgSimple.edges.add(edge2);
        imgSimple.edges.add(edge3);
        imgSimple.edges.add(edge4);
        imgSimple.edges.add(edge5);
        imgSimple.edges.add(edge6);
        imgSimple.edges.add(edge7);
        imgSimple.edges.add(edge8);
        imgSimple.edges.add(edge9);


        //第二张

        ImgSimple imgSimple1 = new ImgSimple();
//        imgSimple4.url = "http://o79w6dswy.bkt.clouddn.com/421428.jpg";
        imgSimple1.srcID = R.drawable.nanxiaoqu;
        imgSimple1.scale = 0.75f;

        ArrayList<PointSimple> pointSimples01 = new ArrayList<>();

        PointInfo pointInfo01 = new PointInfo();
        pointInfo01.setPointName("升华公寓");
        pointInfo01.setPointNum("201600");
        pointInfo01.setPointSimpleInfo("中南大学最大的学生宿舍区");

        PointSimple pointSimple01 = new PointSimple();
        pointSimple01.width_scale = 0.2f;
        pointSimple01.height_scale = 0.8f;
        pointSimple01.label = 0;
        pointSimple01.pointInfo = pointInfo01;


        PointInfo pointInfo11 = new PointInfo();
        PointSimple pointSimple11 = new PointSimple();
        pointSimple11.width_scale = 0.4f;
        pointSimple11.height_scale = 0.8f;
        pointInfo11.setPointName("二食堂");
        pointInfo11.setPointNum("201601");
        pointInfo11.setPointSimpleInfo("中南大学最大食堂，中南大学最新的食堂");
        pointSimple11.pointInfo = pointInfo11;
        pointSimple11.label = 1;


        PointInfo pointInfo21 = new PointInfo();
        PointSimple pointSimple21 = new PointSimple();
        pointSimple21.width_scale = 0.65f;
        pointSimple21.height_scale = 0.5f;
        pointInfo21.setPointName("体育场");
        pointInfo21.setPointNum("201602");
        pointInfo21.setPointSimpleInfo("体育场晚上人多");
        pointSimple21.pointInfo = pointInfo21;
        pointSimple21.label = 2;



        PointInfo pointInfo31 = new PointInfo();
        pointInfo31.setPointName("南校荷花池");
        pointInfo31.setPointNum("201603");
        pointInfo31.setPointSimpleInfo("夏天最美花池");

        PointSimple pointSimple31 = new PointSimple();
        pointSimple31.width_scale = 0.48f;
        pointSimple31.height_scale = 0.48f;
        pointSimple31.pointInfo = pointInfo31;
        pointSimple31.label = 3;



        PointInfo pointInfo41 = new PointInfo();
        pointInfo41.setPointName("南校礼堂");
        pointInfo41.setPointNum("201604");
        pointInfo41.setPointSimpleInfo("内部装修华丽的礼堂");

        PointSimple pointSimple41 = new PointSimple();
        pointSimple41.width_scale = 0.3f;
        pointSimple41.height_scale = 0.35f;
        pointSimple41.pointInfo = pointInfo41;
        pointSimple41.label = 4;



        PointInfo pointInfo51 = new PointInfo();
        pointInfo51.setPointName("三教");
        pointInfo51.setPointNum("201605");
        pointInfo51.setPointSimpleInfo("南校自习圣地");

        PointSimple pointSimple51 = new PointSimple();
        pointSimple51.width_scale = 0.44f;
        pointSimple51.height_scale = 0.65f;
        pointSimple51.pointInfo = pointInfo51;
        pointSimple51.label = 5;

        pointSimples01.add(pointSimple01);
        pointSimples01.add(pointSimple11);
        pointSimples01.add(pointSimple21);
        pointSimples01.add(pointSimple31);
        pointSimples01.add(pointSimple41);
        pointSimples01.add(pointSimple51);



        imgSimple1.pointSimples = pointSimples01;


        Edge edge01 = new Edge(0,1,6);
        Edge edge11 = new Edge(0,5,12);
        Edge edge21 = new Edge(0,4,10);
        Edge edge31 = new Edge(1,5,8);
        Edge edge91 = new Edge(1,3,5);
        Edge edge41 = new Edge(1,2,3);
        Edge edge51 = new Edge(2,3,7);
        Edge edge61 = new Edge(3,4,9);
        Edge edge71 = new Edge(3,5,11);
        Edge edge81 = new Edge(4,5,16);
        imgSimple1.edges.add(edge01);
        imgSimple1.edges.add(edge11);
        imgSimple1.edges.add(edge21);
        imgSimple1.edges.add(edge31);
        imgSimple1.edges.add(edge41);
        imgSimple1.edges.add(edge51);
        imgSimple1.edges.add(edge61);
        imgSimple1.edges.add(edge71);
        imgSimple1.edges.add(edge81);
        imgSimple1.edges.add(edge91);

        imgSimples.add(imgSimple1);

        imgSimples.add(imgSimple);



    }
}
