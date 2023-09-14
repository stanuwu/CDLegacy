package com.stanuwu.cdlegacy.db;

import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DB {
    private final Logger logger;
    private final Connection connection;
    private final Map<String, String> queries = new HashMap<>();

    public DB() {
        this.logger = new SimpleLoggerFactory().getLogger("Database");
        logger.info("READING DB INFO");
        List<String> info;
        try {
            info = Files.readAllLines(Paths.get("data/db.txt"));
        } catch (IOException e) {
            logger.error("UNABLE TO READ DB INFO");
            throw new DBInfoException();
        }
        if (info.size() < 5) throw new DBInfoException();

        logger.info("CONNECTING TO DB");
        try {
            String url = "jdbc:postgresql://%s:%s/%s".formatted(info.get(0), info.get(1), info.get(4));
            Properties props = new Properties();
            props.setProperty("user", info.get(2));
            props.setProperty("password", info.get(3));
            this.connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            logger.error("UNABLE TO CONNECT TO DATABASE");
            throw new SQLRuntimeException(e);
        }
        logger.info("CONNECTED TO DB " + info.get(0) + ":" + info.get(1) + "@" + info.get(4));
    }

    public String getQuery(String file) {
        return queries.computeIfAbsent(file, f -> {
            try (InputStream stream = this.getClass().getResourceAsStream("/db/" + file)) {
                if (stream == null) return "";
                return new String(stream.readAllBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private ResultSet getDBFirst(String query) throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet result = statement.executeQuery(getQuery(query));
        result.next();
        return result;
    }

    public String firstAsString(String query) {
        try {
            return getDBFirst(query).getString(1);
        } catch (SQLException e) {
            logger.error("QUERY " + query + " FAILED");
            throw new SQLRuntimeException(e);
        }
    }

    public String test() {
        return firstAsString("test.sql");
    }
}
