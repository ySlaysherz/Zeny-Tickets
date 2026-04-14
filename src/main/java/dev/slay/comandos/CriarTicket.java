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

import java.util.Random;

public class CriarTicket implements CommandExecutor {

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command cmd, @NonNull String label, @NonNull String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }
        Player jogador = (Player) sender;
        if (!jogador.isOp()) {
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("tickets")) {
            if (Manager.getTickets().isEmpty()) return true;
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
                if (Manager.getTickets().isEmpty()) return true;
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
                if (!Main.instance.getConfig().getStringList("Vips").contains(vip)) {
                    jogador.sendMessage("");
                    jogador.sendMessage(ChatColor.RED + "Esse vip nao existe em nosso banco de dados.");
                    jogador.sendMessage("");
                    jogador.sendMessage(ChatColor.YELLOW + "Vip inserido: " + ChatColor.GRAY + vip);
                    jogador.sendMessage("");
                    jogador.sendMessage(ChatColor.GREEN + "Vips disponiveis");
                    jogador.sendMessage("");
                    for (String v : Main.instance.getConfig().getStringList("Vips")) {
                        jogador.sendMessage(ChatColor.GOLD + " " + v);
                    }
                    jogador.sendMessage("");
                    return true;
                }
                int duracao = Integer.parseInt(args[1]);
                int id = gerarID();
                ItemStack item = Main.instance.getTicketItemStack(vip, duracao, id);
                Ticket ticket = new Ticket(id, item, vip, duracao);
                Manager.addTicket(ticket);
                jogador.getInventory().addItem(item);
                jogador.sendMessage(ChatColor.GREEN + "Ticket criado e adicionado ao seu inventario com sucesso!");
                return true;
            }
        }
        return false;
    }

    public static int gerarID() {

        Manager.CarregarTickets();
        int id = 0;
        if (!Manager.getTickets().isEmpty()) {
            id = Manager.getTickets().size() - 1;
            for (Ticket ticket : Manager.getTickets()) {
                if (ticket.getId() == id) {
                    id++;
                }
            }
        }
        return id;
    }

}
