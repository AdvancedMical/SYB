package cn.mcdawncity.syb.utils;

import cn.mcdawncity.syb.SYB;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.config.PConfig;
import org.serverct.parrot.parrotx.utils.I18n;

import java.io.File;

public class Settings extends PConfig {

    private static Settings settings;

    public static String URL;

    public static String STORAGE;

    public Settings(PPlugin plugin) {
        super(plugin, "Settings", "主配置文件");
    }

    public static Settings getSettings() {
        if (settings == null)
            settings = new Settings(SYB.getInstance());
        return settings;
    }

    @Override
    public void saveDefault() {
        this.plugin.saveResource("Settings.yml", false);
    }

    @Override
    public void load(File file) {
        URL = getConfig().getString("URL");
        STORAGE = getConfig().getString("Storage");
    }
}
