package de.kainianer.mmo.managers;

import de.kainianer.mmo.item.Armor;
import de.kainianer.mmo.item.BonusSpell;
import de.kainianer.mmo.item.CustomItem;
import de.kainianer.mmo.item.Rarity;
import de.kainianer.mmo.item.Stat;
import de.kainianer.mmo.item.Stat.StatType;
import de.kainianer.mmo.item.Weapon;
import de.kainianer.mmo.item.WeaponType;
import de.kainianer.mmo.util.DatabaseUtil;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Material;

public class ItemManager {

    public static ItemManager instance = new ItemManager();
    private Map<String, CustomItem> itemList;

    public ItemManager() {
        this.itemList = new HashMap<>();
        for (CustomItem item : this.getItems()) {
            this.itemList.put(item.getFormattedName(), item);
        }
    }

    public Map<String, CustomItem> getItemList() {
        return this.itemList;
    }

    public static ItemManager getInstance() {
        return instance;
    }

    public List<Weapon> getWeapons() throws SQLException {
        ResultSet set = DatabaseUtil.getResultSet("SELECT * FROM weapon");
        List<Weapon> list = new ArrayList<>();
        while (set.next()) {
            String name = set.getString(1);
            WeaponType type = WeaponType.valueOf(set.getString(2));
            Rarity rarity = Rarity.valueOf(set.getString(3));
            int minschaden = set.getInt(4);
            //int maxschaden = set.getInt(5);
            int zaubsch = set.getInt(6);
            int level = set.getInt(7);

            String spell1 = set.getString(8);
            String spell2 = set.getString(9);
            String spell3 = set.getString(10);
            List<BonusSpell> spells = new ArrayList<>();
            for (String str : Arrays.asList(spell1, spell2, spell3)) {
                if (!"".equals(str) && str != null) {
                    spells.add(BonusSpell.valueOf(str));
                }
            }
            String lore = set.getString(11);
            List<Stat> stats = Arrays.asList(new Stat(StatType.SCHADEN, minschaden), new Stat(StatType.ZAUBERSCH, zaubsch), new Stat(StatType.LEVEL, level));
            Weapon w = new Weapon(type, name, rarity, stats, spells, lore);
            list.add(w);
        }
        return list;
    }

    public List<Armor> getArmor() throws SQLException {
        ResultSet set = DatabaseUtil.getResultSet("SELECT * FROM armor");
        List<Armor> list = new ArrayList<>();
        while (set.next()) {
            String name = set.getString(1);
            Material mat = Material.getMaterial(set.getString(2));
            Rarity rarity = Rarity.valueOf(set.getString(3));
            Stat lebenreg = new Stat(StatType.LEBENSREG, set.getDouble(4));
            Stat hungerreg = new Stat(StatType.HUNGERREG, set.getDouble(5));
            Stat ruestung = new Stat(StatType.RÜSTUNG, set.getDouble(6));
            Stat erfahrung = new Stat(StatType.ERFAHRUNG, set.getDouble(7));
            Stat leben = new Stat(StatType.LEBEN, set.getDouble(8));
            Stat level = new Stat(StatType.LEVEL, set.getDouble(9));
            String lore = set.getString(10);
            Armor a = new Armor(mat, name, rarity, Arrays.asList(lebenreg, hungerreg, erfahrung, leben, level), lore);
            list.add(a);
        }
        return list;
    }

    private List<CustomItem> getItems() {
        List<CustomItem> list = new ArrayList<>();
        try {
            list.addAll(this.getWeapons());
            list.addAll(this.getArmor());
        } catch (SQLException ex) {
            Logger.getLogger(ItemManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
}
