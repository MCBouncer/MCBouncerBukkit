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

package com.mcbouncer.impl;

import com.mcbouncer.Perm;
import com.mcbouncer.api.MCBouncerPlayer;
import org.bukkit.entity.Player;

import java.net.InetAddress;
import java.util.UUID;

public class BukkitPlayer extends BukkitCommandSender implements MCBouncerPlayer {

    private Player player;

    public BukkitPlayer(Player player) {
        super(player);
        this.player = player;
    }

    public void kick(String s) {
        this.player.kickPlayer(s);
    }

    public UUID getUniqueID() {
        return this.player.getUniqueId();
    }

    public InetAddress getIPAddress() {
        return this.player.getAddress().getAddress();
    }

    public String getName() {
        return this.player.getName();
    }

    public Boolean hasPermission(Perm perm) {
        return this.player.hasPermission(perm.toString());
    }

    public void sendMessage(String s) {
        this.player.sendMessage(s);
    }
}
