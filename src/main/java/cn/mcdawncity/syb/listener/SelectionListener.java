package cn.mcdawncity.syb.listener;

import cn.mcdawncity.syb.SYB;
import cn.mcdawncity.syb.utils.TempStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.utils.BasicUtil;
import org.serverct.parrot.parrotx.utils.I18n;

import java.util.UUID;

public class SelectionListener implements Listener {
    @EventHandler
    public void onBlockBreak(PlayerInteractEvent event){
        PPlugin plugin = SYB.getInstance();
        if (event.getItem() != null && event.getItem().getType().equals(Material.WOODEN_SWORD)){
            Block block = event.getClickedBlock();
            if (block != null){
                event.setCancelled(true);
                Location location = block.getLocation();
                Player user = event.getPlayer();
                UUID uuid = user.getUniqueId();
                if (event.getAction() == Action.LEFT_CLICK_BLOCK){
                    TempStorage.getTempPos1().put(uuid, location);
                    I18n.send(user, plugin.lang.build(plugin.localeKey, I18n.Type.INFO, "已选择点1(" + BasicUtil.formatLocation(location) + "&7)"));
                } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
                    TempStorage.getTempPos2().put(uuid, location);
                    I18n.send(user, plugin.lang.build(plugin.localeKey, I18n.Type.INFO, "已选择点2(" + BasicUtil.formatLocation(location) + "&7)"));
                }
            }
        }
    }
}