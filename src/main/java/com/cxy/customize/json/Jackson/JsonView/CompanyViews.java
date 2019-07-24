package com.cxy.customize.json.Jackson.JsonView;

/**
 * Normal只能显示name age
 * Manager 可以显示全部
 */
public class CompanyViews {
    public class Normal{}
    public class Manager extends Normal{}
}
