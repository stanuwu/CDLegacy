package com.stanuwu.cdlegacy.message.embeds;

import com.stanuwu.cdlegacy.Config;
import com.stanuwu.cdlegacy.message.LangManager;
import com.stanuwu.cdlegacy.message.Placeholder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

public class Embeds {
    private String title = null;
    private String titleUrl = null;
    private String description = null;
    private final List<MessageEmbed.Field> fields = new ArrayList<>();
    private Color color = null;
    private String image = null;
    private String thumbnail = null;
    private String footer = null;
    private String footerIcon = null;
    private String author = null;
    private String authorUrl = null;
    private String authorIcon = null;
    private String url = null;
    private TemporalAccessor timestamp = null;

    private Embeds() {

    }

    public static Embeds blank() {
        return new Embeds();
    }

    public static Embeds defSmall() {
        return blank().defaultColor().defaultFooter();
    }

    public static Embeds defLarge() {
        return blank().defaultColor().defaultFooter().defaultThumbnail();
    }

    public static Embeds small(String title) {
        return defSmall().title(title);
    }

    public static Embeds large(String title) {
        return defLarge().title(title);
    }

    public static Embeds error(String error) {
        return blank().colorError().title("Error").description(error);
    }

    public static Embeds langSmall(String key, Placeholder... placeholders) {
        return defSmall().title(LangManager.get(key, placeholders));
    }

    public static Embeds langLarge(String key, Placeholder... placeholders) {
        return defLarge().title(LangManager.get(key, placeholders));
    }

    public static Embeds langError(String key, Placeholder... placeholders) {
        return blank().colorError().title("Error").description(LangManager.get(key, placeholders));
    }

    public MessageEmbed build() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(title, titleUrl);
        builder.setDescription(description);
        for (MessageEmbed.Field f : fields) {
            builder.addField(f);
        }
        builder.setColor(color);
        builder.setImage(image);
        builder.setThumbnail(thumbnail);
        builder.setFooter(footer, footerIcon);
        builder.setAuthor(author, authorUrl, authorIcon);
        builder.setUrl(url);
        builder.setTimestamp(timestamp);
        return builder.build();
    }

    public Embeds title(String title) {
        this.title = title;
        return this;
    }

    public Embeds langTitle(String key, Placeholder... placeholders) {
        return title(LangManager.get(key, placeholders));
    }

    public Embeds title(String title, String url) {
        this.title = title;
        this.titleUrl = url;
        return this;
    }

    public Embeds langTitle(String key, String url, Placeholder... placeholders) {
        return title(LangManager.get(key, placeholders), url);
    }

    public Embeds description(String description) {
        this.description = description;
        return this;
    }

    public Embeds langDescription(String key, Placeholder... placeholders) {
        return description(LangManager.get(key, placeholders));
    }

    public Embeds field(String title) {
        this.fields.add(new MessageEmbed.Field(title, "", false));
        return this;
    }

    public Embeds langField(String key, Placeholder... placeholders) {
        return field(LangManager.get(key, placeholders));
    }

    public Embeds field(String title, boolean inline) {
        this.fields.add(new MessageEmbed.Field(title, "", inline));
        return this;
    }

    public Embeds langField(String key, boolean inline, Placeholder... placeholders) {
        return field(LangManager.get(key, placeholders), inline);
    }

    public Embeds field(String title, String value) {
        this.fields.add(new MessageEmbed.Field(title, value, false));
        return this;
    }

    public Embeds langField(String keyTitle, String keyValue, Placeholder... placeholders) {
        return field(LangManager.get(keyTitle, placeholders), LangManager.get(keyValue, placeholders));
    }

    public Embeds field(String title, String value, boolean inline) {
        this.fields.add(new MessageEmbed.Field(title, value, inline));
        return this;
    }

    public Embeds langField(String keyTitle, String keyValue, boolean inline, Placeholder... placeholders) {
        return field(LangManager.get(keyTitle, placeholders), LangManager.get(keyValue, placeholders), inline);
    }

    public Embeds blankField() {
        this.fields.add(new MessageEmbed.Field("\u200e", "\u200e", false));
        return this;
    }

    public Embeds blankField(boolean inline) {
        this.fields.add(new MessageEmbed.Field("\u200e", "\u200e", inline));
        return this;
    }

    public Embeds color(Color color) {
        this.color = color;
        return this;
    }

    public Embeds image(String image) {
        this.image = image;
        return this;
    }

    public Embeds thumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public Embeds footer(String footer) {
        this.footer = footer;
        return this;
    }

    public Embeds langFooter(String key, Placeholder... placeholders) {
        return footer(LangManager.get(key, placeholders));
    }

    public Embeds footer(String footer, String icon) {
        this.footer = footer;
        this.footerIcon = icon;
        return this;
    }

    public Embeds langFooter(String key, String icon, Placeholder... placeholders) {
        return footer(LangManager.get(key, placeholders), icon);
    }

    public Embeds footerIcon(String icon) {
        this.footerIcon = icon;
        return this;
    }

    public Embeds author(String author) {
        this.author = author;
        return this;
    }

    public Embeds author(String author, String url) {
        this.author = author;
        this.authorUrl = url;
        return this;
    }

    public Embeds author(String author, String url, String icon) {
        this.author = author;
        this.authorUrl = url;
        this.authorIcon = icon;
        return this;
    }

    public Embeds authorIcon(String icon) {
        this.authorIcon = icon;
        return this;
    }

    public Embeds authorIcon(String author, String icon) {
        this.author = author;
        this.authorIcon = icon;
        return this;
    }

    public Embeds url(String url) {
        this.url = url;
        return this;
    }

    public Embeds timestamp(TemporalAccessor timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Embeds defaultFooter() {
        return langFooter("default-desc", Config.AVATAR);
    }

    public Embeds defaultThumbnail() {
        return thumbnail(Config.AVATAR);
    }

    public Embeds defaultColor() {
        return color(Color.MAGENTA);
    }

    public Embeds colorError() {
        return color(Color.RED);
    }

    public Embeds colorSuccess() {
        return color(Color.GREEN);
    }

    public Embeds colorWarn() {
        return color(Color.ORANGE);
    }
}
