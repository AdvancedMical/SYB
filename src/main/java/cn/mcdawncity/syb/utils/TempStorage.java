package cn.mcdawncity.syb.utils;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

public class TempStorage {
    private static HashMap<UUID, Location> tempPos1 = new HashMap<>();

    public static HashMap<UUID, Location> getTempPos1() {
        return tempPos1;
    }

    private static HashMap<UUID, Location> tempPos2 = new HashMap<>();

    public static HashMap<UUID, Location> getTempPos2() {
        return tempPos2;
    }
}
