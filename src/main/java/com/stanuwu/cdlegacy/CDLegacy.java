package com.stanuwu.cdlegacy;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.simple.SimpleLoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class CDLegacy {
    public static void main(String[] args) {
        Logger logger = new SimpleLoggerFactory().getLogger("CDLegacy");

        boolean modeDev = false;
        if (args.length > 0) modeDev = Objects.equals(args[0], "dev");
        long devGuild = -1L;
        if (args.length > 1) devGuild = Long.parseLong(args[1]);

        if (modeDev) {
            logger.warn("STARTING IN DEVELOPMENT MODE");
            if (devGuild == -1L) {
                logger.error("INVALID DEV GUILD");
                return;
            }
            logger.info("DEV GUILD: " + devGuild);
        }

        logger.info("READING TOKEN");
        String TOKEN;
        try {
            TOKEN = new String(Files.readAllBytes(Paths.get("data/token.txt")));
        } catch (IOException e) {
            logger.error("UNABLE TO READ TOKEN");
            return;
        }

        logger.info("STARTING BOT");
        JDABuilder b = JDABuilder.createDefault(TOKEN);
        Config.configMemory(b);
        Config.configFeatures(b);
        Config.configActivity(b);

        Features features = new Features();
        features.registerPre(b);

        JDA JDA = b.build();
        try {
            JDA.awaitReady();
        } catch (InterruptedException e) {
            logger.error("BOT STARTUP INTERRUPTED");
            return;
        }

        logger.info("BOT STARTED");

        logger.info("REGISTERING FEATURES");
        features.registerPost(JDA, modeDev, devGuild);
        logger.info("REGISTERED FEATURES");
    }
}
