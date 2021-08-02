package com.jowney.common.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Tool {
    /***
     * MD5加密 生成32位md5码
     * @param待加密字符串
     * @return 返回32位md5码
     */
    public static String md5_32Encrypt(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /***
     * MD5加密 生成16位md5码 然后进行base64编码
     * @param待加密字符串
     * @return 返回生成16位md5码 然后进行base64编码
     */
    public static String md5_16ToBase64Encrypt(String sourceStr) {
        String result = "";//通过result返回结果值
        try {
            //1.初始化MessageDigest信息摘要对象,并指定为MD5不分大小写都可以
            MessageDigest md = MessageDigest.getInstance("MD5");
            //2.传入需要计算的字符串更新摘要信息，传入的为字节数组byte[],将字符串转换为字节数组使用getBytes()方法完成
            md.update(sourceStr.getBytes());
            //3.计算信息摘要digest()方法,返回值为字节数组
            byte b[] = md.digest();
            int i;//定义整型
            //声明StringBuffer对象
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];//将首个元素赋值给i
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");//前面补0
                buf.append(Integer.toHexString(i));//转换成16进制编码
            }
            result = buf.toString().substring(8, 24);//转换成字符串,输出16位16进制字符串
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //base64编码
        return result;

    }
}
