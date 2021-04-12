package com.youcode.reservation.gravatarGenerator;


import org.springframework.stereotype.Component;

import java.io.*;
import java.security.*;

@Component
public class Gravatar {
    public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (byte b : array) {
            sb.append(Integer.toHexString((b
                    & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }
    public static String md5Hex (String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex (md.digest(message.getBytes("CP1252")));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("handle this error");
        }
        catch (UnsupportedEncodingException e) {
            System.out.println("handle this error later");
        }
        return null;
    }
}

//https://www.gravatar.com/avatar/' + ${user.gravatar} + '?d=robohash&s=130
