package com.cxy.customize.CompletionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Description:   </br>
 * Date: 2021/9/30 16:22
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class Examples<T> {

    void solve(Executor e,Collection<Callable<T>> solvers) throws InterruptedException, ExecutionException {
        CompletionService<T> ecs = new ExecutorCompletionService<>(e);
        for (Callable<T> s : solvers)
            ecs.submit(s);

        int n = solvers.size();

        for (int i = 0; i < n; ++i) {
            T r = ecs.take().get();
            if (r != null)
                use(r);
        }
    }




    void solve2(Executor e,Collection<Callable<T>> solvers) throws InterruptedException {
        CompletionService<T> ecs = new ExecutorCompletionService<>(e);
        int n = solvers.size();
        List<Future<T>> futures = new ArrayList<>(n);
        T result = null;
        try {
            for (Callable<T> s : solvers)
                futures.add(ecs.submit(s));

            for (int i = 0; i < n; ++i) {
                try {
                    T r = ecs.take().get();
                    if (r != null) {
                        result = r;
                        break;
                    }
                } catch (ExecutionException ignore) {}
            }
        }
        finally {
            for (Future<T> f : futures)
                // 注意这⾥的参数给的是 true，详解同样在前序 Future 源码分析⽂章中
                f.cancel(true);
        }
        if (result != null)
            use(result);
    }


    private void use(T r) {
        //todo 
    }

}
