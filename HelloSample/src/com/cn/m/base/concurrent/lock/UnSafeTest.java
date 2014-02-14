package com.cn.m.base.concurrent.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by macx on 13/2/14.
 */
public class UnSafeTest {

    private static int apple = 10;
    private int orange = 10;
    private static int byteArrayBaseOffset;


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Unsafe unsafe = getUnsafeInstance();
        AtomicInteger index = new AtomicInteger(1);
        Field appleField = UnSafeTest.class.getDeclaredField("apple");
        Field orangeField = UnSafeTest.class.getDeclaredField("orange");
        System.out.printf("Location of Apple:"+unsafe.staticFieldOffset(appleField)+"\n");
        System.out.printf("Location of Apple:"+unsafe.objectFieldOffset(orangeField)+"\n");

        byte[] data = new byte[10];
        System.out.println(Arrays.toString(data));
        unsafe.putByte(data,byteArrayBaseOffset,(byte)1);
        unsafe.putByte(data,byteArrayBaseOffset+5,(byte)5);
        System.out.println(Arrays.toString(data));

    }
    private static Unsafe getUnsafeInstance() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe)theUnsafeInstance.get(Unsafe.class);
    }

}
