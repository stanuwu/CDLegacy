package com.stanuwu.cdlegacy.game.gameplay;

import com.stanuwu.cdlegacy.Config;
import com.stanuwu.cdlegacy.game.data.DBUser;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import net.dv8tion.jda.api.JDA;
import org.discordbots.api.client.DiscordBotListAPI;
import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

@UtilityClass
public class Vote {
    private final Logger logger = new SimpleLoggerFactory().getLogger("Discord Bot List");
    private DiscordBotListAPI api;
    @Getter
    private boolean voteFeatures;

    public void init() {
        logger.info("READING TOKEN");
        String TOKEN;
        try {
            TOKEN = new String(Files.readAllBytes(Paths.get("data/dbl_token.txt")));
            voteFeatures = true;
        } catch (IOException e) {
            logger.error("UNABLE TO READ TOKEN");
            voteFeatures = false;
            return;
        }
        api = new DiscordBotListAPI.Builder()
                .token(TOKEN)
                .botId("" + Config.SELF)
                .build();
    }

    public void postStats(JDA jda) {
        logger.info("POSTING SERVER COUNT");
        if (voteFeatures) api.setStats(jda.getGuilds().size());
        logger.info("POSTED SERVER COUNT");
    }

    public boolean hasVotedSync(DBUser user) {
        if (Config.isStaff(user.getUserId())) return true;
        if (!voteFeatures) return false;
        try {
            return api.hasVoted("" + user.getUserId()).toCompletableFuture().get();
        } catch (ExecutionException | InterruptedException e) {
            return false;
        }
    }
}
