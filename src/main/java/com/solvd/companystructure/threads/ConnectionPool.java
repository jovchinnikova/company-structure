package com.solvd.companystructure.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool {

    private volatile static ConnectionPool instance;

    private volatile static  List<Connection> connections;
    private volatile static  List<Connection> runningConnections = new ArrayList<>();
    private static Integer maxConnections;

    private ConnectionPool(Integer maxConnections) {
    }

    public static ConnectionPool getInstance(Integer maxConnections) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool(maxConnections);
                    ConnectionPool.maxConnections = maxConnections;
                    List<Connection> connections = new ArrayList<>();
                    IntStream.range(0,maxConnections)
                            .forEach(index -> connections.add(new Connection("Connection" + index)));
                    ConnectionPool.connections = connections;
                }
            }
        }
        return instance;
    }

    public synchronized Connection getConnection(){
        Connection connection = connections
                .remove(connections.size() - 1);
        runningConnections.add(connection);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection){
        connections.add(connection);
        runningConnections.remove(connection);
    }
}
