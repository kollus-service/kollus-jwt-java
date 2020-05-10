package com.kollus.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kollus.jwt.JwtGenerator;
import com.kollus.jwt.Mc;
import com.kollus.jwt.McGenerator;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class DiveTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {


        String custom_key = "0439b59f2de892aa950a14e91a778550b1035bae4d136e21c166531d482b96f7";
        String secret_key = "hyundaicard";


        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap mcMap1 = new HashMap<String, String>();
        mcMap1.put("mckey", "P0yl1P5K");
        mcMap1.put("pfname", "정사각 PC");
        mcMap1.put("pfkey", "hyundaicard-pc1-high");

        HashMap mcMap2 = new HashMap<String, String>();
        mcMap2.put("mckey", "P0yl1P5K");
        mcMap2.put("pfname", "정사각 모바일");
        mcMap2.put("pfkey", "hyundaicard-mobile1-normal");

        HashMap mcMap3 = new HashMap<String, String>();
        mcMap3.put("mckey", "tyxgjAVz");
        mcMap3.put("pfname", "16X9 전용 PC");
        mcMap3.put("pfkey", "hyundaicard-pc1-hd\t");

        HashMap mcMap4 = new HashMap<String, String>();
        mcMap4.put("mckey", "tyxgjAVz");
        mcMap4.put("pfname", "16X9 전용 모바일");
        mcMap4.put("pfkey", "hyundaicard-mobile1-hd");

        HashMap mcMap5 = new HashMap<String, String>();
        mcMap5.put("mckey", "1qL5mLAL");
        mcMap5.put("pfname", "9:16 세로 PC");
        mcMap5.put("pfkey", "hyundaicard-pc1-hd-1");
        HashMap mcMap6 = new HashMap<String, String>();
        mcMap6.put("mckey", "1qL5mLAL");
        mcMap6.put("pfname", "9:16 세로 모바일");
        mcMap6.put("pfkey", "hyundaicard-mobile1-hd");

        list.add(mcMap1);
        list.add(mcMap2);
        list.add(mcMap3);
        list.add(mcMap4);
        list.add(mcMap5);
        list.add(mcMap6);


        for (HashMap<String, String> mcMap : list) {
            JwtGenerator jwtGenerator = new JwtGenerator();
            McGenerator mcGenerator = new McGenerator();

            Mc mc = mcGenerator.mckey(mcMap.get("mckey"))
                    .mcpf(mcMap.get("pfkey"))
                    .build();
            String jwtBody = jwtGenerator.mc(mc)
                    .expt(2000000000)
                    .secret_key(secret_key)
                    .cuid("dive")
                    .toJson();
            String token = jwtGenerator.generate();

            System.out.format("%s(%s)\n", mcMap.get("mckey"), mcMap.get("pfname"));
            System.out.println(jwtBody);

            System.out.format("HLS 프로파일지정 : https://v.kr.kollus.com/sr?jwt=%s&custom_key=%s&cdn=hd-hls\n", token, custom_key);
            System.out.format("HLS ABR : https://v.kr.kollus.com/sr?jwt=%s&custom_key=%s&cdn=hd-hls2\n", token, custom_key);
            System.out.format("MP4 : https://v.kr.kollus.com/sr?jwt=%s&custom_key=%s&cdn=hd-dd\n", token, custom_key);
            System.out.println("");

        }


    }
}
