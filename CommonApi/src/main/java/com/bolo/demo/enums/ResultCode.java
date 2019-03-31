package com.bolo.demo.enums;

/**
 * 结果码枚举
 */
public enum ResultCode {
    succeed("0000", "操作成功"),
    fail("00001", "操作失败");


    /**
     * 代码
     */
    private String code;

    /**
     * 说明
     */
    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
