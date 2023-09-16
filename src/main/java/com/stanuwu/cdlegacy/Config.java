package com.stanuwu.cdlegacy;

import lombok.experimental.UtilityClass;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

@UtilityClass
public class Config {
    public final String TOS = "https://docs.google.com/document/d/1TiKOlQeP6SdrC1n-QIFZHJhYAbhTUdqij0wUWoQ4fUg/edit?usp=sharing";
    public final String PP = "https://docs.google.com/document/d/1XB37EaNfEdynYvoUPhogNtkYRXF6lBUbGGxkX9uhDe4/edit?usp=sharing";
    public final String AVATAR = "https://cdn.discordapp.com/avatars/887247576297013288/ab077ae898cf9ca26c3f035c5ebf15a2.webp?size=1024";
    public final long[] staff = {623984743914012712L};

    public boolean isStaff(long id) {
        for (long id2 : staff) {
            if (id == id2) return true;
        }
        return false;
    }

    public void configMemory(JDABuilder b) {
        b.disableCache(CacheFlag.VOICE_STATE, CacheFlag.STICKER, CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.FORUM_TAGS, CacheFlag.MEMBER_OVERRIDES, CacheFlag.SCHEDULED_EVENTS);
        b.setMemberCachePolicy(MemberCachePolicy.ONLINE);
    }

    public void configFeatures(JDABuilder b) {
        b.enableIntents(GatewayIntent.GUILD_MEMBERS);
        b.disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_TYPING);
        b.setBulkDeleteSplittingEnabled(false);
    }

    public void configActivity(JDABuilder b) {
        b.setActivity(Activity.competing("with you! | /help"));
    }
}
