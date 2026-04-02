package dev.slay;

import org.bukkit.inventory.ItemStack;

public class Ticket {

    private int Id;
    private ItemStack Item;
    private String Vip;
    private int VipDuracao;


    public Ticket(int id, ItemStack item, String vip, int vipDuracao) {
        setItem(item);
        setId(id);
        setVip(vip);
        setVipDuracao(vipDuracao);
    }

    public int getVipDuracao() {
        return VipDuracao;
    }

    public void setVipDuracao(int vipDuracao) {
        VipDuracao = vipDuracao;
    }

    public String getVip() {
        return Vip;
    }

    public void setVip(String vip) {
        Vip = vip;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public ItemStack getItem() {
        return Item;
    }

    public void setItem(ItemStack item) {
        Item = item;
    }
}
