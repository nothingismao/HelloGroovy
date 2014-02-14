package com.cn.m.db

import java.sql.Connection
import java.sql.DriverManager
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by macx on 7/2/14.
 */
class DBConnectionPool {
    DBConfigBean dbConfigBean
    List<Connection> allConnections = new LinkedList<Connection>();
    BlockingQueue<Connection> freeConnection = new LinkedBlockingQueue<Connection>()
    AtomicInteger totalConnections = new AtomicInteger(0);
    AtomicInteger freeConnections = new AtomicInteger(0);

    public DBConnectionPool(DBConfigBean bean) {
        this.dbConfigBean = bean;
        this.initalize();
    }

    private initalize() {
        Class.forName(this.dbConfigBean.deiverClass);
        for (def i in 1..this.dbConfigBean.minConn) {
            Connection connection = DriverManager.getConnection(dbConfigBean.url, dbConfigBean.user, dbConfigBean.password);
//            DBConnection dbConnection = new Connection(connection)
            allConnections.add(connection)
            freeConnection.put(connection)
            totalConnections.incrementAndGet();
            freeConnections.incrementAndGet();
        }
    }

    public Connection getConnection() {


        Connection connection = freeConnection.poll()

        if (conection == null) {
            //如果当前已分配的连接数等于最大连接数调用take等待下一个空闲的连接
            if (totalConnections == this.dbConfigBean.maxConn) {
                freeConnection.take()
            } else {
                connection = addConnection();
            }
        }
        freeConnections.decrementAndGet();
        return conection;
    }

    private Connection addConnection() {
        Connection connection = DriverManager.getConnection(dbConfigBean.url, dbConfigBean.user, dbConfigBean.password);
        totalConnections.incrementAndGet();
        freeConnections.incrementAndGet();
        return connection
    }

    public boolean freeConnection(Connection connection) {
        freeConnections.incrementAndGet();
        freeConnection.put(connection);
    }
}
