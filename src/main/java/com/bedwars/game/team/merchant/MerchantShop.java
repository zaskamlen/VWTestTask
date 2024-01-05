package com.bedwars.game.team.merchant;

import com.bedwars.game.generator.ItemType;
import com.bedwars.game.item.ItemBuilder;
import com.bedwars.game.menu.Menu;
import com.bedwars.game.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MerchantShop extends Menu implements MerchantManager {

    private final ItemBuilder WOOL = new ItemBuilder(Material.WOOL).setAmount(2).setName("&eШерсть &7[2x]").setLore(
            "&7Данный предмет поможет",
            "&7вам строиться до противников",
            " ",
            "&fЦена &e1 Бронза",
            "",
            "&aНажмите чтобы купить");
    private final ItemBuilder SWORD = new ItemBuilder(Material.WOOD_SWORD).setAmount(1).setName("&eМеч &7[1x]").setLore(
            "&7Данный предмет поможет",
            "&7вам убивать ваших противников",
            "", "&fЦена &e3 Железа",
            "", "&aНажмите чтобы купить");
    private final ItemBuilder SHEARS = new ItemBuilder(Material.SHEARS).setAmount(1).setName("&eНожницы &7[1x]").setLore(
            "&7Данный предмет поможет",
            "&7вам бысрее ломать блоки",
            "", "&fЦена &e2 Железа",
            "",
            "&aНажмите чтобы купить");

    public MerchantShop() {
        super("Магазин", 27);


        setItem(12, WOOL.build());

        setItem(13, SWORD.build());

        setItem(14, SHEARS.build());

    }

    @Override
    public void onClick(int slot, ItemStack item, Player player) {

        ItemStack WOOL_BUY = WOOL.setName("&eШерсть").setLore().build();
        ItemStack SWORD_BUY = SWORD.setName("&eМеч").setLore().build();
        ItemStack SHEARS_BUY = SHEARS.setName("&eНожницы").setLore().build();

        switch (slot) {
            case 12:
                buy(player, WOOL_BUY, ItemType.BRONZE, 1);
                break;
            case 13:
                buy(player, SWORD_BUY, ItemType.IRON, 3);
                break;
            case 14:
                buy(player, SHEARS_BUY, ItemType.IRON, 2);
                break;
        }
    }


    @Override
    public void buy(Player player,ItemStack item, ItemType type, int price) {
        int itemCount = getCountItem(player,type.getItemType());

        if (itemCount >= price) {
            removeItem(player,type.getItemType(),price);
            player.getInventory().addItem(item);
            player.sendMessage(Utils.color("&aВы успешно купили " + item.getItemMeta().getDisplayName()));
        }else {
            player.sendMessage(Utils.color("&cНехватает ресурсов"));
        }
    }
}
