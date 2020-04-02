package com.wzh.command;

/**
 * web层返回提示信息
 */
public enum ResultCodeInfoEnum {
    SUCCESS_MSG(1,"success",""),
    FAIL_MSG(0,"success",""),
    NO_LOGIN(-2,"fail","请登录后在操作"),
    NO_PERMIT(-1,"fail","鉴权失败! 很抱歉,此功能暂时不对外开放!"),
    INSERT_SUCCESS(1,"success","发布成功!"),
    INSERT_FAIL(0,"fail","发布失败!"),
    REGISTER_SUCCESS(1,"success","注册成功!"),
    REGISTER_FAIL(0,"fail","注册失败!"),
    UPDATE_SUCCESS(1,"success","更新成功!"),
    UPDATE_FAIL(0, "success","更新失败!"),

    DEL_SUCCESS(1,"success","删除成功"),
    DEL_FAIL(1,"fail","删除失败"),
    PHONE_EXISTS(0,"fail","该手机号已经注册!"),
    USERNAME_EXISTS(0,"fail","该用户名已存在")
    ;
    private  int no;
    private String msg;
    private String desc;

    private ResultCodeInfoEnum(int no, String msg, String desc) {
        this.no = no;
        this.msg = msg;
        this.desc = desc;

    }
    private ResultCodeInfoEnum(int no, String msg) {
        this.no = no;
        this.msg = msg;

    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
