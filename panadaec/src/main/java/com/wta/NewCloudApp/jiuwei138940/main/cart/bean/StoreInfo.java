package com.wta.NewCloudApp.jiuwei138940.main.cart.bean;



public class StoreInfo {
    protected String merchName;
    protected String id;
    protected boolean isChoosed;
    protected String logo;
    private boolean isEditor;

    public boolean isEditor() {
        return isEditor;
    }

    public void setEditor(boolean editor) {
        isEditor = editor;
    }

    public StoreInfo(String merchName, String id, String logo) {
        this.merchName = merchName;
        this.id = id;
        this.logo = logo;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
