
package com.jowney.common.util.tsl;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ConverterUtils {
    private static ByteBuffer intBuf = ByteBuffer.allocate(4);
    private static ByteBuffer shortBuf = ByteBuffer.allocate(2);
    private static ByteOrder order = ByteOrder.LITTLE_ENDIAN;
    static {
        intBuf.order(order);
        shortBuf.order(order);
    }


    public static byte[] byteMerger(byte[] bt1, byte[] bt2){
        byte[] bt3 = new byte[bt1.length+bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }

    public static byte[] hex2byte(String str){
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    public static String byte2hex(byte[] b)
    {
        StringBuilder hs = new StringBuilder();
        String tmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            tmp = Integer.toHexString(b[n] & 0XFF);
            if (tmp.length() == 1)
                hs.append('0');
            hs.append(tmp);
        }
        return hs.toString().toLowerCase();
    }

    public static byte[] getBytes (char[] chars) {
        Charset cs = Charset.forName ("UTF-8");
        CharBuffer cb = CharBuffer.allocate (chars.length);
        cb.put (chars);
        cb.flip ();
        ByteBuffer bb = cs.encode (cb);

        return bb.array();
    }

    public static char[] getChars (byte[] bytes) {
        Charset cs = Charset.forName ("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate (bytes.length);
        bb.put (bytes);
        bb.flip ();
        CharBuffer cb = cs.decode (bb);

        return cb.array();
    }

    public static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

    public static char byteToChar(byte[] b) {
        int hi = (b[0] & 0xFF) << 8;
        int lo = b[1] & 0xFF;
        return (char) (hi | lo);
    }


    //byte 数组与 int 4的相互转换
    public static byte[] intToBytes(int x) {
        intBuf.clear();
        intBuf.putInt(0, x);
        return intBuf.array();
    }

    public static int bytesToInt(byte[] bytes) {
        intBuf.clear();
        intBuf.put(bytes, 0, bytes.length);
        intBuf.flip();//need flip
        return intBuf.getInt();
    }

    //byte 数组与 short2的相互转换
    public static byte[] shortToBytes(short x) {
        shortBuf.clear();
        shortBuf.putShort(0, x);
        return shortBuf.array();
    }

    public static short bytesToShort(byte[] bytes) {
        shortBuf.clear();
        shortBuf.put(bytes, 0, bytes.length);
        shortBuf.flip();//need flip
        return shortBuf.getShort();
    }


    //解码返回byte

    public static byte[] decryptBASE64(String message) {
        try{
            return Base64.decode(message.replaceAll("[\r,\n]", ""));
        }catch (Exception e){
            return null;
        }
    }

    //编码返回字符串
    public static String encryptBASE64(byte[] key) {
        return Base64.encode(key).replaceAll("[\r,\n]", "");
    }

    public static byte[] reverse(byte[] b)
    {
        byte[] b1 = new byte[b.length];
        int index = 0;
        for(int i=b.length-1; i>=0; --i)
        {
            b1[index] = b[i];
            ++index;
        }
        return b1;
    }

    /**************
     * 16进制转10进制字符串
     * @param hexNum "fe10B6"
     * @return octStr "522684"
     */
    public static String getDecByHex(String hexNum){
        hexNum = hexNum.toUpperCase();
        hexNum = reverse(hexNum);

        String DEC = "0123456789ABCDEF";
        List<String> decNums = new ArrayList<>();

        for(int i=0; i<hexNum.length(); i++){
            String curNum = ""+hexNum.charAt(i);
            curNum = "" + DEC.indexOf(curNum);
            curNum = reverse(curNum);

            for(int j=0; j<i; j++){
                curNum = getDecDup16(curNum);
            }
            decNums.add(curNum);
        }

        String dst = DecAdd(decNums);
        dst = reverse(dst);
        return dst;
    }

    public static String getDecDup16(String decNum){
        StringBuilder dup6 = new StringBuilder();
        int carryOver = 0;  //进位
        for(int i=0; i<decNum.length(); i++){      // *6
            int curNum = Integer.parseInt(""+decNum.charAt(i));
            curNum = curNum*6+carryOver;
            dup6.append(curNum%10);
            carryOver = curNum/10;
        }

        if(carryOver>0)
            dup6.append(reverse(""+carryOver));

        String dup6Str = dup6.toString();
        String dup10Str = "0"+decNum;

        List<String> decNums = new ArrayList<>();
        decNums.add(dup6Str);
        decNums.add(dup10Str);
        return DecAdd(decNums);
    }

    //十进制数字字符串相加 大端
    public static String DecAdd(List<String> decNums){
        int length = decNums.get(0).length();

        for(String decNum: decNums){
            if(decNum.length() > length)
                length= decNum.length();
        }

        StringBuilder dst = new StringBuilder();

        int carryOver = 0;  //进位

        for(int i=0; i<length; i++){
            int curNum = carryOver;
            for(String decNum: decNums){
                if(decNum.length() > i)
                    curNum = curNum + Integer.parseInt(""+decNum.charAt(i));
            }
            dst.append(curNum%10);
            carryOver = curNum/10;
        }

        if(carryOver>0){
            dst.append(reverse(""+carryOver));
        }
        return dst.toString();
    }

    public static String reverse(String src){
        StringBuilder sb1 = new StringBuilder(src);
        return sb1.reverse().toString();
    }

    public static String convertCardNo(String cardNo){
        if(cardNo == null)
            return null;
        cardNo = cardNo.toUpperCase();
        if(cardNo.startsWith("HEX"))
            cardNo = cardNo.substring(3, cardNo.length());
        else if(cardNo.startsWith("DEC")){
            cardNo = cardNo.substring(3, cardNo.length());
            cardNo = DecToHex(cardNo);
        }
        if(cardNo.length() % 2 == 1)
            cardNo = "0"+cardNo;
        cardNo = cardNo.toUpperCase();
        return cardNo;
    }

    public static String DecToHex(String decStr){
        return new BigInteger(decStr, 10).toString(16);
    }

    public static Long HexToLong(String hex){
        return Long.parseLong(hex,16);
    }

}
