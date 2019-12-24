package com.roger.recyclerviewdemo.baseadapter;

import java.util.ArrayList;
import java.util.List;

public class DataBean {

    public DataBean() {

    }

    public DataBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    /**
     * Creating 10 dummy content for list.
     *
     * @param itemCount
     * @return
     */
    public static List<DataBean> createDatas(int itemCount) {
        List<DataBean> movies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DataBean movie = new DataBean("黑暗骑士" , (itemCount == 0 ?
                    (itemCount + 1 + i) : (itemCount + i)));
            movies.add(movie);
        }
        return movies;
    }
}
