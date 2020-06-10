package cn.mcdawncity.syb.command;

import cn.mcdawncity.syb.command.subcommand.CreateCmd;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.command.CommandHandler;

public class SYBCommand extends CommandHandler {
    public SYBCommand(PPlugin plugin, String mainCmd) {
        super(plugin, mainCmd);
        registerSubCommand("create", new CreateCmd());
    }
}
