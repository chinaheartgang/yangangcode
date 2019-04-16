package com.ems.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Sha256Util {
 
    /**
    * 利用java原生的类实现SHA256加密
    * @param str 加密后的报文
    * @return
    */
    public static String getSHA256(String str){
      MessageDigest messageDigest;
     String encodestr = "";
     try {
      messageDigest = MessageDigest.getInstance("SHA-256");
      messageDigest.update(str.getBytes("UTF-8"));
      encodestr = byte2Hex(messageDigest.digest());
     } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
     } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
     }
     return encodestr;
    }
    /**
    * 将byte转为16进制
    * @param bytes
    * @return
    */
    private static String byte2Hex(byte[] bytes){
     StringBuffer stringBuffer = new StringBuffer();
     String temp = null;
     for (int i=0;i<bytes.length;i++){
      temp = Integer.toHexString(bytes[i] & 0xFF);
      if (temp.length()==1){
      //1得到一位的进行补0操作
      stringBuffer.append("0");
      }
      stringBuffer.append(temp);
     }
     return stringBuffer.toString();
    }
    public static String getTimeStamp(){
    	return String.valueOf(new Date().getTime()).substring(1,5);
    }
    public static String getUUID(){
    	String uuid = UUID.randomUUID().toString().replace("-", "");
    	return uuid;
    }
    public static String getOrderIdByTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;
    }

    //测试
    public static void main(String[] args) throws ParseException {
    	//===111111===bcb15f82 1479b4d5 772bd0ca 866c00ad 5f926e35 80720659 cc80d39c 9d09802a
		String sha256 = Sha256Util.getSHA256("111111");
    	System.out.println("===="+sha256);
    	System.out.println(sha256.substring(0, 32));
    	System.out.println(new Date().getTime());
    	System.out.println(getTimeStamp());
        System.out.println(String.valueOf(System.currentTimeMillis()));
        System.out.println(getOrderIdByTime());
	}
}