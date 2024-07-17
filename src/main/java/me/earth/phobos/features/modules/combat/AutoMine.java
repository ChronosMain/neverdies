package me.earth.phobos.features.modules.combat;

import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.setting.Setting;
import me.earth.phobos.util.BlockUtil;
import me.earth.phobos.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AutoMine
        extends Module {
    public Setting<Boolean> futureAutoSelect = this.register(new Setting<Boolean>("FutureAutoSelect", false));
    public Setting<Boolean> rotate = this.register(new Setting<Boolean>("Rotate", false));
    public Setting<Integer> range = this.register(new Setting<Integer>("Range", 4, 1, 8));
    public Setting<Integer> delay = this.register(new Setting<Integer>("Delay", 0, 0, 50));

    public AutoMine() {
        super("AutoMine", "automatically mine", Module.Category.COMBAT, true, false, false);
    }

    @Override
    public void onTick() {
        Entity targetPos = EntityUtil.getClosestEnemy(range.getValue());


        if (!(targetPos == null))
        {
            BlockPos blockTargetPos = targetPos.getPosition();

            if (BlockUtil.isBlockSolid(blockTargetPos.north(1)))
            {
                if (futureAutoSelect.getValue() == false)
                {
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                mc.playerController.onPlayerDamageBlock(blockTargetPos.north(1), EnumFacing.UP);
                Vec3d vecTargetPosNorth = BlockUtil.posToVec3d(blockTargetPos.north(1));
                System.out.println("north");

            }
            else if (BlockUtil.isBlockSolid(blockTargetPos.east(1)))
            {
                if (futureAutoSelect.getValue() == false)
                {
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                mc.playerController.onPlayerDamageBlock(blockTargetPos.east(1), EnumFacing.UP);

            }
            else if (BlockUtil.isBlockSolid(blockTargetPos.west(1)))
            {
                if (futureAutoSelect.getValue() == false)
                {
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                mc.playerController.onPlayerDamageBlock(blockTargetPos.west(1), EnumFacing.UP);

            }
            else if (BlockUtil.isBlockSolid(blockTargetPos.south(1)))
            {
                if (futureAutoSelect.getValue() == false)
                {
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                mc.playerController.onPlayerDamageBlock(blockTargetPos.south(1), EnumFacing.UP);

            }     }

    }
}
