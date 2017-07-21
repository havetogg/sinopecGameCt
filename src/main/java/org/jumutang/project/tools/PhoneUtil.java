package org.jumutang.project.tools;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 10:56 2017/7/18
 * @Modified By:
 */
public class PhoneUtil {
    public static String phoneSecrecy(String phone){
        String phoneStart = phone.substring(0,4);
        String phoneEnd = phone.substring(7);
        return phoneStart+"****"+phoneEnd;
    }

    public static void main(String[] args) {
        System.out.println(PhoneUtil.phoneSecrecy("18094233235"));
    }
}
