package com.cxy.customize.NIO.Channle;

import com.cxy.customize.core.io.FileUtil;
import com.cxy.customize.core.io.file.FileModeEnum;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {

    public static void main(String args[]){


        File file = new File("d:\\cxy\\nio-data.txt");
           // RandomAccessFile:
           // 1.对文件内容（读操作和写操作）的访问
            // 2.支持随机访问文件，以及访问文件的任意位置
            // 硬盘存储上文件存储的方式为byte数据的集合
            // 打开方式 rw(读写) 和 r(只读)
        try(
                RandomAccessFile accessFile = FileUtil.createRandomAccessFile(file, FileModeEnum.rw);
                FileChannel inChannel = accessFile.getChannel();
                ){
            //创建一个Buffer
            ByteBuffer buf = ByteBuffer.allocate(48);//capacity=48bytes
            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1){
                System.out.println("Read " + bytesRead);
               buf.flip();//反转buffer,再从缓冲里读取数据 Q:为什么要反转? A:容量48，当前34，buf.hasRemaining()是false的
                while (buf.hasRemaining()){
                    System.out.println((char) buf.get());
                }
                buf.clear();
                bytesRead = inChannel.read(buf);
            }


        }catch (IOException e){

        }
    }
}
