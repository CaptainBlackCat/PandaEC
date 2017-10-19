package com.wta.NewCloudApp.jiuwei138940.main.goods.bean;
import java.util.ArrayList;
import java.util.List;

/*
 * 描述:     TODO 商品属性的实体类格式
 */

public class BigClassification {
    //大分类
    private String title;
    public List<SmallClassification> list =new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SmallClassification> getList() {
        return list;
    }

    public void setList(List<SmallClassification> list) {
        this.list = list;
    }

    //小分类
    public static class SmallClassification {
        private String id;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
