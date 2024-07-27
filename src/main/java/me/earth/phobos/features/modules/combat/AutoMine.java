package me.earth.phobos.features.modules.combat;

import me.earth.phobos.Phobos;
import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.modules.client.Managers;
import me.earth.phobos.features.setting.Setting;
import me.earth.phobos.util.BlockUtil;
import me.earth.phobos.util.EntityUtil;
import me.earth.phobos.util.MathUtil;
import me.earth.phobos.util.RotationUtil;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AutoMine
        extends Module {
    private static final Boolean ALL = null;
    private long lastPlaceTime = 0;
    private float yaw = 0.0f;
    private float pitch = 0.0f;
    public boolean rotating = false;


    public Setting<Boolean> futureAutoSelect = this.register(new Setting<Boolean>("FutureAutoSelect", false));
    public Setting<Boolean> rotate = this.register(new Setting<Boolean>("Rotate", false));
    public Setting<Boolean> autoSwitch = this.register(new Setting<Boolean>("AutoSwitch", false));
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
            String targetName = String.valueOf(targetPos.getDisplayName());

            if (BlockUtil.isBlockSolid(blockTargetPos.north(1)))
            {


                if (futureAutoSelect.getValue() == false)
                {
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                long time = System.currentTimeMillis();
                if ((time - lastPlaceTime) < delay.getValue() * 1000) return;
                lastPlaceTime = time;
                mc.playerController.onPlayerDamageBlock(blockTargetPos.north(1), EnumFacing.UP);
                rotateToPos(blockTargetPos.north(1));

                Vec3d vecTargetPosNorth = BlockUtil.posToVec3d(blockTargetPos.north(1));


            }
            else if (BlockUtil.isBlockSolid(blockTargetPos.east(1)))
            {
                if (futureAutoSelect.getValue() == false)
                {
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                long time = System.currentTimeMillis();
                if ((time - lastPlaceTime) < delay.getValue() * 1000) return;
                lastPlaceTime = time;
                mc.playerController.onPlayerDamageBlock(blockTargetPos.east(1), EnumFacing.UP);

            }
            else if (BlockUtil.isBlockSolid(blockTargetPos.west(1)))
            {
                if (futureAutoSelect.getValue() == false)
                {
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                long time = System.currentTimeMillis();
                if ((time - lastPlaceTime) < delay.getValue() * 1000) return;
                lastPlaceTime = time;
                mc.playerController.onPlayerDamageBlock(blockTargetPos.west(1), EnumFacing.UP);

            }
            else if (BlockUtil.isBlockSolid(blockTargetPos.south(1)))
            {
                if (futureAutoSelect.getValue() == false)
                {
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                long time = System.currentTimeMillis();
                if ((time - lastPlaceTime) < delay.getValue() * 1000) return;
                lastPlaceTime = time;
                mc.playerController.onPlayerDamageBlock(blockTargetPos.south(1), EnumFacing.UP);

            }
        }


    }

    private int getPickSlot () {
        for (int i = 0; i < 9; ++ i) {
            if ( mc.player.inventory.getStackInSlot ( i ).getItem ( ) != Items.DIAMOND_PICKAXE ) continue;
            return i;
        }
        return - 1;
    }

    private void rotateToPos(BlockPos pos) {
        if (this.rotate.getValue() == ALL) {

            float[] angle = MathUtil.calcAngle(AutoCrystal.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((float) pos.getX() + 0.5f, (float) pos.getY() - 0.5f, (float) pos.getZ() + 0.5f));
            Phobos.rotationManager.setPlayerRotations(angle[0], angle[1]);

            this.yaw = angle[0];
            this.pitch = angle[1];
            this.rotating = true;
        }
    }




}
