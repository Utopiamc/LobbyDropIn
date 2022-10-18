package de.utopiamc.lobby.commands;

import de.utopiamc.framework.api.commands.descriptors.MapRoute;
import de.utopiamc.framework.api.stereotype.Command;
import de.utopiamc.lobby.creator.ItemBuilder;
import de.utopiamc.lobby.creator.SkullBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Command(value = "profil", aliases = {"profile", "p"})
public class ProfileCommand {

    public static final String INVENTORYNAME = "§8Mein Profil";
    public static final String BACK = "§8Zurück";

    public static final String PLAYERSKULL = "§8Dein Profil";
    public static final String FRIENDS = "§8Freunde";
    public static final String PARTY = "§8Party";
    public static final String CLAN = "§8Clan";
    public static final String KUERTZLICH = "§8Kürzlich gespielt";
    public static final String LINK = "§2Verlinkte Kontos";
    public static final String STATS = "§8Deine Stats ansehen";
    public static final String SETTINGS = "§8Einstellungen";
    public static final String USERACCS = "§8Deine Konten";
    public static final String IMAGE = "§8Erscheinungs Bild";
    public static final String BANNS = "§8Deine Banns ansehen";

    public static final String Discord = "§8Discord";
    public static final String WEB = "§aForum§7/§cWebseite";

    public static final String LINKT = "§aVebunden";
    public static final String UNLINKT = "§cNicht Verbunden";

    @MapRoute("")
    public void profile(Player player) {
        openInv(player);
    }

    public static void openInv(Player p){
        Inventory inv = Bukkit.createInventory(null, 9+9+9+9+9+9, INVENTORYNAME);

//        ItemStack s = SkullBuilder.createPlayerSkull(p.getDisplayName());
//        SkullMeta sm = (SkullMeta) s.getItemMeta();
//        sm.setDisplayName(PLAYERSKULL);
//        s.setItemMeta(sm);

//        inv.setItem(47, s);
        inv.setItem(48, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTQ0MTNiZGIyYTdlYTI3Y2YzMDNkNzk5ZGRjNjI3ZDZmZWVkNzFiMDE4NzZiOWIxYjBlMDEzYThlMWM2MjQifX19", FRIENDS).setDisplayName(FRIENDS).build());
        inv.setItem(49, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmYzYzRiZDZmZDRlMTVkZmZhYzM0NjgwYTc2NmI4YTJiNWQ1MjM0NTFjYmQzZjk4MzQyOTRjYzhmMmM5MmJmIn19fQ==", PARTY).setDisplayName(PARTY).build());
        inv.setItem(50, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTNjZjUzYzg0N2UyYzQ4NWQ0MjFjYTNiMmY1YmJiYTQzOWRhMTkwNjQyYTE4OGMyMjhiMjc5YjNmYWM1MmQifX19", CLAN).setDisplayName(CLAN).build());
        inv.setItem(51, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE5OGZjZTdlYWZkYWNhMTViZDZlOThjYTk0YjQwNDY1ODNhOGIyZTU0MTFjYzhjOTQ0NTg4ZmQ3ZjFhNWY5NyJ9fX0=", KUERTZLICH).setDisplayName(KUERTZLICH).build());

        ItemStack wand = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§9").build();

        inv.setItem(36, wand);
        inv.setItem(37, wand);
        inv.setItem(38, wand);
        inv.setItem(39, wand);
        inv.setItem(40, wand);
        inv.setItem(41, wand);
        inv.setItem(42, wand);
        inv.setItem(43, wand);
        inv.setItem(44, wand);

        inv.setItem(13, new ItemBuilder(Material.OAK_SAPLING).setDisplayName(LINK).addLineLore("§7Hier kannst du deinen").addLineLore("§7Minecraft Konto verknüpfen mit").addLineLore("§7» Discord").addLineLore("§7» Forum/Webseite").addLineLore("").addLineLore("§e§oKlick").build());
        inv.setItem(21, new ItemBuilder(Material.PAPER).setDisplayName(STATS).build());
        inv.setItem(22, new ItemBuilder(Material.COMPARATOR).setDisplayName(SETTINGS).build());
        //inv.setItem(23, new SkullBuilder("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmFkNDZhNDIyYWU1OTYwM2ZkODg5YzI1MzQ0ZmY2N2JjODQzYWY4ZWU1MTg5MzJjMmUyYWQwN2NkYmY5MzliMyJ9fX0=", USERACCS).setDisplayName(USERACCS).build());
        inv.setItem(29, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setDisplayName(IMAGE).build());
        inv.setItem(30, new ItemBuilder(Material.STONE_BUTTON).setDisplayName("§8§lSoon").build());
        inv.setItem(31, new ItemBuilder(Material.ANVIL).setDisplayName(BANNS).build());
        inv.setItem(32, new ItemBuilder(Material.STONE_BUTTON).setDisplayName("§8§lSoon").build());
        inv.setItem(33, new ItemBuilder(Material.STONE_BUTTON).setDisplayName("§8§lSoon").build());

        p.openInventory(inv);
    }

}
