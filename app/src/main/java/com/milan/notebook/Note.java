package com.milan.notebook;

public class Note {
    String title;
    String dis;
    String category;
    Integer color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    Long id;

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Note(String title, String dis, String category, Integer color, Long id) {
        this.title = title;
        this.dis = dis;
        this.category = category;
        this.color = color;
        this.id = id;
    }

    public Note(String title, String desc, String category, Integer color){
        this.title = title;
        this.category= category;
        this.dis = desc;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
