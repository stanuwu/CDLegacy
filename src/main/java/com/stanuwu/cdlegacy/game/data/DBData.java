package com.stanuwu.cdlegacy.game.data;

import com.stanuwu.cdlegacy.db.DB;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class DBData {
    private final Map<Long, DBUser> users = new ConcurrentHashMap<>();
    private final Map<Long, DBGuild> guilds = new ConcurrentHashMap<>();
    private final Logger logger = new SimpleLoggerFactory().getLogger("Database");

    public void load(DB database) {
        logger.info("LOADING DATABASE INFO");
        for (DBUser user : database.loadUsers()) {
            users.put(user.getUserId(), user);
        }
        for (DBGuild guild : database.loadGuilds()) {
            guilds.put(guild.getGuildId(), guild);
        }
        logger.info("LOADED DATABASE INFO");
    }

    public synchronized void save(DB database) {
        logger.info("SAVING DATABASE INFO");
        database.saveUsers(users.values());
        database.saveGuilds(guilds.values());
        logger.info("SAVED DATABASE INFO");
    }

    public DBUser getUser(long userId) {
        DBUser user = users.get(userId);
        if (user != null && user.isDeleted()) return null;
        return user;
    }

    public boolean makeUser(long userId, String name) {
        if (users.containsKey(userId)) return false;
        users.put(userId, new DBUser(userId, name));
        return true;
    }

    public void overrideUser(long userId, String name) {
        users.put(userId, new DBUser(userId, name));
    }

    public DBGuild getGuild(long guildId) {
        return guilds.computeIfAbsent(guildId, g -> new DBGuild(g, 0, 0, 0));
    }
}
