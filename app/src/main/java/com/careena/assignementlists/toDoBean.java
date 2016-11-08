package com.careena.assignementlists;

import java.io.Serializable;
/**
 * Created by Careena on 9/30/16.
 */
public class toDoBean implements Serializable{


    private String title;
    private String desc;
    private int value = 0; //0 unchecked 1 checked


    public toDoBean() {

    }


    public toDoBean(String title, String desc, int value) {
        this.title = title;
        this.desc = desc;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public  String getTitle() {

        return title;
    }

    public  void setTitle(String title) {

        this.title = title;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
