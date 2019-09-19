package com.cxy.customize.core.util;

import com.cxy.customize.core.collection.EnumerationIter;
import com.cxy.customize.core.io.IOCustomException;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ResourceUtil {

    /**
     * 获取指定路径下的资源Iterator<br>
     * 路径格式必须为目录格式,用/分隔，例如:
     *
     * <pre>
     * config/a
     * spring/xml
     * </pre>
     *
     * @param resource 资源路径
     * @return 资源列表
     * @since 4.1.5
     */
    public static EnumerationIter<URL> getResourceIter(String resource) {
        final Enumeration<URL> resources;
        try {
            resources = ClassLoaderUtil.getClassLoader().getResources(resource);
        } catch (IOException e) {
            throw new IOCustomException(e);
        }
        return new EnumerationIter<>(resources);
    }
}
