package dev.slay.eventos;

import dev.slay.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class UsarTicket implements Listener {

    @EventHandler
    public void aoUsarTicket(PlayerInteractEvent evento) {
        Player jogador = (Player) evento.getPlayer();

        if (jogador.getInventory().getItemInMainHand() == null) return;
        ItemStack item = jogador.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasLore()) return;
        List<String> lore = meta.getLore();
        String[] idString = lore.get(0).split(":");
        int id = Integer.parseInt(idString[1].trim());
        if (item.getType() != Manager.getTicket(id).getItem().getType()) return;
        evento.setCancelled(true);
        jogador.getInventory().removeItem(jogador.getInventory().getItemInMainHand());
        String comando = "/darvip " + " " + Manager.getTicket(id).getVip() + " " + Manager.getTicket(id).getVipDuracao();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comando);
        jogador.sendMessage(ChatColor.GREEN + "Ticket usado e vip adicionado com sucesso!");
    }

}
