package com.leiyang.csumap;

import android.util.Log;

import com.lnyp.imgdots.utils.Utils;

import java.util.ArrayList;

public class Dijkstra {

    private int mEdgNum;        // 边的数量
    private int mVertexNum;
    private String[] mVexs;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵
    private int[][] p;
    private static final int INF = Integer.MAX_VALUE;   // 最大值

    Vertex[] vertex_list ;

    public ArrayList<Edge> edges = new ArrayList<>();

    /*
     * 创建图(用已提供的矩阵)
     *
     * 参数说明：
     *     vexs  -- 顶点数组
     *     matrix-- 矩阵(数据)
     */

    public Vertex[] getVertex_list() {
        return vertex_list;
    }

    public void setVertex_list(Vertex[] vertex_list) {
        this.vertex_list = vertex_list;
        mVertexNum = vertex_list.length;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public Dijkstra(){
    }
    public Dijkstra(String[] vexs, int[][] matrix) {
        
        // 初始化"顶点数"和"边数"
        int vlen = vexs.length;


        // 初始化"顶点"
        mVexs = new String[vlen];
        for (int i = 0; i < mVexs.length; i++)
            mVexs[i] = vexs[i];
        p = new int[vlen][vlen];
        // 初始化"边"
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++)
            for (int j = 0; j < vlen; j++)
                mMatrix[i][j] = matrix[i][j];

        // 统计"边"
        mEdgNum = 0;
        for (int i = 0; i < vlen; i++)
            for (int j = i+1; j < vlen; j++)
                if (mMatrix[i][j]!=INF)
                    mEdgNum++;
    }

    /*
     * Dijkstra最短路径。
     * 即，统计图中"顶点vs"到其它各个顶点的最短路径。
     *
     * 参数说明：
     *       vs -- 起始顶点(start vertex)。即计算"顶点vs"到其它顶点的最短路径。
     *     prev -- 前驱顶点数组。即，prev[i]的值是"顶点vs"到"顶点i"的最短路径所经历的全部顶点中，位于"顶点i"之前的那个顶点。
     *     dist -- 长度数组。即，dist[i]是"顶点vs"到"顶点i"的最短路径的长度。
     */
    public String dijkstra(int vs, int[] prev, int[] dist) {
        // flag[i]=true表示"顶点vs"到"顶点i"的最短路径已成功获取
        boolean[] flag = new boolean[mVexs.length];

        // 初始化
        for (int i = 0; i < mVexs.length; i++) {
            flag[i] = false;          // 顶点i的最短路径还没获取到。
            prev[i] = 0;              // 顶点i的前驱顶点为0。
            dist[i] = mMatrix[vs][i];  // 顶点i的最短路径为"顶点vs"到"顶点i"的权。
            for(int m=0; m<mVexs.length; m++)
                p[i][m]=-1;//设p[][]初值为-1，即没有路径
            if(dist[i]<INF)//v0到v有直接路径
            {
                p[i][0]=vs;//v0到v最短路径经过的第一个顶点
                p[i][1]=i;//v0到v最短路径经过的第二个顶点
            }
        }

        // 对"顶点vs"自身进行初始化
        flag[vs] = true;
        dist[vs] = 0;

        // 遍历mVexs.length-1次；每次找出一个顶点的最短路径。

        int k=0;
        for (int i = 1; i < mVexs.length; i++) {
            // 寻找当前最小的路径；
            // 即，在未获取最短路径的顶点中，找到离vs最近的顶点(k)。
            int min = INF;
            for (int j = 0; j < mVexs.length; j++) {
                if (flag[j]==false && dist[j]<min) {
                    min = dist[j];
                    k = j;

                }
            }
            // 标记"顶点k"为已经获取到最短路径
            flag[k] = true;
            // 修正当前最短路径和前驱顶点
            // 即，当已经"顶点k的最短路径"之后，更新"未获取最短路径的顶点的最短路径和前驱顶点"。
            for (int j = 0; j < mVexs.length; j++) {
                int tmp = (mMatrix[k][j]==INF ? INF : (min + mMatrix[k][j]));
                if (flag[j]==false && (tmp<dist[j]) ) {
                    dist[j] = tmp;
                    prev[j] = k;
                    for(int m=0; m <mVexs.length; m++)//修改p[w]，v0到w经过的顶点包括v0到v经过的所有顶点再加上顶点w
                    {
                        p[j][m]=p[k][m];
                        if(p[j][m]==-1)//在p[w][]第一个等于-1的地方加上顶点w
                        {
                            p[j][m]=j;
                            break;
                        }
                    }
                }
            }

        }


        StringBuilder stringBuilder = new StringBuilder();
        // 打印dijkstra最短路径的结果
        System.out.printf("dijkstra(%s): \n", mVexs[vs]);
        for (int i=0; i < mVexs.length; i++)
        {
            String s1 = mVexs[vs]+"-->"+mVexs[i]+" 的最短路径为："+dist[i] + "\n路径为：";
            System.out.print(s1);
            stringBuilder.append(s1);
            for(int j=0; j<mVexs.length; j++)
            {
                if(p[i][j]>-1){
                    String s2 = mVexs[p[i][j]] + "--";
                    System.out.print(s2);
                    stringBuilder.append(s2);
                }

            }
            System.out.println();
            stringBuilder.append("\n\n");
        }

        // 打印dijkstra最短路径的结果
        String result = stringBuilder.toString();
        return result;
    }

    // 边的结构体
    private static class EData {
        char start; // 边的起点
        char end;   // 边的终点
        int weight; // 边的权重

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    };


    public String start() {
        String[] vexs = new String[vertex_list.length];
        for (int i = 0; i < vertex_list.length; i++){
            vexs[i] = vertex_list[i].getPointInfo().getPointName();
        }



        int[][] matrix = new int[mVertexNum][mVertexNum];
        for (int i = 0; i< mVertexNum; i++){
            for (int j = 0; j< mVertexNum; j++){
                matrix[i][j] = INF;
            }
        }
        for (Edge edge:edges
             ) {
            matrix[edge.getFromVertex()][edge.getToVertex()] = edge.getWeight();
            matrix[edge.getToVertex()][edge.getFromVertex()] = edge.getWeight();
        }

        Dijkstra pG;

        // 自定义"图"(输入矩阵队列)
        //pG = new Dijkstra();
        // 采用已有的"图"
        pG = new Dijkstra(vexs, matrix);

        //pG.print();   // 打印图
        //pG.DFS();     // 深度优先遍历
        //pG.BFS();     // 广度优先遍历
        //pG.prim(0);   // prim算法生成最小生成树
        //pG.kruskal(); // Kruskal算法生成最小生成树

        int[] prev = new int[pG.mVexs.length];
        int[] dist = new int[pG.mVexs.length];
        // dijkstra算法获取"第4个顶点"到其它各个顶点的最短距离
        return pG.dijkstra(3, prev, dist);

    }
}
