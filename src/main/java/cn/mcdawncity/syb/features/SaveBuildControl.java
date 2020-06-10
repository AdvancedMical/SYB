package cn.mcdawncity.syb.features;

import cn.mcdawncity.syb.SYB;
import cn.mcdawncity.syb.utils.WorldEditUtils;
import cn.mcdawncity.syb.utils.Worth;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.utils.I18n;

import java.io.IOException;
import java.util.*;

public class SaveBuildControl {

    public static void saveBuild(String typeID, org.bukkit.World world, Location location1, org.bukkit.Location location2, UUID player) throws WorldEditException, IOException {
        SYB plugin = (SYB) SYB.getInstance();
        com.sk89q.worldedit.world.World WEWorld = BukkitAdapter.adapt(world);
        BlockVector3 min = BlockVector3.at(location1.getX(), location1.getY(), location1.getZ());
        BlockVector3 max = BlockVector3.at(location2.getX(), location2.getY(), location2.getZ());
        CuboidRegion region = new CuboidRegion(WEWorld, min, max);
        Player user = Bukkit.getPlayer(player);
        Map<Material, Integer> blocks = new HashMap<>();
        if (user == null) {
            plugin.lang.logError("保存", typeID, "Player 对象为空");
            return;
        }
        I18n.send(user, plugin.lang.build(plugin.localeKey, I18n.Type.INFO, "方块价值统计:"));
        for (Material material : blocks.keySet()){
            double single = Worth.price(material);
            double total = single * blocks.get(material);
            String report = " &c" + material + " &7▶ &c" + blocks.get(material) + " &7块, 共 &c" + total + " &7金币. (" + single + "/块)";
            I18n.send(user, I18n.color(report));
        }
        WorldEditUtils.saveSchematicToFile(typeID, region);
        I18n.send(user, plugin.lang.build(plugin.localeKey, I18n.Type.INFO, "保存成功."));
    }
}
