package com.cxy.customize.core.io;

/**
 * 行处理器
 */
public interface LineHandler {
    //处理一行数据，可以编辑后存入指定地方
    void handler(String line);
}
