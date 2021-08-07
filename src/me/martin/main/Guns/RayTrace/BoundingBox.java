package me.martin.main.Guns.RayTrace;

import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class BoundingBox {

    Vector min, max;

    public BoundingBox(Vector min, Vector max){

        this.min = min;
        this.max = max;

    }

    public BoundingBox(Entity target){

        AxisAlignedBB bb = ((CraftEntity) target).getHandle().getBoundingBox();
        min = new Vector(bb.a, bb.b, bb.c).subtract(new Vector(40, 40, 40));
        max = new Vector(bb.d, bb.e, bb.f).add(new Vector(40, 40, 40));

    }

}
