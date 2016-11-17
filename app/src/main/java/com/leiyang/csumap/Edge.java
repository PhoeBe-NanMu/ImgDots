package com.leiyang.csumap;

/**
 * Created by leiyang on 2016/11/16.
 */

public class Edge {
    private static final int INF = Integer.MAX_VALUE;   // 最大值
    private int fromVertex;
    private int toVertex;
    private int weight = INF ;

    public Edge(int fromVertex, int toVertex, int weight) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.weight = weight;
    }

    public int getFromVertex() {
        return fromVertex;
    }

    public void setFromVertex(int fromVertex) {
        this.fromVertex = fromVertex;
    }

    public int getToVertex() {
        return toVertex;
    }

    public void setToVertex(int toVertex) {
        this.toVertex = toVertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
