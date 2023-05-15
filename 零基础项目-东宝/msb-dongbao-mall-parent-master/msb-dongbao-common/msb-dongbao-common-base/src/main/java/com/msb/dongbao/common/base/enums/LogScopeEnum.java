package com.msb.dongbao.common.base.enums;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @date: 2020年05月29日 15时40分
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public enum LogScopeEnum {

    ALL(0), REQUEST(1), RESPONSE(2);

    private final int value;

    LogScopeEnum(int value) {
        this.value = value;
    }
}
