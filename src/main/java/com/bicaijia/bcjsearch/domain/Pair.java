package com.bicaijia.bcjsearch.domain;

/**
 * 成对的数据结构
 * 
 * @author JiangTeng
 * 
 */
public class Pair<K,V> {

    private K x;// x

    private V y; // y
    
    public Pair (){     
    }
    
    public Pair (K x, V y){
        this.x =x;
        this.y=y;
    }
    
    public K getX() {
        return x;
    }

    public void setX(K x) {
        this.x = x;
    }

    public V getY() {
        return y;
    }

    public void setY(V y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }

     

}
