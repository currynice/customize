//package com.cxy.customize.core.util;
//
//
//import java.io.ByteArrayInputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//
///**
// * 对象通用方法
// */
//public class ObjectUtil {
//    /**
//     * 序列化后拷贝流的方式  完成深克隆
//     * 对象必须实现Serializable接口
//     *
//     * @param <T> 对象类型
//     * @param obj 被克隆对象
//     * @return 克隆后的对象
//     * @throws UtilException IO异常和ClassNotFoundException封装
//     */
//    @SuppressWarnings("unchecked")
//    public static <T> T cloneByStream(T obj) {
//        if (null == obj || false == (obj instanceof Serializable)) {
//            return null;
//        }
//        final FastByteArrayOutputStream byteOut = new FastByteArrayOutputStream();
//        ObjectOutputStream out = null;
//        try {
//            out = new ObjectOutputStream(byteOut);
//            out.writeObject(obj);
//            out.flush();
//            final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray()));
//            return (T) in.readObject();
//        } catch (Exception e) {
//            throw new UtilException(e);
//        } finally {
//            IoUtil.close(out);
//        }
//    }
//}
