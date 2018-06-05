package com.yd.dubbo.callback.implicit;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class NotifyImpl implements Notify {
    public Map<Integer, Person> ret = new HashMap<Integer, Person>();
    public Map<Integer, Throwable> errors = new HashMap<Integer, Throwable>();
    Random random = new Random();

    public void onreturn(Person msg, Integer id) {
        System.out.println("onreturn:" + msg);
        if (random.nextBoolean() == true) {
            throw new RuntimeException("you has error");
        }
        ret.put(id, msg);
    }

    public void onthrow(Throwable ex, Integer id) {
        errors.put(id, ex);
    }
}