package com.bolo.demo.entity.resp;

import com.bolo.demo.enums.ResultCode;

/**
 * 响应类基类
 */
public abstract class BaseResp {
    /**
     * 实例id
     */
    protected String instanceId;

    /**
     * 结果码
     */
    protected String code;

    /**
     * 说明信息
     */
    protected String msg;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 设置成功响应
     */
    public void setSucceed() {
        this.code = ResultCode.succeed.getCode();
        this.msg = ResultCode.succeed.getMsg();
    }

    /**
     * 设置失败响应
     *
     * @param code
     * @param depict
     */
    public void setFail(ResultCode code, String depict) {
        this.code = code.getCode();
        StringBuilder msg = new StringBuilder(code.getMsg());
        if (depict != null && !"".equals(depict)) {
            msg.append(',').append(depict);
        }
        this.msg = msg.toString();
    }
}
