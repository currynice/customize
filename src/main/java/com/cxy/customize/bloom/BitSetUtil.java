package com.cxy.customize.bloom;
import com.googlecode.javaewah.EWAHCompressedBitmap;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * 多线程要读写加锁哦,
 * 稀疏数据可以使用  EWAHCompressedBitmap
 */
public class BitSetUtil {

        /**
         * 求一个字符串包含的char,去重
         *
         */
        public static List<Character> containChars(String str) {
            BitSet used = new BitSet();
            for (int i = 0; i < str.length(); i++){
                used.set(str.charAt(i));
        }
            int size = used.size();
            List<Character> characters = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (used.get(i)) {
                    characters.add((char) i);
                }
            }
            return characters;
        }



        /**
         * 进行数字排序
         */
        public static int[] sortArray(int[] array) {
//            int[] array = new int[] { 423, 700, 9999, 2323, 356, 6400, 1,2,3,2,2,2,2 };
            BitSet bitSet = new BitSet(2 << 13);
            // 虽然可以自动扩容，但尽量在构造时指定估算大小(default 64)
            for (int i = 0; i < array.length; i++) {
                bitSet.set(array[i]);
            }
            //剔除重复数字后的元素个数
            int bitLen=bitSet.cardinality();

            //进行排序，即把bit为true的元素复制到另一个数组
            int[] orderedArray = new int[bitLen];
            int k = 0;
            for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
                orderedArray[k++] = i;
            }

//            for (int i = 0; i < bitLen; i++) {
//
//            }
            return orderedArray;
        }

        /**
         * 将BitSet对象转化为ByteArray
         * @param bitSet
         * @return
         */
        public static byte[] bitSet2ByteArray(BitSet bitSet) {
            // 1byte = 8bit
            byte[] bytes = new byte[bitSet.size() / 8];
            for (int i = 0; i < bitSet.size(); i++) {
                int index = i / 8;
                int offset = 7 - i % 8;
                bytes[index] |= (bitSet.get(i) ? 1 : 0) << offset;
            }
            return bytes;
        }

        /**
         * 将ByteArray对象转化为BitSet
         * @param bytes
         * @return
         */
        public static BitSet byteArray2BitSet(byte[] bytes) {
            BitSet bitSet = new BitSet(bytes.length * 8);
            int index = 0;
            for (int i = 0; i < bytes.length; i++) {
                for (int j = 7; j >= 0; j--) {
                    bitSet.set(index++, (bytes[i] & (1 << j)) >> j == 1);
                }
            }
            return bitSet;
        }

        /**
         * 简单使用示例
         */
        public static void simpleExample() {
            String names[] = { "Java", "Source", "and", "Support" };
            BitSet bits = new BitSet();
            for (int i = 0, n = names.length; i < n; i++) {
                if ((names[i].length() % 2) == 0) {
                    bits.set(i);
                }
            }

            System.out.println(bits);
            System.out.println("Size : " + bits.size());
            System.out.println("Length: " + bits.length());
            for (int i = 0, n = names.length; i < n; i++) {
                if (!bits.get(i)) {
                    System.out.println(names[i] + " is odd");
                }
            }
            BitSet bites = new BitSet();
            bites.set(0);
            bites.set(1);
            bites.set(2);
            bites.set(3);
            bites.andNot(bits);
            System.out.println(bites);
        }

        public static void main(String args[]) {
            //BitSet使用示例
        //    System.out.println(BitSetUtil.containChars("How do you do? 你好呀"));


                System.out.println(Arrays.toString(BitSetUtil.sortArray(new int[] { 423, 700, 9999, 2323, 356, 6400, 1,2,3,2,2,2,2 })));



            //BitSet与Byte数组互转示例
//            BitSet bitSet = new BitSet();
//            bitSet.set(3, true);
//            bitSet.set(98, true);
//            System.out.println(bitSet.size()+","+bitSet.cardinality());
//            //将BitSet对象转成byte数组
//            byte[] bytes = BitSetUtil.bitSet2ByteArray(bitSet);
//            System.out.println(Arrays.toString(bytes));
//
//            //在将byte数组转回来
//            bitSet = BitSetUtil.byteArray2BitSet(bytes);
//            System.out.println(bitSet.size()+","+bitSet.cardinality());
//            System.out.println(bitSet.get(3));
//            System.out.println(bitSet.get(98));
//            for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
//                System.out.print(i+"\t");
//            }

            //            BitSetUtil.simpleExample();


            EWAHCompressedBitmap delayOptPut = new EWAHCompressedBitmap();

//            BitSet delayOptPut = new BitSet();
            delayOptPut.set(1);
            delayOptPut.set(100000000);
            File file = new File("d:/cxy/10.9.txt");
            try(
                    //对象输出流
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    //对象输入流
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ){
                oos.writeObject(delayOptPut);
                EWAHCompressedBitmap bitSet = (EWAHCompressedBitmap) ois.readObject();
                System.out.println(bitSet.get(1));
                System.out.println(bitSet.get(100000000));
            }catch (IOException|ClassNotFoundException  e){
                e.printStackTrace();
            }


        }
    }
