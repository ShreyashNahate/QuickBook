package com.example.quickbook;


public class DataClass2 {

    private String dataTitle;

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    private String dataTime;

    private String dataImage;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass2(String dataTitle,   String dataImage,String dataTime) {
        this.dataTitle = dataTitle;
        this.dataImage = dataImage;
        this.dataTime = dataTime;
    }
    public DataClass2(){

    }
}