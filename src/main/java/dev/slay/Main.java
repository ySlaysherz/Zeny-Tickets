package dev.slay;

import dev.slay.comandos.CriarTicket;
import dev.slay.eventos.UsarTicket;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.Material;

import org.bukkit.command.CommandExecutor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin implements CommandExecutor, Listener {
    public static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new UsarTicket(), this);

        getCommand("ticket").setExecutor(new CriarTicket());
        getCommand("tickets").setExecutor(new CriarTicket());
        getCommand("criarticket").setExecutor(new CriarTicket());
    }

    @Override
    public void onDisable() {
    }

    public ItemStack getTicketItemStack(String vip, int duracao, int id) {
        ItemStack ticket = new ItemStack(Material.TRIAL_KEY);
        ItemMeta meta = ticket.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Ticket Vip");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLACK + "ID: " + id);
        lore.add("");
        lore.add(ChatColor.GRAY + " Clique com o botao direito ou");
        lore.add(ChatColor.GRAY + " interaja com o ambiente para");
        lore.add(ChatColor.GRAY + " receber o seu Vip automaticamente.");
        lore.add("");
        lore.add(ChatColor.GOLD + "Vip: " + vip);
        lore.add(ChatColor.GOLD + "Duracao: " + duracao + " dias");
        meta.setLore(lore);
        ticket.setItemMeta(meta);
        return ticket;
    }


}
