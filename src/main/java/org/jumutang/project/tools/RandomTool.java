package org.jumutang.project.tools;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 14:54 2017/8/3
 * @Modified By:
 */
public class RandomTool {
    //随机字符串方法
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        java.util.Random random = new java.util.Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    //随机字符串方法
    public static String getRandomStringByType(String type) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        java.util.Random random = new java.util.Random();
        StringBuffer sb = new StringBuffer();
        sb.append(type);
        for (int i = 0; i < 10; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        while(true){
            System.out.println(RandomTool.getRandomStringByType("01").startsWith("01"));
        }
    }
}
