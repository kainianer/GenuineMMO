package de.kainianer.genuine.util;

import de.kainianer.genuine.MainMMO;
import de.kainianer.genuine.item.Armor;
import de.kainianer.genuine.item.BonusSpell;
import de.kainianer.genuine.item.CustomItem;
import de.kainianer.genuine.item.Gem;
import de.kainianer.genuine.item.Rarity;
import de.kainianer.genuine.item.Stat;
import de.kainianer.genuine.item.Stat.StatType;
import de.kainianer.genuine.item.Weapon;
import de.kainianer.genuine.item.WeaponType;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemLoader {

    /**
     * public static void saveOffer(Offer offer) throws IOException {
     *
     * File shop = new File(Shop.shop.getDataFolder(), "/shop/shop.yml");
     * FileConfiguration offers = YamlConfiguration.loadConfiguration(shop); int
     * counter = offers.getInt("counter") + 1;
     *
     * File indiFile = new File(Shop.shop.getDataFolder(), "/shop/indi.yml");
     * FileConfiguration indip = YamlConfiguration.loadConfiguration(indiFile);
     *
     * offers.set("counter", counter); offers.set("auction." + counter +
     * ".itemStack", offer.getItemStack()); offers.set("auction." + counter +
     * ".price", offer.getOffer_price()); offers.set("auction." + counter +
     * ".amount", offer.getOffer_amount()); offers.set("auction." + counter +
     * ".seller", offer.getPlayer());
     *
     * Indi indi = new Indi(offer.getPlayer(),
     * indip.getIntegerList(offer.getPlayer()));
     * indi.getOfferList().add(counter); indip.set(indi.getName(),
     * indi.getOfferList());
     *
     * offers.save(shop); indip.save(indiFile); }
     *
     */
    private final File items;
    private final FileConfiguration itemConf;

    public ItemLoader() {
        this.items = new File(MainMMO.getInstance().getDataFolder(), "/items.yml");
        this.itemConf = YamlConfiguration.loadConfiguration(items);
    }

    private Weapon getWeapon(String string) {
        WeaponType type = WeaponType.valueOf(this.itemConf.getString(string + ".type"));
        Rarity rarity = Rarity.valueOf(this.itemConf.getString(string + ".rarity"));
        String name = this.itemConf.getString(string + ".name");
        String lore = this.itemConf.getString(string + ".lore");

        List<Stat> statList = new ArrayList<>();
        for (String str : this.itemConf.getConfigurationSection(string + ".stats").getKeys(false)) {
            int value = this.itemConf.getInt(string + ".stats." + str);
            if (value > 0) {
                statList.add(new Stat(StatType.valueOf(str), value));
            }
        }

        List<BonusSpell> spells = new ArrayList<>();
        for (String str : this.itemConf.getStringList(string + ".spells")) {
            spells.add(BonusSpell.valueOf(str));
        }
        return new Weapon(type, name, rarity, statList, spells, lore);
    }

    private Armor getArmor(String string) {
        Material mat = Material.getMaterial(this.itemConf.getString(string + ".type"));
        Rarity rarity = Rarity.valueOf(this.itemConf.getString(string + ".rarity"));
        String name = this.itemConf.getString(string + ".name");
        String lore = this.itemConf.getString(string + ".lore");

        List<Stat> statList = new ArrayList<>();
        for (String str : this.itemConf.getConfigurationSection(string + ".stats").getKeys(false)) {
            int value = this.itemConf.getInt(string + ".stats." + str);
            if (value > 0) {
                statList.add(new Stat(StatType.valueOf(str), value));
            }
        }
        return new Armor(mat, name, rarity, statList, lore);
    }

    private Gem getGem(String string) {
        String mat = this.itemConf.getString(string + ".material");
        String name = this.itemConf.getString(string + ".name");
        String spell = this.itemConf.getString(string + ".spell");
        return new Gem(Material.getMaterial(mat), name, BonusSpell.valueOf(spell));
    }

    public List<CustomItem> getItems() {
        List<CustomItem> list = new ArrayList<>();
        for (String key : this.itemConf.getConfigurationSection("weapon").getKeys(false)) {
            list.add(this.getWeapon("weapon." + key));
        }
        for (String key : this.itemConf.getConfigurationSection("armor").getKeys(false)) {
            list.add(this.getArmor("armor." + key));
        }
        for (String key : this.itemConf.getConfigurationSection("gem").getKeys(false)) {
            list.add(this.getGem("gem." + key));
        }
        return list;
    }
}
