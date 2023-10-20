package com.wcx.dongbao.common.base.result;

import com.wcx.dongbao.common.base.enums.StateCodeEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class ResultWrapper<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    //把自己的builder返回出去，外界利用builder来向data里面填写信息
    public static ResultWrapper.ResultWrapperBuilder getSuccessBuilder(){
        return ResultWrapper.builder()
                .code(StateCodeEnum.SUCCESS.getCode())
                .msg(StateCodeEnum.SUCCESS.getMsg());
    }

    public static ResultWrapper.ResultWrapperBuilder getFailBuilder(){
        return ResultWrapper.builder()
                .code(StateCodeEnum.FAIL.getCode())
                .msg(StateCodeEnum.FAIL.getMsg());
    }

    public static ResultWrapper.ResultWrapperBuilder getBuilder(StateCodeEnum stateCodeEnum){
        return ResultWrapper.builder()
                .code(stateCodeEnum.getCode())
                .msg(stateCodeEnum.getMsg());
    }

    public static ResultWrapper getResultWrapper(StateCodeEnum stateCodeEnum){
        return ResultWrapper.builder()
                .code(stateCodeEnum.getCode())
                .msg(stateCodeEnum.getMsg()).build();
    }
}
