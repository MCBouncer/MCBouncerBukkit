/*
 * MCBouncerBukkit
 * Copyright 2012-2014 Deaygo Jarkko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mcbouncer;

import com.mcbouncer.api.MCBouncerImplementation;
import com.mcbouncer.api.MCBouncerPlayer;
import com.mcbouncer.impl.BukkitOfflinePlayer;
import com.mcbouncer.impl.BukkitPlayer;
import com.mcbouncer.impl.commands.*;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.charset.StandardCharsets;
import java.util.UUID;


public class MCBouncerBukkit extends JavaPlugin implements MCBouncerImplementation {

    private MCBouncer mcbouncer;

    @Override
    public void onEnable() {
        mcbouncer = new MCBouncer(this, new YamlConfig(this));

        mcbouncer.getConfig().load();

        getServer().getPluginCommand("ban").setExecutor(new BukkitBanCommand(this));
        getServer().getPluginCommand("unban").setExecutor(new BukkitUnbanCommand(this));
        getServer().getPluginCommand("lookup").setExecutor(new BukkitLookupCommand(this));
        getServer().getPluginCommand("kick").setExecutor(new BukkitKickCommand(this));
        getServer().getPluginCommand("addnote").setExecutor(new BukkitNoteCommand(this));
        getServer().getPluginCommand("addglobalnote").setExecutor(new BukkitGlobalNoteCommand(this));
        //getServer().getPluginCommand("removenote")
        //getServer().getPluginCommand("banip")
        //getServer().getPluginCommand("unbanip")
        //getServer().getPluginCommand("mcbouncer")
    }

    @Override
    public void onDisable() {
    }

    public void shutdown() {
        this.getPluginLoader().disablePlugin(this);
    }

    public MCBouncerPlayer getOfflinePlayer(String s) {
        OfflinePlayer p = getServer().getOfflinePlayer(s);
        if (p != null) {
            UUID uid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + s).getBytes(StandardCharsets.UTF_8));
            if (uid.compareTo(p.getUniqueId()) == 0) {
                return null;
            }
            return new BukkitOfflinePlayer(p);
        }
        return null;
    }

    public MCBouncerPlayer[] getOnlinePlayers() {
        Player online[] = getServer().getOnlinePlayers();
        BukkitPlayer players[] = new BukkitPlayer[online.length];
        int i = 0;
        for (Player p : getServer().getOnlinePlayers()) {
            players[i] = new BukkitPlayer(p);
            i++;
        }

        return players;
    }

    public String getVersion() {
        return this.getDescription().getVersion();
    }

    public void broadcast(String permission, String message) {
        getServer().broadcast(message, permission);
    }

    public MCBouncer getMCBouncerPlugin() {
        return mcbouncer;
    }

}
