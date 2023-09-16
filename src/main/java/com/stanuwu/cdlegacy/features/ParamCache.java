package com.stanuwu.cdlegacy.features;

import lombok.experimental.UtilityClass;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class ParamCache {
    private final Map<String, CacheObject> cache = new ConcurrentHashMap<>();

    public CacheBuilder start(String key, long id) {
        return new CacheBuilder(key, id);
    }

    public boolean isUpToDate(String key, long id) {
        if (!ParamCache.cache.containsKey(key)) return false;
        return ParamCache.cache.get(key).id() == id;
    }

    public CacheObject popCache(String key) {
        return ParamCache.cache.remove(key);
    }

    public class CacheBuilder {
        private final String key;
        private final long id;
        private final Map<String, Object> data;

        private CacheBuilder(String key, long id) {
            this.key = key;
            this.id = id;
            this.data = new HashMap<>();
        }

        public CacheBuilder putString(String key, String value) {
            data.put(key, value);
            return this;
        }

        public CacheBuilder putInt(String key, Integer value) {
            data.put(key, value);
            return this;
        }

        public CacheBuilder putLong(String key, Long value) {
            data.put(key, value);
            return this;
        }

        public CacheBuilder putFloat(String key, Float value) {
            data.put(key, value);
            return this;
        }

        public CacheBuilder putDouble(String key, Double value) {
            data.put(key, value);
            return this;
        }

        public CacheBuilder putBoolean(String key, Boolean value) {
            data.put(key, value);
            return this;
        }

        public CacheBuilder putAttachment(String key, Message.Attachment value) {
            data.put(key, value);
            return this;
        }

        public CacheBuilder putChannel(String key, Channel value) {
            data.put(key, value);
            return this;
        }

        public CacheBuilder putMember(String key, Member value) {
            data.put(key, value);
            return this;
        }

        public CacheBuilder putMentionable(String key, IMentionable value) {
            data.put(key, value);
            return this;
        }

        public CacheBuilder putRole(String key, Role value) {
            data.put(key, value);
            return this;
        }

        public CacheBuilder putUser(String key, User value) {
            data.put(key, value);
            return this;
        }

        public void end() {
            ParamCache.cache.put(this.key, new CacheObject(this.id, this.data));
        }
    }

    public record CacheObject(long id, Map<String, Object> cachedData) {
        public String getString(String key) {
            return (String) this.cachedData.get(key);
        }

        public int getInt(String key) {
            return (Integer) this.cachedData.get(key);
        }

        public long getLong(String key) {
            return (Long) this.cachedData.get(key);
        }

        public float getFloat(String key) {
            return (Float) this.cachedData.get(key);
        }

        public double getDouble(String key) {
            return (double) this.cachedData.get(key);
        }

        public boolean getBoolean(String key) {
            return (Boolean) this.cachedData.get(key);
        }

        public Message.Attachment getAttachment(String key) {
            return (Message.Attachment) this.cachedData.get(key);
        }

        public Channel getChannel(String key) {
            return (Channel) this.cachedData.get(key);
        }

        public Member getMember(String key) {
            return (Member) this.cachedData.get(key);
        }

        public IMentionable getMentionable(String key) {
            return (IMentionable) this.cachedData.get(key);
        }

        public Role getRole(String key) {
            return (Role) this.cachedData.get(key);
        }

        public User getUser(String key) {
            return (User) this.cachedData.get(key);
        }
    }
}
