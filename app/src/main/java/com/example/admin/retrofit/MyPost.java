package com.example.admin.retrofit;


import java.security.MessageDigest;

public class MyPost {
    private int status;
    private String result;

    public int getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    public static String getSha256(String value) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(value.getBytes());
            return bytesToHex(md.digest());
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString() ;
    }
}