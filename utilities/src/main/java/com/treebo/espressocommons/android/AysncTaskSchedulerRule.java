package com.treebo.espressocommons.android;

import android.os.AsyncTask;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by kishorepolisetty on 16/02/17.
 *
 * AysncTaskSchedulerRule is a Test Rule which waits until screen loads completely.
 *
 **/

public class AysncTaskSchedulerRule implements TestRule{

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
                    @Override
                    public Scheduler apply(Scheduler scheduler) throws Exception {
                        return Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                });

                try {
                    base.evaluate();
                }finally{
                    RxJavaPlugins.reset();
                }
            };


        };
    }
}
