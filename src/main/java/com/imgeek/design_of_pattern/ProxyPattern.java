package com.imgeek.design_of_pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * author:  xiemin
 * date:    2017-07-14
 * desc:    代理模式
 */

interface IGamePlayer {
    void killBoss();
}

@Slf4j
class GamerPlayer implements IGamePlayer {
    private String _name;

    public GamerPlayer(String name) {
        this._name = name;
    }

    @Override
    public void killBoss() {
        log.info("kill Boss");
    }
}

@Slf4j
class GamerPlayerProxy implements IGamePlayer {
    private GamerPlayer gamerPlayer;

    public GamerPlayerProxy(String _name) {
        this.gamerPlayer = new GamerPlayer(_name);
    }

    @Override
    public void killBoss() {
        preHandler();
        gamerPlayer.killBoss();
        afterHandler();
    }

    public void preHandler() {
        log.info("preHandler");
    }

    public void afterHandler() {
        log.info("afterHandler");
    }
}


public class ProxyPattern {
}
