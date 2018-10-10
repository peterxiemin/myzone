package com.imgeek.net.rpc.server;

import com.imgeek.net.ITcpConmunicateToClientHandler;
import com.imgeek.net.TcpServer;
import com.imgeek.net.rpc.ProtocolFormat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: peterxiemin
 * @date: 2018/10/10 11:33
 * @desc:
 **/
public class RpcServer {
    private static ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        (new TcpServer("localhost", 5763, new ITcpConmunicateToClientHandler() {
            @Override
            public void handle(Socket socket) throws IOException {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ProtocolFormat protocolFormat = null;
                try {
                    protocolFormat = (ProtocolFormat) in.readObject();

                    String className = protocolFormat.getClassName();
                    String methodName = protocolFormat.getMethodName();
                    Class<?>[] parameterTypes = protocolFormat.getTypes();
                    Object[] args = protocolFormat.getObjects();

                    Object objCls = null;
                    if (concurrentHashMap.get(className) == null) {
                        objCls = Class.forName(className).newInstance();
                        concurrentHashMap.put(className, objCls);
                    } else {
                        objCls = concurrentHashMap.get(className);
                    }

                    Method method = objCls.getClass().getMethod(methodName, parameterTypes);
                    Object result = method.invoke(objCls, args);
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject(result);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        })).createServerSocketAndWaitConnection();
    }
}
