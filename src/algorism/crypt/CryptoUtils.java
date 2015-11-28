package algorism.crypt;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;  

/**
 * Hash 加盐处理
 * salt根据Rfc2898标准生成
 * @see http://blog.csdn.net/xsi640/article/details/44962289
 * @author Administrator
 *
 */
public class CryptoUtils {  
    private static int saltSize = 32;  
    private static int iterations = 1000;  
    private static int subKeySize = 32;  
  
    /** 
     * 获取 Salt 
     * @return 
     */  
    public static String getSalt() {  
        return Rfc2898DeriveBytes.generateSalt(saltSize);  
    }  
  
    /** 
     * 获取hash后的密码 
     * @param password 
     * @param salt 
     * @return 
     */  
    public static String getHash(String password, String salt) {  
        Rfc2898DeriveBytes keyGenerator = null;  
        try {  
            keyGenerator = new Rfc2898DeriveBytes(password + salt, saltSize, iterations);  
        } catch (Exception e1) {  
            e1.printStackTrace();  
        }  
        byte[] subKey = keyGenerator.getBytes(subKeySize);  
        byte[] bSalt = keyGenerator.getSalt();  
        byte[] hashPassword = new byte[1 + saltSize + subKeySize];  
        System.arraycopy(bSalt, 0, hashPassword, 1, saltSize);  
        System.arraycopy(subKey, 0, hashPassword, saltSize + 1, subKeySize);  
        return Base64.encodeBase64String(hashPassword);  
    }  
  
    /** 
     * 验证密码 
     * @param hashedPassword 
     * @param password 
     * @param salt 
     * @return 
     */  
    public static boolean verify(String hashedPassword, String password, String salt) {  
        byte[] hashedPasswordBytes = Base64.decodeBase64(hashedPassword);  
        if (hashedPasswordBytes.length != (1 + saltSize + subKeySize) || hashedPasswordBytes[0] != 0x00) {  
            return false;  
        }  
  
        byte[] bSalt = new byte[saltSize];  
        System.arraycopy(hashedPasswordBytes, 1, bSalt, 0, saltSize);  
        byte[] storedSubkey = new byte[subKeySize];  
        System.arraycopy(hashedPasswordBytes, 1 + saltSize, storedSubkey, 0, subKeySize);  
        Rfc2898DeriveBytes deriveBytes = null;  
        try {  
            deriveBytes = new Rfc2898DeriveBytes(password + salt, bSalt, iterations);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        byte[] generatedSubkey = deriveBytes.getBytes(subKeySize);  
        return byteArraysEqual(storedSubkey, generatedSubkey);  
    }  
  
    private static boolean byteArraysEqual(byte[] storedSubkey, byte[] generatedSubkey) {  
        int size = storedSubkey.length;  
        if (size != generatedSubkey.length) {  
            return false;  
        }  
  
        for (int i = 0; i < size; i++) {  
            if (storedSubkey[i] != generatedSubkey[i]) {  
                return false;  
            }  
        }  
        return true;  
    }  
  
    /**
     * 测试
     * 即使加密字符串被截获, original也被反向破解出来   hash(pojieStr) = hashstr
     * 黑客用original登录系统也不会成功, 后台会拿pojieStr + salt之后hash(pojieStr + salt) 与hashStr不相等
     * 
     * 前提, salt服务器要知道(随机salt需要存在DB中与用户关联,  固定salt可以存服务器中)
     * @param args
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {  
    	//salt也可以用固定的字符串
    	//使用不固定的salt的话  salt需要跟
        String salt = CryptoUtils.getSalt();  
        String password = "admin123";  
        String hashPassword = CryptoUtils.getHash(password, salt);  
        System.out.println("hashPassword:" + hashPassword);  
        System.out.println("salt:" + salt);  
        System.out.println("password:" + password);  
        // verify  
        boolean result = CryptoUtils.verify(hashPassword, password, salt);  
        System.out.println("Verify:" + result);  
      
    } 
    
}

