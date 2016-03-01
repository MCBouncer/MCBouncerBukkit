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

import com.mcbouncer.api.MCBouncerPlayerLoginEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class BukkitUtils {
    public static BukkitCommandSender convertCommandSender(CommandSender commandSender) {
        BukkitCommandSender cs;
        if (commandSender instanceof Player) {
            cs = new BukkitPlayer((Player)commandSender);
        }  else {
            cs = new BukkitCommandSender(commandSender);
        }

        return cs;
    }

    public static MCBouncerPlayerLoginEvent toMCBPlayerLoginEvent(AsyncPlayerPreLoginEvent event) {
        return new MCBouncerPlayerLoginEvent(event.getName(), event.getAddress(), event.getUniqueId());
    }

    public static void fromMCBPlayerLoginEvent(MCBouncerPlayerLoginEvent mcb, AsyncPlayerPreLoginEvent event) {
        if (mcb.isDisallowed()) {
            switch (mcb.getReason()) {
                case KICK_BANNED:
                    event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, mcb.getKickMessage());
                    break;
                case KICK_OTHER:
                default:
                    event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, mcb.getKickMessage());
                    break;
            }
        }
        else {
            event.allow();
        }
    }
}
