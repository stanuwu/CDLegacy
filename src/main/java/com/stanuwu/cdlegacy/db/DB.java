package com.stanuwu.cdlegacy.db;

import com.stanuwu.cdlegacy.game.content.Item;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBInv;
import com.stanuwu.cdlegacy.game.data.DBUser;
import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class DB {
    private final Logger logger;
    private final DriverManagerDataSource dataSource;
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
        String url = "jdbc:postgresql://%s:%s/%s".formatted(info.get(0), info.get(1), info.get(4));
        Properties props = new Properties();
        props.setProperty("user", info.get(2));
        props.setProperty("password", info.get(3));
        this.dataSource = new DriverManagerDataSource(url, props);
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
        Statement statement = dataSource.getConnection().createStatement();
        ResultSet result = statement.executeQuery(getQuery(query));
        result.next();
        return result;
    }

    private void queryError(String query) {
        logger.error("QUERY " + query + " FAILED");
    }

    public String firstAsString(String query) {
        try {
            return getDBFirst(query).getString(1);
        } catch (SQLException e) {
            queryError(query);
            throw new SQLRuntimeException(e);
        }
    }

    public int firstAsInt(String query) {
        try {
            return getDBFirst(query).getInt(1);
        } catch (SQLException e) {
            queryError(query);
            throw new SQLRuntimeException(e);
        }
    }

    public long firstAsLong(String query) {
        try {
            return getDBFirst(query).getLong(1);
        } catch (SQLException e) {
            queryError(query);
            throw new SQLRuntimeException(e);
        }
    }

    public boolean firstAsBool(String query) {
        try {
            return getDBFirst(query).getBoolean(1);
        } catch (SQLException e) {
            queryError(query);
            throw new SQLRuntimeException(e);
        }
    }

    public double firstAsDouble(String query) {
        try {
            return getDBFirst(query).getDouble(1);
        } catch (SQLException e) {
            queryError(query);
            throw new SQLRuntimeException(e);
        }
    }

    public float firstAsFloat(String query) {
        try {
            return getDBFirst(query).getFloat(1);
        } catch (SQLException e) {
            queryError(query);
            throw new SQLRuntimeException(e);
        }
    }

    public List<DBUser> loadUsers() {
        String query = "load-users.sql";
        try {
            Map<Long, List<DBInv.Entry>> invs = loadItems();
            List<DBUser> users = new ArrayList<>();
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getQuery(query));
            while (result.next()) {
                long id = result.getLong(1);
                users.add(new DBUser(
                        id,
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getLong(6),
                        result.getLong(7),
                        result.getString(18),
                        result.getLong(19),
                        result.getLong(20),
                        result.getLong(21),
                        result.getLong(22),
                        result.getLong(23),
                        result.getBoolean(24),
                        result.getTimestamp(14).toLocalDateTime(),
                        result.getString(8),
                        result.getString(9),
                        result.getString(10),
                        result.getLong(11),
                        result.getLong(12),
                        result.getLong(13),
                        result.getString(15),
                        result.getInt(16),
                        result.getInt(17),
                        invs.getOrDefault(id, new ArrayList<>())
                ));
            }
            connection.close();
            return users;
        } catch (SQLException e) {
            queryError(query);
            throw new SQLRuntimeException(e);
        }
    }

    public Map<Long, List<DBInv.Entry>> loadItems() {
        String query = "load-items.sql";
        try {
            Map<Long, List<DBInv.Entry>> invs = new HashMap<>();
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getQuery(query));
            while (result.next()) {
                long userId = result.getLong(1);
                if (!invs.containsKey(userId)) {
                    invs.put(userId, new ArrayList<>());
                }
                invs.get(userId).add(new DBInv.Entry(DBEnum.fromKey(result.getString(2), Item.class), result.getLong(3)));
            }
            connection.close();
            return invs;
        } catch (SQLException e) {
            queryError(query);
            throw new SQLRuntimeException(e);
        }
    }

    public List<DBGuild> loadGuilds() {
        String query = "load-guilds.sql";
        try {
            List<DBGuild> guilds = new ArrayList<>();
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(getQuery(query));
            while (result.next()) {
                guilds.add(new DBGuild(
                        result.getLong(1),
                        result.getLong(2),
                        result.getLong(3),
                        result.getLong(4))
                );
            }
            connection.close();
            return guilds;
        } catch (SQLException e) {
            queryError(query);
            throw new SQLRuntimeException(e);
        }
    }

    public void saveUsers(Collection<DBUser> users) {
        String query = "save-users.sql";
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        List<MapSqlParameterSource> params = new ArrayList<>();
        for (DBUser user : users) {
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("$1", user.getUserId());
            param.addValue("$2", user.getName());
            param.addValue("$3", DBEnum.toKey(user.getTitle()));
            param.addValue("$4", user.getDescription());
            param.addValue("$5", DBEnum.toKey(user.getCdClass()));
            param.addValue("$6", user.getExp());
            param.addValue("$7", user.getCoins());
            param.addValue("$8", DBEnum.toKey(user.getWeapon().getType()));
            param.addValue("$9", DBEnum.toKey(user.getArmor().getType()));
            param.addValue("$10", DBEnum.toKey(user.getExtra().getType()));
            param.addValue("$11", user.getWeapon().getExp());
            param.addValue("$12", user.getArmor().getExp());
            param.addValue("$13", user.getExtra().getExp());
            param.addValue("$14", Timestamp.valueOf(user.getLastVote()));
            param.addValue("$15", DBEnum.toKey(user.getQuest().getType()));
            param.addValue("$16", user.getQuest().getLevel());
            param.addValue("$17", user.getQuest().getProgress());
            param.addValue("$18", DBEnum.toKey(user.getFarming()));
            param.addValue("$19", user.getMonstersSlain());
            param.addValue("$20", user.getDoorsOpened());
            param.addValue("$21", user.getBossesSlain());
            param.addValue("$22", user.getItemsFound());
            param.addValue("$23", user.getChestsOpened());
            param.addValue("$24", user.isDeleted());
            params.add(param);
        }
        template.batchUpdate(getQuery(query), params.toArray(new MapSqlParameterSource[0]));
        saveInv(users);
    }

    public void saveInv(Collection<DBUser> users) {
        String query = "save-items.sql";
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        List<MapSqlParameterSource> params = new ArrayList<>();
        for (DBUser user : users) {
            for (DBInv.Entry e : user.getInv().toEntrySet()) {
                MapSqlParameterSource param = new MapSqlParameterSource();
                param.addValue("$1", user.getUserId());
                param.addValue("$2", DBEnum.toKey(e.item()));
                param.addValue("$3", e.amount());
                params.add(param);
            }
        }
        template.batchUpdate(getQuery(query), params.toArray(new MapSqlParameterSource[0]));
    }

    public void saveGuilds(Collection<DBGuild> guilds) {
        String query = "save-guilds.sql";
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        List<MapSqlParameterSource> params = new ArrayList<>();
        for (DBGuild guild : guilds) {
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("$1", guild.getGuildId());
            param.addValue("$2", guild.getDoorsOpened());
            param.addValue("$3", guild.getMonstersSlain());
            param.addValue("$4", guild.getBossesSlain());
            params.add(param);

        }
        template.batchUpdate(getQuery(query), params.toArray(new MapSqlParameterSource[0]));
    }

    public String test() {
        return firstAsString("test.sql");
    }
}
