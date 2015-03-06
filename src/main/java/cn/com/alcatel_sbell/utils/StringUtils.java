package cn.com.alcatel_sbell.utils;


public class StringUtils {
 public static String  encodingConvert(String origencod,String targetencod,String orString) throws Exception {
	  return new String(orString.getBytes(origencod),targetencod);
}
}
