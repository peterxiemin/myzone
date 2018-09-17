package com.imgeek.design_of_pattern;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * author:  xiemin
 * date:    2018-07-28
 * desc:    观察者模式
 */

interface Observerable {
    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObservers(String context);
}

interface Observer {
   void update(String context);
}

@Slf4j
class Lisi implements Observer{

    @Override
    public void update(String context) {
        log.info("lisi 观察到：".concat(context));
    }
}

interface IHanFeizi {
    void haveBreakfast();
    void haveFun();
}

class HanFeizi implements Observerable, IHanFeizi {
    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void register(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(String context) {
        for (Observer observer : observerList) {
            observer.update(context);
        }
    }

    @Override
    public void haveBreakfast() {
        String msg = "韩非子吃早饭";
        this.notifyObservers(msg);
    }

    @Override
    public void haveFun() {
        String msg = "韩非子在娱乐";
        this.notifyObservers(msg);
    }
}

public class ObserverPattern {
}
