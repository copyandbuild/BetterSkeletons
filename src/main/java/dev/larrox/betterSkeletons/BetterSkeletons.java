package dev.larrox.betterSkeletons;

import dev.larrox.betterSkeletons.events.SkeletonShoot;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterSkeletons extends JavaPlugin {

    private static BetterSkeletons instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new SkeletonShoot(this), this);
    }

    public static BetterSkeletons getInstance() {
        return instance;
    }
}
