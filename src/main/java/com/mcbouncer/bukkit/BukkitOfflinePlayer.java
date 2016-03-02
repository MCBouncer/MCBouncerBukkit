/*
 * mcbouncer
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

package com.mcbouncer.bukkit;

import com.mcbouncer.Perm;
import com.mcbouncer.api.MCBouncerPlayer;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import java.net.InetAddress;
import java.util.UUID;

public class BukkitOfflinePlayer implements MCBouncerPlayer {

    private OfflinePlayer offlinePlayer;

    public BukkitOfflinePlayer(OfflinePlayer player) {
        this.offlinePlayer = player;
    }

    @Override
    public void kick(String s) {
        if (this.offlinePlayer.isOnline()) {
            this.offlinePlayer.getPlayer().kickPlayer(s);
        }
    }

    @Override
    public UUID getUniqueID() {
        return this.offlinePlayer.getUniqueId();
    }

    @Override
    public InetAddress getIPAddress() {
        if (this.offlinePlayer.isOnline()) {
            return this.offlinePlayer.getPlayer().getAddress().getAddress();
        }

        return null;
    }

    @Override
    public boolean isOnline() {
        return this.offlinePlayer.isOnline();
    }

    @Override
    public String getName() {
        return this.offlinePlayer.getName();
    }

    @Override
    public Boolean hasPermission(Perm perm) {
        if (this.offlinePlayer.isOnline()) {
            return this.offlinePlayer.getPlayer().hasPermission(perm.toString());
        }

        return false;
    }

    @Override
    public void sendMessage(String s) {
        if (this.offlinePlayer.isOnline()) {
            this.offlinePlayer.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
        }
    }
}
