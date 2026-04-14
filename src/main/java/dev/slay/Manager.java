package dev.slay;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class Manager {

    public static Set<Ticket> TicketsCriados = new HashSet<>();

    public static Boolean isConfiguracaoCarregada = false;

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
        if (Main.instance.getConfig().getConfigurationSection("Tickets") == null) Main.instance.getConfig().createSection("Tickets");
        if (!isConfiguracaoCarregada) {
            for (String ids : Main.instance.getConfig().getConfigurationSection("Tickets").getKeys(false)) {
                if (ids == null) {
                    continue;
                }
                String vip = Main.instance.getConfig().getConfigurationSection("Tickets").getConfigurationSection(ids).getString("Vip");
                int duracao = Integer.parseInt(Main.instance.getConfig().getConfigurationSection("Tickets").getConfigurationSection(ids).getString("Duracao"));
                int id = Integer.parseInt(ids);

                Ticket ticket = new Ticket(id, Main.instance.getTicketItemStack(vip, duracao, id), vip, duracao);
                Manager.addTicket(ticket);

            }
        }
        if (TicketsCriados.isEmpty()) {
            isConfiguracaoCarregada = false;
        } else {
            isConfiguracaoCarregada = true;
        }
    }
    public static void SalvarTickest() {
        for (Ticket ticket : TicketsCriados) {
            Main.instance.getConfig().getConfigurationSection("Tickets").createSection("" + ticket.getId());
            ConfigurationSection secao = Main.instance.getConfig().getConfigurationSection("Tickets").getConfigurationSection("" + ticket.getId());
            secao.set("Vip", ticket.getVip());
            secao.set("Duracao", ticket.getVipDuracao());
        }
        Main.instance.saveConfig();
        Main.instance.reloadConfig();
    }
}
