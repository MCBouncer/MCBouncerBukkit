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

package com.mcbouncer.bukkit.commands;

import com.mcbouncer.api.MCBouncerImplementation;
import com.mcbouncer.commands.MCBouncerPluginCommand;
import com.mcbouncer.bukkit.BukkitUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BukkitMCBouncerPluginCommand extends MCBouncerPluginCommand implements CommandExecutor {

    public BukkitMCBouncerPluginCommand(MCBouncerImplementation plugin) {
        super(plugin);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return this.processCommand(BukkitUtils.convertCommandSender(commandSender), strings);
    }
}
