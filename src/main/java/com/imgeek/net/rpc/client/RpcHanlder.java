package com.imgeek.net.rpc.client;

import com.imgeek.ioc.DynamicProxy;
import com.imgeek.net.ITcpClient;
import com.imgeek.net.ITcpConmunicateToServerHandler;
import com.imgeek.net.TcpClient;
import com.imgeek.net.rpc.ProtocolFormat;
import com.imgeek.net.rpc.IMath;
import com.imgeek.net.rpc.server.MathImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author: peterxiemin
 * @date: 2018/10/10 11:32
 * @desc:
 **/

class TcpConmunicateToServerHandlerImpl implements ITcpConmunicateToServerHandler {
    private Object result;
    private Object target;
    private Method method;
    private Object[] args;

    public TcpConmunicateToServerHandlerImpl(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    @Override
    public void handle(Socket socket) throws IOException {
        ProtocolFormat protocolFormatOut = new ProtocolFormat();
        protocolFormatOut.setClassName(target.getClass().getName());
        protocolFormatOut.setMethodName(method.getName());
        protocolFormatOut.setObjects(args);
        protocolFormatOut.setTypes(method.getParameterTypes());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(protocolFormatOut);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        try {
            result = in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object getResult() {
        return result;
    }
}

public class RpcHanlder implements InvocationHandler {
    private Object target;

    public RpcHanlder(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TcpConmunicateToServerHandlerImpl tcpConmunicateToServerHandler = new TcpConmunicateToServerHandlerImpl(target, method, args);
        ITcpClient iTcpClient = new TcpClient("localhost", 5763, tcpConmunicateToServerHandler);
        iTcpClient.sendToServer();
        return tcpConmunicateToServerHandler.getResult();
    }

    public static void main(String[] args) {
        IMath iMath = new MathImpl();
        IMath iEchoHello = (IMath) DynamicProxy.jdkProxyCreate(iMath, new RpcHanlder(iMath));
        System.out.println(iEchoHello.add(1, 2));
    }
}
