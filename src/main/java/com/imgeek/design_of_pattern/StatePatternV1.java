package com.imgeek.design_of_pattern;

public class StatePatternV1 {
    public static void main(String[] args) {
        Lift lift = new Lift();
        lift.setState(Lift.STOP_STATE);
        lift.open();
        lift.close();
        lift.run();
        lift.stop();
    }
}

interface ILift {
    void open();

    void close();

    void run();

    void stop();
}

class Lift implements ILift {
    public static final int OPEN_STATE = 0;
    public static final int CLOSE_STATE = 1;
    public static final int RUN_STATE = 2;
    public static final int STOP_STATE = 3;

    private int state;

    @Override
    public void open() {
        switch (state) {
            case OPEN_STATE:
                break;
            case CLOSE_STATE:
                System.out.println("开门");
                setState(OPEN_STATE);
                break;
            case RUN_STATE:
                break;
            case STOP_STATE:
                System.out.println("开门");
                setState(OPEN_STATE);
                break;
            default:
                ;
                ;
        }
    }

    @Override
    public void close() {
        switch (state) {
            case OPEN_STATE:
                System.out.println("关门");
                setState(CLOSE_STATE);
                break;
            case CLOSE_STATE:
                break;
            case RUN_STATE:
                break;
            case STOP_STATE:
                break;
            default:
                ;
                ;
        }
    }

    @Override
    public void run() {
        switch (state) {
            case OPEN_STATE:
                break;
            case CLOSE_STATE:
                System.out.println("运行");
                setState(RUN_STATE);
                break;
            case RUN_STATE:
                break;
            case STOP_STATE:
                System.out.println("运行");
                setState(RUN_STATE);
                break;
            default:
                ;
                ;
        }
    }


    @Override
    public void stop() {
        switch (state) {
            case OPEN_STATE:
                break;
            case CLOSE_STATE:
                System.out.println("停止");
                setState(STOP_STATE);
                break;
            case RUN_STATE:
                System.out.println("停止");
                setState(STOP_STATE);
                break;
            case STOP_STATE:
                break;
            default:
                ;
                ;
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}