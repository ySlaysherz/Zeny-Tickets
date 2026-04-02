package dev.slay.comandos;

import dev.slay.Main;
import dev.slay.Manager;
import dev.slay.Ticket;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NonNull;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CriarTicket implements CommandExecutor {

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command cmd, @NonNull String label, @NonNull String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }
        Player jogador = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("tickets")) {
            for (Ticket ticket : Manager.getTickets()) {
                jogador.sendMessage("");
                jogador.sendMessage(ChatColor.GOLD + "Id: " + ChatColor.GRAY + ticket.getId());
                jogador.sendMessage(ChatColor.GOLD + "Vip: " + ChatColor.GRAY + ticket.getVip());
                jogador.sendMessage(ChatColor.GOLD + "Duracao: " + ChatColor.GRAY + ticket.getVipDuracao());
                jogador.sendMessage("");
            }
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("ticket")) {
            if (args.length == 1) {
                String idTexto = args[0];
                int id = Integer.parseInt(idTexto);
                if (Manager.getTicket(id) != null) {
                    jogador.getInventory().addItem(Manager.getTicket(id).getItem());
                    return true;
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("criarticket")) {
            if (args.length == 2) {
                String vip = args[0];
                int duracao = Integer.parseInt(args[1]);
                int id = Manager.getTickets().size() + 1;
                ItemStack item = Main.instance.getTicketItemStack(vip, duracao, id);
                Ticket ticket = new Ticket(id, item, vip, duracao);
                Manager.addTicket(ticket);
                jogador.getInventory().addItem(item);
                jogador.sendMessage("Ticket criado e adicionado ao seu inventario com sucesso!");
                return true;
            }

        }
        return false;
    }

}
