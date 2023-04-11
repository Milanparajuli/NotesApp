package com.milan.notebook;

public class Note {
    String title;
    String dis;
    String category;
    public Note(String title,String desc,String category){
        this.title = title;
        this.category= category;
        this.dis = desc;
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
