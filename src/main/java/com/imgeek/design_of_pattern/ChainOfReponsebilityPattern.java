package com.imgeek.design_of_pattern;

import lombok.extern.slf4j.Slf4j;

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

@Slf4j
class Husband extends IHandler {

    @Override
    public void handleMessage() {
        log.info("I am a husband");
        if (_iHandlerNext != null) {
            _iHandlerNext.handleMessage();
        }
    }
}

@Slf4j
class Father extends IHandler {
    @Override
    public void handleMessage() {
        log.info("I am a Father");
        if (_iHandlerNext != null) {
            _iHandlerNext.handleMessage();
        }
    }
}

@Slf4j
class Son extends IHandler {
    @Override
    public void handleMessage() {
        log.info("I am a Son");
        if (_iHandlerNext != null) {
            _iHandlerNext.handleMessage();
        }
    }
}

public class ChainOfReponsebilityPattern {
}
