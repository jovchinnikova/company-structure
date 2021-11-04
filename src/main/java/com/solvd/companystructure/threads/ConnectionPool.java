package com.solvd.companystructure.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConnectionPool implements Runnable{

    private volatile static ConnectionPool instance;

    private static List<Connection> connections;
    private static List<Connection> usedConnections = new ArrayList<>();
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

    public Connection getConnection(){
        Connection connection = connections
                .remove(connections.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public void releaseConnection(Connection connection){
        connections.add(connection);
        usedConnections.remove(connection);
    }

    @Override
    public void run(){
        Connection connection = this.getConnection();
        connection.create();
        connection.read();
        connection.update();
        connection.print();
        this.releaseConnection(connection);
    }
}
