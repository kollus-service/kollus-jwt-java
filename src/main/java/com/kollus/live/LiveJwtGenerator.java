package com.kollus.live;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kollus.jwt.JwtUtil;
import com.kollus.vod.VideoWatermarkingCodePolicy;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class LiveJwtGenerator {
    private JwtPayload jwtPayload;
    private String secret_key;

    public LiveJwtGenerator(){
        this.jwtPayload = new JwtPayload();
    }

    public LiveJwtGenerator secret_key(String secret_key){
        this.secret_key = secret_key;
        return this;
    }

    public LiveJwtGenerator client_user_id(String client_user_id){
        this.jwtPayload.setClient_user_id(client_user_id);
        return this;
    }
    public LiveJwtGenerator client_user_name(String client_user_name){
        this.jwtPayload.setClient_user_name(client_user_name);
        return this;
    }
    public LiveJwtGenerator video_watermarking_code_policy(VideoWatermarkingCodePolicy video_watermarking_code_policy){
        this.jwtPayload.setVideo_watermarking_code_policy(video_watermarking_code_policy);
        return this;
    }
    public LiveJwtGenerator client_user_image(String client_user_image){
        this.jwtPayload.setClient_user_image(client_user_image);
        return this;
    }
    public LiveJwtGenerator expire_time(long expire_time){
        this.jwtPayload.setExpire_time(expire_time);
        return this;
    }
    public LiveJwtGenerator play_expt(long play_expt){
        this.jwtPayload.setPlay_expt(play_expt);
        return this;
    }
    public LiveJwtGenerator live_media_channel_key(String live_media_channel_key){
        this.jwtPayload.setLive_media_channel_key(live_media_channel_key);
        return this;
    }
    public LiveJwtGenerator live_media_profile_key(String live_media_profile_key){
        this.jwtPayload.setLive_media_profile_key(live_media_profile_key);
        return this;
    }
    public LiveJwtGenerator title(String title){
        this.jwtPayload.setTitle(title);
        return this;
    }
    public LiveJwtGenerator chatting_policy(ChattingPolicy chatting_policy){
        this.jwtPayload.setChatting_policy(chatting_policy);
        return this;
    }
    public LiveJwtGenerator player_version(String player_version){
        this.jwtPayload.setPlayer_version(player_version);
        return this;
    }
    public String generate() throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException {
        JwtUtil util = new JwtUtil();
        ObjectMapper mapper = new ObjectMapper();
        return util.createJwt(mapper.writeValueAsString(this.jwtPayload), this.secret_key);
    }
}
