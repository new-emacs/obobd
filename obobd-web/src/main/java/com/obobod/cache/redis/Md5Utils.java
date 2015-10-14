package com.obobod.cache.redis;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Utils {

  public static final int MD5_LENGTH = 16; // bytes

  public static byte[] md5sum(String s) {
	  if (s==null)
		  return null;
    MessageDigest d;
    try {
      d = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("MD5 algorithm not available!", e);
    }

    return d.digest(s.getBytes(Charset.forName("UTF-8")));
  }
  
  public static void main(String args[]) throws UnsupportedEncodingException{
	  String test="SELECT NON EMPTY {Hierarchize({[Measures].[销量],[Measures].[销售金额]})} ON COLUMNS, NON EMPTY { HIERARCHIZE( DESCENDANTS( [城市.省份城市].[北京] ,  [城市.省份城市].[城市], SELF_AND_BEFORE ) ) } ON ROWS FROM [CommodityHotSale_W] WHERE  CrossJoin({ [部门].[35]}, CrossJoin( [年周].[2014].[50]:[年周].[2015].[2] ,{[商品品类.品类Sku].[737].[738].[745].[1453]}))";
	  byte s[] = Md5Utils.md5sum(test);
	  for (int i = 0; i < s.length; i++) {
		System.out.println(s[i]);
	}
//	  System.out.println(new String(Md5Utils.md5sum(test),"utf-8"));
//	  System.out.println(new String(Md5Utils.md5sum(test),"utf-8"));
  }

}
