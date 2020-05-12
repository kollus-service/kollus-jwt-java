package com.kollus.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kollus.live.ChattingPolicy;
import com.kollus.live.LiveTokenGenerator;
import com.kollus.url.QueryString;
import com.kollus.url.StreamType;
import com.kollus.url.UrlFactory;
import com.kollus.url.UrlType;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class LiveTest {
    @Test
    public void createLiveUrl() {
        String secret_key = "hdyang2";
        String custom_key = "e02395ee5914d5ef9553908e54b21f61f72b7b4b240b3876b596fb896bcc1fa4";
        LiveTokenGenerator generator = new LiveTokenGenerator();
        ChattingPolicy chattingPolicy = new ChattingPolicy();
        chattingPolicy.setIs_admin(true);
        chattingPolicy.setIs_visible(true);
        generator.client_user_id("client_user_id")
                .expire_time(1609426799) //2020-12-31 23:59:59
                .client_user_image("https://lh3.googleusercontent.com/proxy/HAz9-gf9ybkMax2-xl5BUHBp6pRBL178LIy5j4s9aUTnwqS13h9htcR75DjAOkjfvAZJ1VqNF8gkNvWjU-JK8DZPZtBXPNVr")
                .client_user_name("채팅테스트")
                .chatting_policy(chattingPolicy)
                .live_media_channel_key("aaylieayxpmcvdym")
                .secret_key(secret_key);
        QueryString queryString = QueryString.create()
                .autoplay(true)
                .uservalue0("uservalue0")
                .uservalue2("uservalue2");
        String playUrl = "";

        try {
            playUrl = UrlFactory.create(UrlType.LIVE, StreamType.STREAMING, generator, custom_key, queryString);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(playUrl);
    }
}
