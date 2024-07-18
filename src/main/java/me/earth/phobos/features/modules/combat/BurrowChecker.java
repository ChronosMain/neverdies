package me.earth.phobos.features.modules.combat;

import me.earth.phobos.features.modules.Module;
import me.earth.phobos.util.BlockUtil;
import me.earth.phobos.util.EntityUtil;
import me.earth.phobos.util.PlayerUtil;
import me.earth.phobos.util.RenderUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

public class BurrowChecker
        extends Module {
    public BurrowChecker() {
        super("BurrowChecker", "Check if Player is in a Burrow", Category.RENDER, true, false, false);
    }

    @Override
    public void onTick() {
        Entity targetPos = EntityUtil.getClosestEnemy(20);
        if (!(targetPos == null))
        {
            if (BlockUtil.isBlockSolid(targetPos.getPosition()))
            {
                RenderUtil.drawText(targetPos.getPosition().up(3), "I am Burrowed");
            }
        }

    }


}

