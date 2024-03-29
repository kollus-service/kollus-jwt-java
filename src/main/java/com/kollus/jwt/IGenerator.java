package com.kollus.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface IGenerator {

    String generate() throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException;
    String toJson() throws JsonProcessingException;
}
