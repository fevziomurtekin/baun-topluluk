
package com.tekin.baungm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Example {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("baslik")
    @Expose
    private String baslik;
    @SerializedName("yazi")
    @Expose
    private String yazi;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("date")
    @Expose
    private String date;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The baslik
     */
    public String getBaslik() {
        return baslik;
    }

    /**
     * 
     * @param baslik
     *     The baslik
     */
    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    /**
     * 
     * @return
     *     The yazi
     */
    public String getYazi() {
        return yazi;
    }

    /**
     * 
     * @param yazi
     *     The yazi
     */
    public void setYazi(String yazi) {
        this.yazi = yazi;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

}
