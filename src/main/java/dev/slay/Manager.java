package dev.slay;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class Manager {

    public static Set<Ticket> TicketsCriados = new HashSet<>();

    public static void addTicket(Ticket ticket) {
        TicketsCriados.add(ticket);
    }

    public static void removerTicket(Ticket ticket) {
        TicketsCriados.remove(ticket);
    }

    public static Set<Ticket> getTickets() {
        return TicketsCriados;
    }

    public static Ticket getTicket(int id) {
        for (Ticket ticket : TicketsCriados) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public static void CarregarTickets() {
        for (String ids : Main.instance.getConfig().getConfigurationSection("Tickets").getKeys(false)) {
            int id = Integer.parseInt(ids);
            String vip = Main.instance.getConfig().getConfigurationSection("Tickets").getConfigurationSection(ids).getString("Vip");
            int duracao = Main.instance.getConfig().getConfigurationSection("Tickets").getConfigurationSection(ids).getInt("Duracao");
            ItemStack item = Main.instance.getTicketItemStack(vip, duracao,id);
            Ticket ticket = new Ticket(id, item, vip, duracao);
            TicketsCriados.add(ticket);
        }
    }
    public static void SalvarTickest() {
        for (Ticket ticket : TicketsCriados) {
            ConfigurationSection secao = Main.instance.getConfig().getConfigurationSection("Tickets").getConfigurationSection("" + ticket.getId());
            if (secao == null) {
                secao = Main.instance.getConfig().getConfigurationSection("Tickets").createSection("" + ticket.getId());
                secao.set("Vip", ticket.getVip());
                secao.set("Duracao", ticket.getVipDuracao());
            }
        }
        Main.instance.saveConfig();
        Main.instance.reloadConfig();
    }
}
