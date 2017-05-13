package com.zeyd.sozluk;

/**
 * Created by Zeyd on 23.5.2016.
 */
public class Kelime {
    private int id;
    private String tr;
    private String ing;

    public  Kelime(int id ,String tr, String ing)
    {
        this.id =id;
        this.tr=tr;
        this.ing =ing;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTr() {
        return tr;
    }

    public void setTr(String tr) {
        this.tr = tr;
    }

    public String getIng() {
        return ing;
    }

    public void setIng(String ing) {
        this.ing = ing;
    }
}
