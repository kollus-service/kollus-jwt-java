package com.kollus.test;

import com.kollus.jwt.JwtGenerator;
import com.kollus.jwt.Mc;
import com.kollus.jwt.McGenerator;
import com.kollus.multidrm.token.CustomDataGenerator;
import com.kollus.multidrm.token.TokenGenerator;
import com.kollus.multidrm.tokenrule.DrmTypes;
import com.kollus.multidrm.tokenrule.TokenRuleGenerator;

import java.util.Date;


public class Main {

//    public static HashMap<String, SkylifeChannelInfo> channelMap = new HashMap<String, SkylifeChannelInfo>();
//    static {
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//        channelMap.put("viki", new SkylifeChannelInfo());
//    }


    //인카 설정 값
    private static final String INKA_SITE_ID = "7HL5";
    // Site Key - https://console.pallycon.com/svc/setting.htm 확인 가능
    private static final String INKA_SITE_KEY = "LveayTzvMHiyyU7KtYbwOhH25mEs8IG6";
    // Access Key - https://console.pallycon.com/svc/setting.htm 확인 가능
    private static final String INKA_ACCESS_KEY = "ylKHjBratSHOrJySd18DZzVOMvJX9z3H";
    private static final String INKA_LICENSE_URL = "https://license.pallycon.com/ri/licenseManager.do";
    private static final String INKA_CERTIFICATE_URL = "https://license.pallycon.com/ri/fpsKeyManager.do?siteId=7HL5";
    private static final String INKA_CUSTOM_HEADER_NAME = "pallycon-customdata-v2";

    //아카마이 설정 값
    private static final String AKAMAI_ENCRYPTION_KEY = "eaa80370984fac14418a0d01aa06aecd";
    private static final String AKAMAI_TOKEN_NAME = "hdnts";
    private static final String AKAMI_PATH = "/*";

    private static final String KOLLUS_USER_KEY = "a7df605307c95ac11bb204fe989791538139f4c9dcf573fe5251f736db5e1414";
    private static final String KOLLUS_SECRET_KEY = "skylife-prem-n";


//    private static final String KOLLUS_USER_KEY = "4b84396a8068b1a71b8f5633568c2f8fc1a0d7853985fe016f56e23c57dc90e1d0234b8879eb81714e6519a537056923a51b1ece3d062a98feafd33e744be9798f617fd9f98318284f450ad5652a3e69";
//    private static final String KOLLUS_SECRET_KEY = "hdyang";


    //URL DURATION 설정
    private static final int INKA_DURATION = 365*24*60*60;
    private static final int AKAMAI_DURATION = 365*24*60*60;


    public static void main(String[] args) {


        //	ViBYYt4t
        //skylife-prem-n
        //a7df605307c95ac11bb204fe989791538139f4c9dcf573fe5251f736db5e1414
        String strTokenRule = "";
        String strToken = "";
        TokenRuleGenerator tokenRuleGenerator = new TokenRuleGenerator();
        TokenGenerator tokenGenerator = new TokenGenerator();

        CustomDataGenerator customDataGenerator = new CustomDataGenerator();

        McGenerator mcGenerator = new McGenerator();
        JwtGenerator jwtGenerator = new JwtGenerator();
        try {
            strTokenRule = tokenRuleGenerator
                    .site_key(INKA_SITE_KEY)
                    .limit(false)
//                    .persistent(true)
//                    .duration(INKA_DURATION) // 1Hour
//                    .hardware_drm(true)
//                    .expire_date("2019-09-25T10:00:00Z")
                    .build();

            strToken = tokenGenerator
                    /*
                     * 브라우저별 DRM TYPE 설정
                     * IE, Edge: PlayReady
                     * Chrome, Firefox : Widevine
                     * Safari : FairPlay
                     * */
//                    .drm_type(DrmTypes.FairPlay)
                    .drm_type(DrmTypes.Widevine)
//                    .drm_type(DrmTypes.PlayReady)
                    .site_id(INKA_SITE_ID)
                    .user_id("catenoid") //시청자 아이디
                    /*
                     * 라이브 URL 주소중 채널 명으로 지정
                     * https://skylife-tm.akamaized.net/live-01-ch6/ch6_720p/manifest.mpd
                     * CID중 ch6_720p
                     * */
//                    .cid("ch340")
//                    .cid("ch340_720p")
//                    .cid("ch341_720p")
//                    .cid("ch342_720p")
//                    .cid("ch343_720p")
//                    .cid("ch344_720p")
                    .cid("ch147_720p")
//                    .cid("ch256_audio")
//                    .cid("ch253_audio")
//                    .cid("ch260_audio")
//                    .cid("ch269_audio")
//                    .cid("ch147_720p")
                    .token(strTokenRule)
                    .timestamp(new Date())
                    .hash(INKA_ACCESS_KEY)
                    .generate();
            System.out.println(strToken);
            Mc mc = mcGenerator
                    .mckey("VliI4Af7")
//                    .mckey("tmm2SJl9")

                    /*
                     * 브라우저별 URL및 스트리밍 타입 설정
                     * IE, Edge: dash (.mpd)
                     * Chrome, Firefox : dash (.mpd)
                     * Safari : hls(.m3u8)
                     * */
//            https://premium-live.catenoid.net/live-01-ch340/ch340_720p/playlist.m3u8
//                    .live_url("http://119.205.236.232:1935/live-01-ch340/ch340_720p/manifest.mpd")
//                    .live_url("https://skylife-tm-ch341.akamaized.net/live-01-ch341/ch341_720p/chunklist.m3u8")
//                    .live_url("https://skylife-tm-ch340.akamaized.net/live-01-ch340/ch340_720p/manifest.mpd")
//                    .live_url("https://skylife-tm-ch341.akamaized.net/live-01-ch341/ch341_720p/manifest.mpd")
//                    .live_url("https://skylife-tm-ch342.akamaized.net/live-01-ch342/ch342_720p/manifest.mpd")
//                    .live_url("https://skylife-tm-ch343.akamaized.net/live-01-ch343/ch343_720p/manifest.mpd")
//                    .live_url("https://skylife-tm-ch344.akamaized.net/live-01-ch344/ch344_720p/manifest.mpd")
//                    .live_url("https://premium-live.catenoid.net/live-01-ch340/ch340_720p/manifest.mpd")
//                    .live_url("https://premium-live.catenoid.net/live-01-ch341/ch341_720p/manifest.mpd")
//                    .live_url("https://premium-live.catenoid.net/live-01-ch342/ch342_720p/manifest.mpd")
//                    .live_url("https://premium-live.catenoid.net/live-01-ch343/ch343_720p/manifest.mpd")
//                    .live_url("https://premium-live.catenoid.net/live-01-ch344/ch344_720p/manifest.mpd")


//                    .live_url("https://skylife-tm-ch147.akamaized.net/live-01-ch147/ch147_720p/manifest.mpd")
//                    .live_url("https://premium-live.catenoid.net/live-01-ch147/ch147_720p/manifest_mvlist.mpd")
//                    .live_url("https://premium-live.catenoid.net/live-01-ch6/ch6_720p/chunklist.m3u8")
                    .live_url("https://premium-live.catenoid.net/live-01-ch147/ch147_720p/manifest.mpd")
//                    .live_url("https://premium-live.catenoid.net/live-01-ch14/ch14_720p/manifest.mpd")
//                    .live_url("https://premium-live.catenoid.net/live-01-ch340/ch340_720p/manifest.mpd")

//                    .live_url("https://skylife-tm-ch340.akamaized.net/live-01-ch340/ch340_720p/manifest.mpd")
//                    .live_url("https://skylife-tm-ch341.akamaized.net/live-01-ch341/ch341_720p/manifest.mpd")
//                    .live_url("https://skylife-tm-ch340.akamaized.net/live-01-ch342/ch342_720p/manifest.mpd")
//                    .live_url("https://skylife-tm-ch6.akamaized.net/live-01-ch6/ch6_720p/manifest.mpd")
//                    .live_url("https://premium-live.catenoid.net/live-01-ch340/ch340_720p/manifest.mpd")
//                    .live_url("https://skylife-tm-ch256.akamaized.net/live-01-ch256/ch256_audio/manifest.mpd")
//                    .live_url("https://skylife-tm-ch256.akamaized.net/live-01-ch256/ch256_audio/chunklist.m3u8")
//                    .live_url("https://skylife-tm-ch253.akamaized.net/live-01-ch253/ch253_audio/chunklist.m3u8")
//                    .live_url("https://skylife-tm-ch260.akamaized.net/live-01-ch260/ch260_audio/chunklist.m3u8")
//                    .live_url("https://skylife-tm-ch269.akamaized.net/live-01-ch269/ch269_audio/chunklist.m3u8")
//                                        .live_url("https://skylife-tm-ch147.akamaized.net/live-01-ch147/ch147_720p/chunklist.m3u8")
//                    .live_url("https://skylife-tm-ch147.akamaized.net/live-01-ch147/ch147_720p/manifest.mpd")
//                    .live_poster_url("https://v.kr.kollus.com/poster/wTP24AdO")
                    /*
                     * 아카마이 토큰 설정
                     * */
                    .live_url_token(AKAMAI_ENCRYPTION_KEY,
                            AKAMAI_TOKEN_NAME, AKAMI_PATH, AKAMAI_DURATION)
                    .drm_policy_kind("inka") //고정값
//                    .drm_policy_streaming_type("hls") //hls or dash
                    .drm_policy_streaming_type("dash") //hls or dash
                    .drm_policy_data_license_url(INKA_LICENSE_URL)
                    .drm_policy_data_certificate_url(INKA_CERTIFICATE_URL)//ios만 사용
                    .drm_policy_data_custom_header_key(INKA_CUSTOM_HEADER_NAME)
                    .drm_policy_data_custom_header_value(strToken)

                    .build();
            String jwttoken = jwtGenerator.cuid("ABC")
                    .expt(1866957016l)
                    .secret_key(KOLLUS_SECRET_KEY)
                    .mc(mc).generate();
//            System.out.println(mc.getLive().getUrl());
//            System.out.format("https://v.kr.kollus.com/s?jwt=%s&custom_key=%s\n", jwttoken, KOLLUS_USER_KEY);
            System.out.format("https://v.kr.kollus.com/s?jwt=%s&custom_key=%s\n", jwttoken, KOLLUS_USER_KEY);




//
//
//            /*audio, 홈쇼핑 채널*/
//            String KOLLUS_SECRET_KEY2 = "skylife-prem-n2";
//            String KOLLUS_USER_KEY2 = "8712b408f74156ffb6392dc45b7a93e9d429788ae111a21f416be1a63808a9d6";
//            McGenerator nonDrmMcGenerator = new McGenerator();
//            Mc nonDrmMc = nonDrmMcGenerator
//                    .mckey("4fIV4Jvr")
////                    .live_url("https://skylife-tm-ch340.akamaized.net/live-01-ch340/ch340_720p/manifest.mpd")
////            .live_url("https://skylife-tm-ch256.akamaized.net/live-01-ch256/ch256_audio/manifest.mpd")
////            .live_url("https://skylife-tm-ch269.akamaized.net/live-01-ch269/ch269_audio/manifest.mpd")
////                    .live_url("https://premium-live.catenoid.net/live-01-ch269/ch269_audio/manifest.mpd")
////            .live_url("https://skylife-tm-ch253.akamaized.net/live-01-ch253/ch253_audio/manifest.mpd")
////            .live_url("https://skylife-tm-ch260.akamaized.net/live-01-ch260/ch260_audio/manifest.mpd")
////            .live_url("https://skylife-tm-ch4.akamaized.net/live-01-ch4/ch4_720p/manifest.mpd")
////            .live_url("https://skylife-tm-ch6.akamaized.net/live-01-ch6/ch6_720p/manifest_mvlist.mpd")
////            .live_url("https://skylife-tm-ch10.akamaized.net/live-01-ch10/ch10_720p/manifest.mpd")
////            .live_url("https://skylife-tm-ch12.akamaized.net/live-01-ch12/ch12_720p/manifest.mpd")
////            .live_url("https://skylife-tm-ch14.akamaized.net/live-01-ch14/ch14_720p/manifest.mpd")
////                    .live_url("https://skylife-tm-ch256.akamaized.net/live-01-ch256/ch256_audio/manifest.mpd")
////                    .live_url("https://skylife-tm-ch253.akamaized.net/live-01-ch253/ch253_audio/manifest.mpd")
////                    .live_url("https://skylife-tm-ch260.akamaized.net/live-01-ch260/ch260_audio/manifest.mpd")
////                    .live_url("https://skylife-tm-ch269.akamaized.net/live-01-ch269/ch269_audio/manifest.mpd")
////                    .live_url_token(AKAMAI_ENCRYPTION_KEY,
////                            AKAMAI_TOKEN_NAME, AKAMI_PATH, AKAMAI_DURATION)
//                    .build();
//            JwtGenerator nonDrmJwtGenerator = new JwtGenerator();
//            String nonDrmJwtToken = nonDrmJwtGenerator
//                    .cuid("ABC")
//                    .expt(1766957016l)
//                    .mc(nonDrmMc)
//                    .secret_key(KOLLUS_SECRET_KEY2)
//                    .generate();
//
////            System.out.format("https://v.kr.kollus.com/s?jwt=%s&custom_key=%s\n", nonDrmJwtToken, KOLLUS_USER_KEY2);
//
//            mc.setMckey("h9Nw0JWm");
//            String jwttoken_hdyang = jwtGenerator.cuid("ABC")
//                    .expt(1766957016l)
//                    .secret_key("hdyang2")
//                    .mc(mc).generate();
//            System.out.println(nonDrmMc.getLive().getUrl());
////            System.out.format("https://v.kr.kollus.com/s?jwt=%s&custom_key=%s&player_version=html5\n", jwttoken_hdyang, "5ee45d7be587f9bcacc5d69d6bb6da0505aa48f7c0c57c2373058a2fda275e92");
//

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
