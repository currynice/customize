package com.cxy.soft_design.principle.IOC;

public abstract class TestCase {
    public void run() {
        if (doTest()) {
            System.out.println(" succeed.");
        } else {
            System.out.println(" failed.");
        }
    }
    public abstract boolean doTest();
}