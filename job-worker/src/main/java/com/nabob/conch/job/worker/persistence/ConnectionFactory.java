package com.nabob.conch.job.worker.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接管理
 *
 * @author Adam
 * @date 2021/2/18
 */
public class ConnectionFactory {

    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    private static DataSource getDataSource() {
        if (dataSource != null) {
            return dataSource;
        }
        synchronized (ConnectionFactory.class) {
            if (dataSource == null) {
                HikariConfig config = new HikariConfig();
                config.setDriverClassName("org.h2.Driver");
                config.setJdbcUrl("jdbc:h2:file:~/.h2/oms/oms_worker_db");
                config.setAutoCommit(true);
                // 池中最小空闲连接数量
                config.setMinimumIdle(2);
                // 池中最大连接数量
                config.setMaximumPoolSize(16);
                dataSource = new HikariDataSource(config);
            }
        }
        return dataSource;
    }
}
