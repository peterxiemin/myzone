package com.imgeek.design_of_pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * auth: xiemin
 * date: 2018-08-04
 * desc: 装饰者模式
 */

abstract class Component {
    public abstract void operation();
}

@Slf4j
class ConcreateComponent extends Component {

    @Override
    public void operation() {
        log.info("hello world");
    }
}

abstract class Decorator extends Component {
    protected Component _component;
    public Decorator(Component component) {
        this._component = component;
    }
}

@Slf4j
class ConcreateDecorator extends Decorator {

    public ConcreateDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        log.info("I am a Concreate");
        _component.operation();
    }
}


public class DecoratorPattern {
}
