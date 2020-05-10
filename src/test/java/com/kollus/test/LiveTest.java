package com.kollus.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kollus.live_jwt.LiveJwtGenerator;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class LiveTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {


        LiveJwtGenerator jwtGenerator = new LiveJwtGenerator();

        ArrayList<String[]> channels = new ArrayList<String[]>();

        channels.add(new String[]{"f7dbjonf9b7fze60", "기타"});
        channels.add(new String[]{"aaylieayxpmcvdym","ㄱㅣ타2"});

        for (String[] channel : channels) {
            String token = jwtGenerator.secret_key("hdyang2")
                    .live_media_channel_key(channel[0])
                    .expire_time(1587015938)
//                    .player_version("ios")
                    .generate();
            String playUrl = String.format("https://v-live-kr.kollus.com/s?jwt=%s&custom_key=%s", token, "3051971ce75403e3596e0b3cc059e445aa305a6481dd2bf9923e07e0f91e932e");
            System.out.println(playUrl);

//            System.out.format("%s : %s\n%s\n", channel[1], channel[0], playUrl);
//            System.out.format("adapter.add(new LiveChannelItem(\"%s\", \"%s\", \"%s\"));\n", channel[1], channel[0], playUrl);

        }

    }
}
