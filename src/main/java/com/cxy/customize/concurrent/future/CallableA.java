package com.cxy.customize.concurrent.future;

import java.util.concurrent.Callable;

/**
 * Description:   </br>
 * Date: 2021/9/29 16:07
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class CallableA implements Callable<String> {
    @Override
    public String call() throws Exception {
        // Perform something
        return "done";
    }
}
