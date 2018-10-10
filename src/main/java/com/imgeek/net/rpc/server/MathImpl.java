package com.imgeek.net.rpc.server;

import com.imgeek.net.rpc.IMath;

/**
 * @author: peterxiemin
 *
 * @date: 2018/10/10 12:48
 * @desc:
 **/
public class MathImpl implements IMath {
    @Override
    public int add(int o1, int o2) {
        return o1 + o2;
    }
}
