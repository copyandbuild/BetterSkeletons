<h1 align=center>BetterSkeletons</h1>
<h2 align=center>🦴 BetterSkeletons affects how skeletons behave – for better or worse.</h2>

---

### 🔍 **Overview**

This plugin hooks into Minecraft’s event system to intercept whenever a **Skeleton** entity fires an arrow. Based on random probabilities, it either makes the arrow more accurate (like an aimbot) or deliberately introduces inaccuracy (to simulate poor aim). This adds variety and unpredictability to Skeleton attacks.

---

### ⚙️ **Detailed Breakdown**

1. **Event Listening & Entity Filtering**

   * The plugin listens for the Bukkit `EntityShootBowEvent`, which is triggered whenever any entity shoots a bow.
   * It filters the event to only proceed if the shooter is a `Skeleton` and the projectile is an `Arrow`.

2. **Randomized Behavior**

   * Two random checks introduce variability:

     * **First Random Check (`random1 < 10`)**: In 10% of cases, the plugin does nothing — the arrow behaves normally.
     * **Second Random Check (`random2 < 50`)**: If the first check passes, this introduces a 50% chance that the Skeleton will "aimbot" the target (if it's a player).

3. **"Aimbot" Mode**

   * If the skeleton's target is a `Player`, and the second random check passes:

     * The plugin waits **1 tick** (using `runTaskLater`) to ensure the arrow has spawned properly.
     * It then **calculates a direction vector** from the arrow's current position to the player’s current position.
     * The arrow's velocity is **manually adjusted** to point in that direction, scaled by a factor (1.5), effectively making it a more accurate, faster shot.
     * This simulates a "lock-on" effect.

4. **"Inaccuracy" Mode**

   * If the "aimbot" mode isn't triggered, the plugin instead **adds a random Vector offset** to the arrow’s current velocity.

     * This results in a less accurate shot, introducing some "scatter" into the skeleton’s firing pattern.
     * The randomness is subtle: values between `-0.1` and `0.1` on each axis.

---

### 🧠 **Technical Notes**

* **Vector Manipulation**:

  * The plugin makes direct use of vector math to alter arrow trajectories post-fire.
  * This approach gives granular control over how the arrow moves in 3D space.

* **Delayed Execution**:

  * The 1-tick delay before modifying the arrow's velocity ensures the arrow entity is fully initialized, preventing issues with premature state access.

* **Customization Potential**:

  * Developers could easily tweak the random thresholds or velocity factors to make skeletons smarter, dumber, or just more chaotic.
  * Could also extend to other mobs or projectile types (e.g., Strays, Pillagers, etc.)

---

### 👋 About the Project

I created this plugin just for fun and don’t plan to actively develop it further. I might update it to support newer Minecraft versions in the future, but don’t expect any major changes.
