package dev.larrox.betterSkeletons.events;

import dev.larrox.betterSkeletons.BetterSkeletons;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.util.Vector;

public class SkeletonShoot implements Listener {

    private final BetterSkeletons plugin;

    public SkeletonShoot(BetterSkeletons plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSkeletonShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Skeleton skeleton)) return;
        if (!(event.getProjectile() instanceof Arrow arrow)) return;

        int random1 = (int) (Math.random() * 100);
        if (random1 < 10) {
            return;
        }

        int random2 = (int) (Math.random() * 100);
        if (random2 < 50) {
            if (skeleton.getTarget() instanceof Player target) {
                //Bukkit.broadcastMessage("§b§lBetterSkeletons §8» §7Aimbot activated!");
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    Vector direction = target.getLocation().toVector().subtract(arrow.getLocation().toVector()).normalize();
                    arrow.setVelocity(direction.multiply(1.5)); // Zielgenauer machen
                }, 1L);
                return;
            }
        }

        Vector original = arrow.getVelocity();
        //Bukkit.broadcastMessage("§b§lBetterSkeletons §8» §7No Aim.");
        Vector modified = original.add(new Vector(
                (Math.random() - 0.5) * 0.2,
                (Math.random() - 0.5) * 0.2,
                (Math.random() - 0.5) * 0.2
        ));

        arrow.setVelocity(modified);
    }
}