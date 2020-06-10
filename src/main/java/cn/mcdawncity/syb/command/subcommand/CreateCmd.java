package cn.mcdawncity.syb.command.subcommand;

import cn.mcdawncity.syb.features.SaveBuildControl;
import cn.mcdawncity.syb.utils.Settings;
import cn.mcdawncity.syb.utils.TempStorage;
import com.sk89q.worldedit.WorldEditException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.command.PCommand;
import org.serverct.parrot.parrotx.utils.I18n;

import java.io.File;
import java.io.IOException;

public class CreateCmd implements PCommand {
    @Override
    public String getPermission() {
        return "SYB.use";
    }

    @Override
    public String getDescription() {
        return "创建你的建筑模板文件并上传到服务器";
    }

    @Override
    public boolean execute(PPlugin plugin, CommandSender sender, String[] args) {
        Player user = (Player)sender;
        if (TempStorage.getTempPos1().get(user.getUniqueId()) == null || TempStorage.getTempPos2().get(user.getUniqueId()) == null){
            I18n.send(user, plugin.lang.build(plugin.localeKey, I18n.Type.WARN, "请先用木剑选区再进行操作."));
        } else {
            try {
                SaveBuildControl.saveBuild(args[1], user.getWorld(), TempStorage.getTempPos1().get(user.getUniqueId()), TempStorage.getTempPos2().get(user.getUniqueId()), user.getUniqueId());
                I18n.send(user, plugin.lang.build(plugin.localeKey, I18n.Type.INFO, "已将你的建筑保存完毕, 可访问地址下载: " + Settings
                        .URL
                        .replace("{typeID}", args[1])));
            } catch (WorldEditException | IOException exception) {
                plugin.lang.logError("保存", args[1], exception.toString());
                exception.printStackTrace();
            }
        }
        return false;
    }
}
