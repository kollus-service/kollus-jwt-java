package com.kollus.test;

import com.kollus.jwt.JwtGenerator;
import com.kollus.jwt.Mc;
import com.kollus.jwt.McGenerator;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class YanadooTest
{
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException{


        JwtGenerator jwtGenerator = new JwtGenerator();
        McGenerator mcGenerator = new McGenerator();
        Mc mc = mcGenerator.mckey("ajNcjGnq").build();
        String token = jwtGenerator.mc(mc).expt(2000000000).secret_key("bambada").cuid("hdyang").generate();

        System.out.format("https://v.kr.kollus.com/s?jwt=%s&custom_key=%s", token, "d612049868020324212bfec4c85fde54675628ec50ed89539c780534d866f530");

    }
}
