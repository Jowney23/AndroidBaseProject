package com.jowney.common.util.tsl;


/**
 * description 数据脱敏工具类
 * author      kai.mr
 * create      2019-05-25 13:51
 * 
 * 由于二维码的加密解密，加密后字符串短一些 2022年5月11日16:44:03
 **/
public class Desensitize4ewm {
    private static final boolean ENCRYPT_VALID = true;

    /**
     * @param sourceString 明文
     * @param password     密码
     * @return 密文
     */
    public static String encrypt(String sourceString, String password) {
        if (!ENCRYPT_VALID)
            return sourceString;
        if (checkDesensitizeStr(sourceString))
            return sourceString;
        if (IotUtilsBase.isEmptyStr(sourceString))
            return sourceString;
        byte[] p = password.getBytes(); // 字符串转字符数组
        int n = p.length; // 密码长度
        byte[] c = sourceString.getBytes();
        int m = c.length; // 字符串长度
        for (int k = 0; k < m; k++) {
            int miMa = c[k] + p[(k / n > n - 1) ? (k % n) : (k / n)]; // 加密
            c[k] = (byte) miMa;
        }
        return "at3s2l7" + ConverterUtils.encryptBASE64(c);
    }

    /**
     * @param sourceString 密文
     * @param password     密码
     * @return 明文
     */

    public static String decrypt(String sourceString, String password) {
        if (!ENCRYPT_VALID)
            return sourceString;
        try {
            if (!checkDesensitizeStr(sourceString))
                return sourceString;
            sourceString = sourceString.substring(7);
            byte[] p = password.getBytes(); // 字符串转字符数组
            int n = p.length; // 密码长度
            byte[] c = ConverterUtils.decryptBASE64(sourceString);
            if (c == null)
                return sourceString;
            int m = c.length; // 字符串长度
            for (int k = 0; k < m; k++) {
                int miMa = c[k] - p[(k / n > n - 1) ? (k % n) : (k / n)]; // 解密
                c[k] = (byte) miMa;
            }
            return new String(c);
        } catch (Exception e) {
            return sourceString;
        }
    }

    public static String display(String cardNo) {
        if (cardNo == null || cardNo.length() < 3)
            return cardNo;
        if (cardNo.length() == 18) {
            return cardNo.substring(0, 4) + "**********" + cardNo.substring(14, 18);
        }
        int index = cardNo.length() / 3;
        StringBuilder dst = new StringBuilder(cardNo.substring(0, index));
        for (int i = 0; i < index; i++)
            dst.append("*");
        return dst.toString() + cardNo.substring(2 * index);
    }

    public static boolean checkDesensitizeStr(String str) {
        return str.startsWith("at3s2l7");
    }

}
