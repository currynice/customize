package com.cxy.customize.json.Jackson.JsonView;

/**
 * Normal只能显示name age
 * Manager 可以显示全部
 * hr 可以看name age skills
 */
public class CompanyViews {
    public class Normal{}
    public class Manager extends Normal{}
    public class Hr extends Normal{}
}
