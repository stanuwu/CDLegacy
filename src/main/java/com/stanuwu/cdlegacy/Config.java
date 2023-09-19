package com.stanuwu.cdlegacy;

import lombok.experimental.UtilityClass;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.concurrent.Executors;

@UtilityClass
public class Config {
    public final String TOS = "https://docs.google.com/document/d/1TiKOlQeP6SdrC1n-QIFZHJhYAbhTUdqij0wUWoQ4fUg/edit?usp=sharing" ;
    public final String PP = "https://docs.google.com/document/d/1XB37EaNfEdynYvoUPhogNtkYRXF6lBUbGGxkX9uhDe4/edit?usp=sharing" ;
    public final String AVATAR = "https://cdn.discordapp.com/avatars/887247576297013288/ab077ae898cf9ca26c3f035c5ebf15a2.webp?size=1024" ;
    public final String VERSION = "1.0-beta" ;
    public final long N = 677623766066659348L;
    public final long[] LEGACY = {623984743914012712L, 364412921368936451L, 262171901521166336L, 159355703016947713L, 421281747653754881L, 677623766066659348L, 255732879525412865L, 363409330910920706L, 837523232054706197L, 543963521181024276L, 506637650615336963L};
    public final long[] STAFF = {623984743914012712L};

    public boolean isLegacy(long id) {
        for (long id2 : LEGACY) {
            if (id == id2) return true;
        }
        return false;
    }

    public boolean isStaff(long id) {
        for (long id2 : STAFF) {
            if (id == id2) return true;
        }
        return false;
    }

    public void configMemory(JDABuilder b) {
        b.disableCache(CacheFlag.VOICE_STATE, CacheFlag.STICKER, CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.FORUM_TAGS, CacheFlag.MEMBER_OVERRIDES, CacheFlag.SCHEDULED_EVENTS);
        b.setMemberCachePolicy(MemberCachePolicy.ONLINE);
    }

    public void configFeatures(JDABuilder b) {
        b.setEventPool(Executors.newCachedThreadPool());
        b.enableIntents(GatewayIntent.GUILD_MEMBERS);
        b.disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_TYPING);
        b.setBulkDeleteSplittingEnabled(false);
    }

    public void configActivity(JDABuilder b) {
        b.setActivity(Activity.competing("with you! | /help"));
    }
}
