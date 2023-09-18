package com.stanuwu.cdlegacy.game.data;

import com.stanuwu.cdlegacy.game.content.Item;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DBInv {
    DBInv(Collection<Entry> data) {
        for (Entry e : data) {
            inv.put(e.item, e.amount);
        }
    }

    DBInv() {

    }

    private final Map<Item, Long> inv = new ConcurrentHashMap<>();

    public boolean has(Item item) {
        return amount(item) > 0;
    }

    public long amount(Item item) {
        return inv.getOrDefault(item, 0L);
    }

    public synchronized void add(Item item, long amount) {
        inv.put(item, inv.getOrDefault(item, 0L) + amount);
    }

    public synchronized void remove(Item item, long amount) {
        long after = inv.getOrDefault(item, 0L) - amount;
        inv.put(item, after);
    }

    public synchronized boolean hasDoRemove(Item item, long amount) {
        long after = inv.getOrDefault(item, 0L) - amount;
        if (after >= 0) {
            inv.put(item, after);
            return true;
        }
        return false;
    }

    public synchronized boolean hasDoRemoveAll(Entry... entries) {
        List<Entry> result = new ArrayList<>();
        for (Entry e : entries) {
            long after = inv.getOrDefault(e.item, 0L) - e.amount;
            if (after < 0) return false;
            result.add(new Entry(e.item, after));
        }
        for (Entry res : result) {
            inv.put(res.item, res.amount);
        }
        return true;
    }

    public Set<Entry> toEntrySet() {
        Set<Entry> set = new HashSet<>();
        inv.forEach((i, c) -> set.add(new Entry(i, c)));
        return set;
    }

    public Set<Entry> toEntrySetOwned() {
        Set<Entry> set = new HashSet<>();
        inv.forEach((i, c) -> {
            if (c > 0) set.add(new Entry(i, c));
        });
        return set;
    }

    public record Entry(Item item, long amount) {

    }
}
