
package com.tekin.baungm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class KatilimCek {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("adsoyad")
    @Expose
    private String adsoyad;
    @SerializedName("ogrenci_numarasi")
    @Expose
    private String ogrenciNumarasi;
    @SerializedName("etkinlik_adi")
    @Expose
    private String etkinlikAdi;

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
     *     The adsoyad
     */
    public String getAdsoyad() {
        return adsoyad;
    }

    /**
     * 
     * @param adsoyad
     *     The adsoyad
     */
    public void setAdsoyad(String adsoyad) {
        this.adsoyad = adsoyad;
    }

    /**
     * 
     * @return
     *     The ogrenciNumarasi
     */
    public String getOgrenciNumarasi() {
        return ogrenciNumarasi;
    }

    /**
     * 
     * @param ogrenciNumarasi
     *     The ogrenci_numarasi
     */
    public void setOgrenciNumarasi(String ogrenciNumarasi) {
        this.ogrenciNumarasi = ogrenciNumarasi;
    }

    /**
     * 
     * @return
     *     The etkinlikAdi
     */
    public String getEtkinlikAdi() {
        return etkinlikAdi;
    }

    /**
     * 
     * @param etkinlikAdi
     *     The etkinlik_adi
     */
    public void setEtkinlikAdi(String etkinlikAdi) {
        this.etkinlikAdi = etkinlikAdi;
    }

}
