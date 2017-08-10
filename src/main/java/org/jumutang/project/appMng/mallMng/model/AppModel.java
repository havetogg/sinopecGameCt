package org.jumutang.project.appMng.mallMng.model;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 10:40 2017/8/8
 * @Modified By:
 */
public class AppModel {

    private String id;

    private String userCode;

    private String mobile;

    private String create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
