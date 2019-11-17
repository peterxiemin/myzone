package com.imgeek.design_of_pattern;

public class StatePatternV2 {
    public static void main(String[] args) {
        Context context = new Context();
        context.setAbstractLiftState(Context.STOP_LIFT_STATE);
        context.open();
        context.close();
        context.run();
        context.stop();
    }
}

class Context {

    public static final OpenLiftState OPEN_LIFT_STATE = new OpenLiftState();
    public static final CloseLiftState CLOSE_LIFT_STATE = new CloseLiftState();
    public static final RunLiftState RUN_LIFT_STATE = new RunLiftState();
    public static final StopLiftState STOP_LIFT_STATE = new StopLiftState();

    private AbstractLiftState abstractLiftState;

    public void setAbstractLiftState(AbstractLiftState abstractLiftState) {
        this.abstractLiftState = abstractLiftState;
        this.abstractLiftState.setContext(this);
    }

    public void open() {
        abstractLiftState.open();
    }

    public void close() {
        abstractLiftState.close();
    }

    public void run() {
        abstractLiftState.run();
    }

    public void stop() {
        abstractLiftState.stop();
    }
}

abstract class AbstractLiftState {

    protected Context context;

    public abstract void open();

    public abstract void close();

    public abstract void run();

    public abstract void stop();

    public void setContext(Context context) {
        this.context = context;
    }
}

class OpenLiftState extends AbstractLiftState {

    @Override
    public void open() {
        System.out.println("开启");
    }

    @Override
    public void close() {
        context.setAbstractLiftState(Context.CLOSE_LIFT_STATE);
        context.close();
    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {

    }
}

class CloseLiftState extends AbstractLiftState {

    @Override
    public void open() {
        context.setAbstractLiftState(Context.OPEN_LIFT_STATE);
        context.open();
    }

    @Override
    public void close() {
        System.out.println("关闭");
    }

    @Override
    public void run() {
        context.setAbstractLiftState(Context.RUN_LIFT_STATE);
        context.run();
    }

    @Override
    public void stop() {
        context.setAbstractLiftState(Context.STOP_LIFT_STATE);
        context.stop();
    }
}

class RunLiftState extends AbstractLiftState {

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public void run() {
        System.out.println("运行");
    }

    @Override
    public void stop() {
        context.setAbstractLiftState(Context.STOP_LIFT_STATE);
        context.stop();
    }
}

class StopLiftState extends AbstractLiftState {

    @Override
    public void open() {
        context.setAbstractLiftState(Context.OPEN_LIFT_STATE);
        context.open();
    }

    @Override
    public void close() {

    }

    @Override
    public void run() {
        context.setAbstractLiftState(Context.RUN_LIFT_STATE);
        context.open();
    }

    @Override
    public void stop() {
        System.out.println("停止");
    }
}