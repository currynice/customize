package com.cxy.customize.NIO.Channle;

import com.cxy.customize.core.io.FileUtil;
import com.cxy.customize.core.io.file.FileModeEnum;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class ChannelTest {

    public static void main(String args[]){
//        testTeansferFrom();
//        testTeansferto();

        //test SelectionKey
//        int interSet = SelectionKey.OP_ACCEPT | SelectionKey.OP_CONNECT;
//        testSelectionKey(interSet);
        testSelector();
    }


    private static void testChannel(){

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



    private static void testTeansferFrom(){
        File file1 = new File("d:\\cxy\\from.txt");
        File file2 = new File("d:\\cxy\\to.txt");
        try(
                RandomAccessFile fromFile = FileUtil.createRandomAccessFile(file1, FileModeEnum.rw);
                FileChannel fromChannel = fromFile.getChannel();
                RandomAccessFile toFile = FileUtil.createRandomAccessFile(file2, FileModeEnum.rw);
                FileChannel toChannel = toFile.getChannel();
        ){
            //position:从position处开始写,最多传输count字节数
            toChannel.transferFrom(fromChannel,0,fromChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void testTeansferto(){
        File file1 = new File("d:\\cxy\\from.txt");
        File file2 = new File("d:\\cxy\\to.txt");
        try(
                RandomAccessFile fromFile = FileUtil.createRandomAccessFile(file1, FileModeEnum.rw);
                FileChannel fromChannel = fromFile.getChannel();
                RandomAccessFile toFile = FileUtil.createRandomAccessFile(file2, FileModeEnum.rw);
                FileChannel toChannel = toFile.getChannel();
        ){
            //position:从position处开始写,最多传输count字节数
            fromChannel.transferTo(0,fromChannel.size(),toChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //  int interestSet = SelectionKey.OP_ACCEPT | SelectionKey.OP_CONNECT;
    private static void testSelectionKey(int interestSet) {


        boolean isInterestInAccept = (interestSet & SelectionKey.OP_ACCEPT)==SelectionKey.OP_ACCEPT ;

        boolean isInterestInCONNECT = (interestSet & SelectionKey.OP_CONNECT)==SelectionKey.OP_CONNECT ;

        boolean isInterestInRead = (interestSet & SelectionKey.OP_READ)==SelectionKey.OP_READ ;

        boolean isInterestInWrite = (interestSet & SelectionKey.OP_WRITE)==SelectionKey.OP_WRITE ;


        System.out.println(isInterestInAccept);

        System.out.println(isInterestInCONNECT);

        System.out.println(isInterestInRead);
        System.out.println(isInterestInWrite);


    }

    /**
     * 打开一个Selector，注册一个通道注册到这个Selector上(通道的初始化过程略去),然后持续监控这个Selector的四种事件（接受，连接，读，写）是否就绪
     */
    private static void testSelector() {
        try(Selector selector = Selector.open();) {
            SocketChannel socketChannel =  SocketChannel.open();
            //设置非阻塞模式
            socketChannel.configureBlocking(false);
            //将socketChannel注册到selector上,对READ事件感兴趣
            SelectionKey key1 = socketChannel.register(selector,SelectionKey.OP_READ,"socketChannel");
            System.out.println(key1.attachment());

            while(true) {

                int readyChannels = selector.selectNow();

                if(readyChannels == 0) continue;


                Set<SelectionKey> selectedKeys = selector.selectedKeys();

                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while(keyIterator.hasNext()) {

                    SelectionKey key = keyIterator.next();

                    if(key.isAcceptable()) {
                        // a connection was accepted by a ServerSocketChannel.

                    } else if (key.isConnectable()) {
                        // a connection was established with a remote server.

                    } else if (key.isReadable()) {
                        // a channel is ready for reading

                    } else if (key.isWritable()) {
                        // a channel is ready for writing
                    }

                    keyIterator.remove();
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }



    }



}
