package com.mouse.springbootshiro.util;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

/**
 * 
* @ClassName: UUIDUtil 
* @Description: TODO(UUID随机数的处理类) 
* @author shil
* @date 2017年5月25日 下午1:18:58 
* 
 */
public class UUIDUtil {

	/**
	 * @Description 获得md5
	 * @author shichanghe
	 * @return String
	 * @date 2017-05-26 
	 * @throws
	 */
	public static String toMD5(String plainText) {
		String password = "";
        try {
             //生成实现指定摘要算法的 MessageDigest 对象。
             MessageDigest md = MessageDigest.getInstance("MD5");    
             //使用指定的字节数组更新摘要。
             md.update(plainText.getBytes());
             //通过执行诸如填充之类的最终操作完成哈希计算。
             byte b[] = md.digest();
             //生成具体的md5密码到buf数组
             int i;
             StringBuffer buf = new StringBuffer("");
             for (int offset = 0; offset < b.length; offset++) {
                  i = b[offset];
                  if (i < 0)
                      i += 256;
                  if (i < 16)
                      buf.append("0");
                  buf.append(Integer.toHexString(i));
             }
             password = buf.toString();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return password;
   }
	
	/**
	 * @Description 获得一个UUID
	 * @author chenyiping
	 * @return
	 * @date 2014-2-27 上午10:03:15
	 * @throws
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉 "-" 符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	/**
	 * @Description 获得指定字符的UUID
	 * @author yinbingpeng
	 * @param number 需要获得的UUID字符数量
	 * @return
	 * @date 2014-2-27 上午9:50:12
	 * @throws
	 */
	public static String getUUID(int number) {
		if (number < 1)
			return null;
		return UUID.randomUUID().toString().replace("-", "").substring(0, number);
	}

	/**
	 * @Description 获得指定数量的UUID
	 * @author chenyiping
	 * @param number 需要获得的UUID数量
	 * @return
	 * @date 2014-2-27 上午10:04:00
	 * @throws
	 */
	public static String[] getUUIDS(int number) {
		if (number < 1) {
			return null;
		}
		String[] strs = new String[number];
		for (int i = 0; i < number; i++) {
			strs[i] = getUUID();
		}
		return strs;
	}
	
	/**
	 * @Description 随机数
	 * @author maxiaojuan
	 * @return  
	 * @throws Exception 
	 * @date 2014-4-16 上午9:54:25  
	 * @throws
	 */
	public static String getRandom() {
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int length = 6;
		String result = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int rd = random.nextInt(10);
			result += str[rd];
		}
		return result;
	}

	public static void main(String[] args) {
		String string = getRandom();
		System.out.println(string);
	}

}
