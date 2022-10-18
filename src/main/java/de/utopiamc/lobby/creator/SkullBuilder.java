package de.utopiamc.lobby.creator;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class SkullBuilder {

    private ItemStack itemStack;
    private SkullMeta skullMeta;

    public SkullBuilder(String owner) {
        itemStack = new ItemStack(Material.PLAYER_HEAD);
        skullMeta = (SkullMeta)itemStack.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
    }

    public SkullBuilder(OfflinePlayer player) {
        itemStack = new ItemStack(Material.PLAYER_HEAD);
        skullMeta = (SkullMeta)itemStack.getItemMeta();
        skullMeta.setOwningPlayer(player);
    }

    public SkullBuilder(PlayerProfile player) {
        itemStack = new ItemStack(Material.PLAYER_HEAD);
        skullMeta = (SkullMeta)itemStack.getItemMeta();
        skullMeta.setPlayerProfile(player);
    }

    public SkullBuilder(String value, String name) {
        itemStack = new ItemStack(Material.PLAYER_HEAD);
        skullMeta = (SkullMeta)itemStack.getItemMeta();
//        GameProfile profile = new GameProfile(UUID.randomUUID(), name);
//        profile.getProperties().put("textures", new Property("textures", value, ""));
//        skullMeta.setPlayerProfile(new CraftPlayerProfile(profile));
    }

    public SkullBuilder setDisplayName(String displayname) {
        skullMeta.setDisplayName(displayname);
        return this;
    }

    public SkullBuilder setLore(List<String> lore) {
        skullMeta.setLore(lore);
        return this;
    }

    public SkullBuilder setUnbreakable(Boolean unbreakable) {
        skullMeta.setUnbreakable(unbreakable);
        return this;
    }

    public SkullBuilder addLineLore(String loreLine) {
        List<String> lore;
        if (skullMeta.hasLore()){
            lore = skullMeta.getLore();
        }else {
            lore = new ArrayList<>();
        }
        lore.add(loreLine);
        skullMeta.setLore(lore);

        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

}
