package com.demkom58.ids_lab_2.engine;

import com.demkom58.ids_lab_2.compute.Compute;
import com.demkom58.ids_lab_2.compute.task.Task;
import com.demkom58.ids_lab_2.compute.util.StringUtil;

import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngine extends UnicastRemoteObject implements Compute {
    private static final String SERVICE_NAME = "rmi://localhost/Compute";

    public static void main(String[] args) throws Exception {
        final URL policyUrl = ComputeEngine.class.getResource("/rmi.policy");
        System.setProperty("java.security.policy", StringUtil.toPathString(policyUrl));

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        Naming.rebind(SERVICE_NAME, new ComputeEngine());
        System.out.println("ComputeEngine bound");
    }

    protected ComputeEngine() throws RemoteException {
        super();
    }

    @Override
    public Object executeTask(Task task) throws RemoteException {
        return task.execute();
    }

}