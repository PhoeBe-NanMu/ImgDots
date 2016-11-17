package com.leiyang.csumap;

import com.lnyp.imgdots.bean.PointInfo;

import java.util.ArrayList;
 
/*顶点类*/
public class Vertex {
    public boolean isVisited;//节点是否被访问
    public ArrayList<Vertex> adjacent=null;//邻接点列表
    public PointInfo pointInfo = new PointInfo();
    int label;

    public PointInfo getPointInfo() {
        return pointInfo;
    }

    public void setPointInfo(PointInfo pointInfo) {
        this.pointInfo = pointInfo;
    }

    public Vertex(){
        isVisited=false;
    }
     
    public Vertex(int l){
        isVisited=false;
        label=l;
    }
     
    public void add_adjacent(Vertex data){//添加邻接点
        if(adjacent==null) adjacent=new ArrayList<Vertex>();
        adjacent.add(data);
         
    }
     
    public void setLabel(int l){//设置标签
        label=l;
    }
     
    public ArrayList<Vertex> getAdj(){
        return adjacent;
    }
     
    public String toString(){
        return String.valueOf(label);
    }
     
 
}