package org.jumutang.project.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * 获取整形数据的工具类
 * @author OAK
 *
 */
public class RandomUtil {
	
	public static int nextInt(int maxInt){
		Random random=new Random();
		return random.nextInt(maxInt);
	}
	
	public static double nextDouble(){
		return Math.random();
	}
	public static void main(String[] args) {
		List<String> a= new ArrayList<>();
		a.add("111");
		a.add("222");
		a.add("333");
		for (int i = 0; i < 100; i++) {
			System.out.println(nextInt(a.size()));
		}
	}
}
