package cn.mcdawncity.syb.utils;
import cn.mcdawncity.syb.SYB;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.*;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import org.bukkit.Location;
import org.serverct.parrot.parrotx.PPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WorldEditUtils {

    public static void saveSchematicToFile(String typeID, CuboidRegion region) throws WorldEditException, IOException {
        PPlugin plugin = SYB.getInstance();
        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
        try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(region.getWorld(), -1)) {
            ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy(editSession, region, clipboard, region.getMinimumPoint());
            Operations.complete(forwardExtentCopy);
        }
        File file = new File(plugin.getDataFolder() + "\\resources\\" + typeID + ".schematic");
        File out = new File(Settings.STORAGE, typeID + ".schematic");
        file.createNewFile();
        out.createNewFile();
        try (ClipboardWriter clipboardWriter = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new FileOutputStream(file))) {
            clipboardWriter.write(clipboard);
        }
        try (ClipboardWriter clipboardWriter = BuiltInClipboardFormat.SPONGE_SCHEMATIC.getWriter(new FileOutputStream(out))) {
            clipboardWriter.write(clipboard);
        }
    }

    public static Clipboard getSchematicFromFile(String typeID) {
        PPlugin plugin = SYB.getInstance();
        File file = new File(plugin.getDataFolder() + "\\resources\\", typeID + ".schematic");
        com.sk89q.worldedit.extent.clipboard.Clipboard clipboard = null;
        ClipboardFormat clipboardFormat = ClipboardFormats.findByFile(file);
        try (ClipboardReader clipboardReader = clipboardFormat.getReader(new FileInputStream(file))) {
            clipboard = clipboardReader.read();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return clipboard;
    }

    public static void pasteSchematic(String typeID, Location location) {
        World weworld = BukkitAdapter.adapt(location.getWorld());
        try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(weworld, -1)) {
            Operation operation = new ClipboardHolder(getSchematicFromFile(typeID + ".schematic")).createPaste(editSession).to(BlockVector3.at(location.getX(), location.getY(), location.getZ())).ignoreAirBlocks(true).copyBiomes(false).copyEntities(false).build();
            Operations.complete(operation);
        } catch (WorldEditException worldEditException) {
            worldEditException.printStackTrace();
        }
    }
}