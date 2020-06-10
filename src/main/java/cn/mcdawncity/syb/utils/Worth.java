package cn.mcdawncity.syb.utils;

import cn.mcdawncity.syb.SYB;
import org.bukkit.Material;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.config.PConfig;

import java.io.File;

public class Worth extends PConfig {

    private static Worth worth;

    public Worth(PPlugin plugin) {
        super(plugin, "Worth", "价格表文件");
    }

    public static Worth getWorth() {
        if (worth == null)
            worth = new Worth(SYB.getInstance());
        return worth;
    }

    @Override
    public void saveDefault() {
        this.plugin.saveResource("Worth.yml", false);
    }

    @Override
    public void init() {
        setFile(new File(this.plugin.getDataFolder(), "Worth.yml"));
        super.init();
    }

    public static double price(Material material){
        return getWorth().getConfig().getDouble("Worth" + material, -1.0D);
    }
}
