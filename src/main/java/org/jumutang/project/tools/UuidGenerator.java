package org.jumutang.project.tools;


import java.util.UUID;

/**
 * UUID的生成器
 *
 */
public class UuidGenerator
{
	/**
	 * 生成UUID的值
	 * @return
	 */
	public synchronized static String generateUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static void main(String[] args) {
		String generateUUID = generateUUID();
		System.out.println(generateUUID);
		System.out.println(generateUUID.length());
	}
}
