package me.libraryaddict.disguise.commands.undisguise;

import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.LibsDisguises;
import me.libraryaddict.disguise.commands.interactions.UndisguiseEntityInteraction;
import me.libraryaddict.disguise.utilities.LibsPremium;
import me.libraryaddict.disguise.utilities.translations.LibsMsg;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UndisguiseEntityCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player && !sender.isOp() &&
                (!LibsPremium.isPremium() || LibsPremium.getPaidInformation() == LibsPremium.getPluginInformation())) {
            sender.sendMessage(ChatColor.RED + "Please purchase Lib's Disguises to enable player commands");
            return true;
        }

        if (sender.getName().equals("CONSOLE")) {
            LibsMsg.NO_CONSOLE.send(sender);
            return true;
        }

        if (!sender.hasPermission("libsdisguises.undisguiseentity")) {
            LibsMsg.NO_PERM.send(sender);
            return true;
        }

        LibsDisguises.getInstance().getListener().addInteraction(sender.getName(), new UndisguiseEntityInteraction(),
                DisguiseConfig.getDisguiseEntityExpire());
        LibsMsg.UND_ENTITY.send(sender);

        return true;
    }
}
