
package com.tekin.baungm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MesajCek {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("mesaj")
    @Expose
    private String mesaj;
    @SerializedName("mesaj_gonderen")
    @Expose
    private String mesajGonderen;
    @SerializedName("tarih")
    @Expose
    private String tarih;

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
     *     The mesaj
     */
    public String getMesaj() {
        return mesaj;
    }

    /**
     * 
     * @param mesaj
     *     The mesaj
     */
    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    /**
     * 
     * @return
     *     The mesajGonderen
     */
    public String getMesajGonderen() {
        return mesajGonderen;
    }

    /**
     * 
     * @param mesajGonderen
     *     The mesaj_gonderen
     */
    public void setMesajGonderen(String mesajGonderen) {
        this.mesajGonderen = mesajGonderen;
    }

    /**
     * 
     * @return
     *     The tarih
     */
    public String getTarih() {
        return tarih;
    }

    /**
     * 
     * @param tarih
     *     The tarih
     */
    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

}
