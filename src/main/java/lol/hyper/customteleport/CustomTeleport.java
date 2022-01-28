package lol.hyper.customteleport;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;

public final class CustomTeleport extends JavaPlugin {

    public static final HashMap<Player, Long> cooldowns = new HashMap<>();

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("tp")).setExecutor(new Command());
    }
}
