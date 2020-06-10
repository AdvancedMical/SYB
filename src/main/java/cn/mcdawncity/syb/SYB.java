package cn.mcdawncity.syb;

import cn.mcdawncity.syb.command.SYBCommand;
import cn.mcdawncity.syb.listener.SelectionListener;
import cn.mcdawncity.syb.utils.Settings;
import cn.mcdawncity.syb.utils.Worth;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.hooks.VaultUtil;

public final class SYB extends PPlugin {

    private static VaultUtil vaultUtil;

    public static VaultUtil getVaultUtil() {
        return vaultUtil;
    }

    @Override
    protected void preload() {
        this.pConfig = new Settings(this);
        this.pConfig.init();
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    protected void load() {
        getLogger().info("正在加载SYB，版本" + getDescription().getVersion());
        vaultUtil = new VaultUtil(this, true);
        Worth.getWorth().init();
        registerCommand(new SYBCommand(this, "syb"));
        if (!vaultUtil.isHooks())
            Bukkit.getPluginManager().disablePlugin(this);
        getLogger().info("插件已加载");
    }

    @Override
    protected void registerListener() {
        Bukkit.getPluginManager().registerEvents(new SelectionListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("插件已卸载");
    }
}
