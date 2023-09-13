package com.stanuwu.cdlegacy;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class CDLegacy {
    public static void main(String[] args) {
        boolean modeDev = false;
        if (args.length > 0) modeDev = Objects.equals(args[0], "dev");
        long devGuild = -1L;
        if (args.length > 1) devGuild = Long.parseLong(args[1]);

        if (modeDev) {
            System.out.println("STARTING IN DEVELOPMENT MODE");
            if (devGuild == -1L) {
                System.out.println("INVALID DEV GUILD");
                return;
            }
            System.out.println("DEV GUILD: " + devGuild);
        }

        System.out.println("READING TOKEN");
        String TOKEN;
        try {
            TOKEN = new String(Files.readAllBytes(Paths.get("data/token.txt")));
        } catch (IOException e) {
            System.out.println("UNABLE TO READ TOKEN");
            return;
        }

        System.out.println("STARTING BOT");
        JDABuilder b = JDABuilder.createDefault(TOKEN);
        Config.configMemory(b);
        Config.configFeatures(b);
        Config.configActivity(b);

        JDA JDA = b.build();
        System.out.println("BOT STARTED");
    }
}
