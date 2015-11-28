package url;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test2 {

	/**
	 * @param args
	 * http://asflex.iteye.com/blog/356028
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {  
			//将 String 转换为 application/x-www-form-urlencoded MIME 格式
            String encodeStr = URLEncoder.encode("中国", "utf-8");  
            System.out.println("处理后:" + encodeStr);  
            String decodeStr = URLDecoder.decode(encodeStr, "utf-8");  
            System.out.println("解码:" + decodeStr);  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
	}

}
