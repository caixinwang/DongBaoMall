package com.wcx.dongbao.common.base.enums;

//定义code的时候，要分区，例如100~199是用户业务；200~299是支付业务。这里是举个例子
public enum StateCodeEnum {//只有state以及对应的code
    REGISTER_INVALID_MGS(101,"注册信息非法"),
    REGISTER_USERNAME_EXIST(102,"用户名重复"),
    REGISTER_SUCCESS(103,"用户创建成功"),
    LOGIN_FAIL(104,"用户名或者密码错误"),
    LOGIN_SUCCESS(105,"登录成功"),
    EDIT_USERNAME_EXIST(106,"用户名重复"),
    EDIT_SUCCESS(107,"修改成功"),
    EDIT_INVALID_TOKEN(108,"token失效"),
    EDIT_USER_NOT_EXIST(109,"要修改的用户不存在"),
    EDIT_AUTH_REJECT(110,"没有修改权限"),
    INVALID_TOKEN(150,"token无效"),
    EMPTY_TOKEN(151,"token无效"),
    TOKEN_REFRESHED(152,"token刷新成功"),
    INVALID_SIGN(153,"sign校验不通过"),


    SUCCESS(200,"请求成功"),

    ILLEGAL_PARAM(401,"参数非法"),
    FAIL(500,"请求失败"),

    ;

    private int code;
    private String msg;

    StateCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    StateCodeEnum() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
