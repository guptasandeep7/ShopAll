package com.example.shopall;

public class Recycler {
    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private Integer image;
    private String text;

    public Recycler(Integer image, String text) {
        this.image = image;
        this.text = text;
    }

}
