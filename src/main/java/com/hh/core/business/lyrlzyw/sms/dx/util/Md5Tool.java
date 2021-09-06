package com.hh.core.business.lyrlzyw.sms.dx.util;
import java.security.MessageDigest;

public class Md5Tool {
	/**
	 * 利用MD5进行加密
	 * @param s
	 * @return
	 */
    public final static String EncoderMd5(String s){
    	char hexDigits[] = { 
    	'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 
    	'e', 'f'}; 
    	if(s == null){
    		return null;
    	}
    	try { 
	    	byte[] strTemp = s.getBytes("utf-8"); 
	    	MessageDigest mdTemp = MessageDigest.getInstance("MD5");
	    	mdTemp.update(strTemp); 
	    	byte[] md = mdTemp.digest(); 
	    	int j = md.length; 
	    	char str[] = new char[j * 2]; 
	    	int k = 0; 
	    	for (int i = 0; i < j; i++) { 
		    	byte byte0 = md[i]; 
		    	str[k++] = hexDigits[byte0 >>> 4 & 0xf]; 
		    	str[k++] = hexDigits[byte0 & 0xf]; 
	    	} 
	    	return new String(str);
    	}catch (Exception e){
    		return null; 
    	} 
    }

    
    public static void main(String[] args){
    	try{
    		System.out.println(EncoderMd5("accNbr=18950295817&templateId=1&parameters=福州电信枢纽营业厅|2&timeStamp=1425461130023&fromSys=DQFXPT&key=SMS_DQFXPT_150209"));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
