package com.sizxero.crowbus.security;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

    private String source;
    private String algorithm;

    // constructors
    public Encrypt( ) {
    }

    // property setters
    public void setSource(String source) {
        this.source = source;
    }
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    // property getters
    public String getEncrypt(){
        return getEncrypt(this.source, this.algorithm);
    }
    public String getEncrypt(String algorithm){
        return getEncrypt(this.source, algorithm);
    }
    public String getEncrypt(String source, String algorithm){
        return encode(source, algorithm);
    }

    private static String byteToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for(byte b : data) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    private String encode(String source, String algorithm) {
//        String initSalt = "개별프로젝트";
        String result = "";
        try{
            // MD2, MD5, SHA-256, SHA-512  except for MD4, SHA-A
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            result = byteToHexString(md.digest());

        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        } //try

        return result;
    }; //encode

} //class