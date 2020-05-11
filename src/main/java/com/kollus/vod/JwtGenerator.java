package com.kollus.vod;

import com.kollus.jwt.Generator;

import java.util.ArrayList;

public class JwtGenerator extends Generator<VodPayload> {
    
    private String secret_key;

    public JwtGenerator() {
        this.payload = new VodPayload();
    }

    public JwtGenerator secret_key(String secret_key){
        this.secret_key = secret_key;
        return this;
    }

    public JwtGenerator cuid(String cuid) {
        this.payload.setCuid(cuid);
        return this;
    }

    public JwtGenerator awtc(String awtc) {
        this.payload.setAwtc(awtc);
        return this;
    }

    public JwtGenerator expt(long expt) {
        this.payload.setExpt(expt);
        return this;
    }

    public JwtGenerator mc(Mc mc) {
        if (this.payload.getMc() == null) {
            this.payload.setMc(new ArrayList<Mc>());
        }
        this.payload.getMc().add(mc);
        return this;
    }

    public JwtGenerator video_watermarking_code_policy(VideoWatermarkingCodePolicy video_watermarking_code_policy) {
        this.payload.setVideo_watermarking_code_policy(video_watermarking_code_policy);
        return this;
    }

    public JwtGenerator pc_skin(PcSkin pc_skin) {
        this.payload.setPc_skin(pc_skin);
        return this;
    }
}
