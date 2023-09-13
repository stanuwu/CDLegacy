package com.stanuwu.cdlegacy;

import lombok.experimental.UtilityClass;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

@UtilityClass
public class Config {
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