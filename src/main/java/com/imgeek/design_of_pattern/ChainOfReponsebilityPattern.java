package com.imgeek.design_of_pattern;

/**
 * autor:   xiemin
 * date:    2018-07-16
 * desc:    责任链模式
 */

interface IWomen {

}

class Women implements IWomen {
    private IHandler _iHandler;

    public void addHandler(IHandler iHandler) {
        this._iHandler = iHandler;
    }

    public void chainHandle() {
        _iHandler.handleMessage();
    }
}

abstract class IHandler {
    protected IHandler _iHandlerNext;

    abstract public void handleMessage();

    public IHandler getIHandlerNext() {
        return _iHandlerNext;
    }

    public void setIHandlerNext(IHandler _iHandlerNext) {
        this._iHandlerNext = _iHandlerNext;
    }
}

class Husband extends IHandler {

    @Override
    public void handleMessage() {
        System.out.println("I am a husband");
        if (_iHandlerNext != null) {
            _iHandlerNext.handleMessage();
        }
    }
}

class Father extends IHandler {
    @Override
    public void handleMessage() {
        System.out.println("I am a Father");
        if (_iHandlerNext != null) {
            _iHandlerNext.handleMessage();
        }
    }
}

class Son extends IHandler {
    @Override
    public void handleMessage() {
        System.out.println("I am a Son");
        if (_iHandlerNext != null) {
            _iHandlerNext.handleMessage();
        }
    }
}

public class ChainOfReponsebilityPattern {
    public static void main(String[] args) {
        Husband husband = new Husband();
        Father father = new Father();
        Son son = new Son();
        husband.setIHandlerNext(father);
        father.setIHandlerNext(son);

        Women women = new Women();
        women.addHandler(husband);
        women.chainHandle();
    }
}
