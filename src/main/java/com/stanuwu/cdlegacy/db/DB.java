package com.stanuwu.cdlegacy.db;

import com.stanuwu.cdlegacy.game.content.Item;
import com.stanuwu.cdlegacy.game.data.DBEnum;
import com.stanuwu.cdlegacy.game.data.DBGuild;
import com.stanuwu.cdlegacy.game.data.DBInv;
import com.stanuwu.cdlegacy.game.data.DBUser;
import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

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
            Statement statement = this.connection.createStatement();
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
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(getQuery(query));
            while (result.next()) {
                long userId = result.getLong(1);
                if (!invs.containsKey(userId)) {
                    invs.put(userId, new ArrayList<>());
                }
                invs.get(userId).add(new DBInv.Entry(DBEnum.fromKey(result.getString(2), Item.class), result.getLong(3)));
            }
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
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(getQuery(query));
            while (result.next()) {
                guilds.add(new DBGuild(
                        result.getLong(1),
                        result.getLong(2),
                        result.getLong(3),
                        result.getLong(4))
                );
            }
            return guilds;
        } catch (SQLException e) {
            queryError(query);
            throw new SQLRuntimeException(e);
        }
    }

    public void saveUser(Collection<DBUser> users) {
        String query = "save-user.sql";
        try {
            for (DBUser user : users) {
                CallableStatement statement = connection.prepareCall(getQuery(query));
                statement.setLong("$1", user.getUserId());
                statement.setString("$2", user.getName());
                statement.setString("$3", DBEnum.toKey(user.getTitle()));
                statement.setString("$4", user.getDescription());
                statement.setString("$5", DBEnum.toKey(user.getCdClass()));
                statement.setLong("$6", user.getExp());
                statement.setLong("$7", user.getCoins());
                statement.setString("$8", DBEnum.toKey(user.getWeapon().getType()));
                statement.setString("$9", DBEnum.toKey(user.getArmor().getType()));
                statement.setString("$10", DBEnum.toKey(user.getExtra().getType()));
                statement.setLong("$11", user.getWeapon().getExp());
                statement.setLong("$12", user.getArmor().getExp());
                statement.setLong("$13", user.getExtra().getExp());
                statement.setTimestamp("$14", Timestamp.valueOf(user.getLastVote()));
                statement.setString("$15", DBEnum.toKey(user.getQuest().getType()));
                statement.setInt("$16", user.getQuest().getLevel());
                statement.setInt("$17", user.getQuest().getProgress());
                statement.setString("$18", DBEnum.toKey(user.getFarming()));
                statement.setLong("$19", user.getMonstersSlain());
                statement.setLong("$20", user.getDoorsOpened());
                statement.setLong("$21", user.getBossesSlain());
                statement.setLong("$22", user.getItemsFound());
                statement.setLong("$23", user.getChestsOpened());
                statement.setBoolean("$24", user.isDeleted());
                statement.executeQuery();
                saveInv(user.getUserId(), user.getInv());
            }
        } catch (SQLException e) {
            queryError(query);
            throw new SQLRuntimeException(e);
        }
    }

    public void saveInv(long userId, DBInv inv) {
        String query = "save-items.sql";
        try {
            for (DBInv.Entry e : inv.toEntrySet()) {
                CallableStatement statement = connection.prepareCall(getQuery(query));
                statement.setLong("$1", userId);
                statement.setString("$2", DBEnum.toKey(e.item()));
                statement.setLong("$3", e.amount());
                statement.executeQuery();
            }
        } catch (SQLException e) {
            queryError(query);
            throw new SQLRuntimeException(e);
        }
    }

    public void saveGuild(Collection<DBGuild> guilds) {
        String query = "save-guilds.sql";
        try {
            for (DBGuild guild : guilds) {
                CallableStatement statement = connection.prepareCall(getQuery(query));
                statement.setLong("$1", guild.getGuildId());
                statement.setLong("$2", guild.getDoorsOpened());
                statement.setLong("$3", guild.getMonstersSlain());
                statement.setLong("$4", guild.getBossesSlain());
                statement.executeQuery();
            }
        } catch (SQLException e) {
            queryError(query);
            throw new SQLRuntimeException(e);
        }
    }

    public String test() {
        return firstAsString("test.sql");
    }
}
