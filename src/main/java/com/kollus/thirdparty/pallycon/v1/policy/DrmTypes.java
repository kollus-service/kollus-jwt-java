package com.kollus.thirdparty.pallycon.v1.policy;

public enum DrmTypes {
    NCG("NCG"),
    Widevine("Widevine"),
    PlayReady("PlayReady"),
    FairPlay("FairPlay");

    private String value;
    private DrmTypes(String type){
        this.value = type;
    }
    public String getType(){
        return this.value;
    }
}
