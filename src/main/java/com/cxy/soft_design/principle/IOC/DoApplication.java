package com.cxy.soft_design.principle.IOC;

import com.cxy.soft_design.principle.IOC.cases.TestCaseImple1;
import com.cxy.soft_design.principle.IOC.cases.TestCaseImple2;

import java.util.ArrayList;
import java.util.List;

public class DoApplication {
        private static final List<TestCase> testCases = new ArrayList<>();

        public static void register(TestCase testCase) {
            testCases.add(testCase);
        }

        public static final void main(String[] args) {
            // todo 注册操作通过配置的方式来实现，而不是显示调用register()
            DoApplication.register(new TestCaseImple1());

            DoApplication.register(new TestCaseImple2());
            for (TestCase testCase: testCases) {
                testCase.run();
            }
        }
    }
