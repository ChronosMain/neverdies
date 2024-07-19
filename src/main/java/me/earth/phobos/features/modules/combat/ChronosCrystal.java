package me.earth.phobos.features.modules.misc;

import me.earth.phobos.Phobos;
import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.setting.Setting;
import me.earth.phobos.util.*;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.awt.*;
import java.io.IOException;

public class ChronosCrystal
        extends Module {


    private long lastPlaceTime = 0;
    private final Setting<Float> range = this.register(new Setting<Float>("PlaceRange", Float.valueOf(6.0f), Float.valueOf(0.0f), Float.valueOf(10.0f)));
    private final Setting<Float> delay = this.register(new Setting<Float>("Delay", Float.valueOf(6.0f), Float.valueOf(0.0f), Float.valueOf(10.0f)));
    public Setting<Boolean> placeCrystal = this.register(new Setting<Boolean>("Place", false));
    public Setting<Boolean> swing = this.register(new Setting<Boolean>("Swing", false));
    public Setting<Boolean> breakCrystal = this.register(new Setting<Boolean>("Break", false));
    public Setting<Boolean> rotate = this.register(new Setting<Boolean>("Rotate", false));

    public ChronosCrystal() {
        super("ChronosCrystal", "Always on", Category.COMBAT, true, false, false);
    }

    @Override
    public void onTick() {


        Entity target = EntityUtil.getClosestEnemy(range.getValue());

        if (!(target == null))
        {
            BlockPos targetPos = target.getPosition();

            if (BlockUtil.canPlaceCrystal(targetPos.north(1).down(1)))
            {
                if (placeCrystal.getValue())
                {
                    InventoryUtil.switchToHotbarSlot(ItemEndCrystal.class, false);
                    BlockUtil.placeCrystalOnBlock(targetPos.north(1).down(1), EnumHand.MAIN_HAND, true, true);
                    if (breakCrystal.getValue())
                    {
                        //Entity crystal = mc.world.getEntityByID(getEntityIdAtPosition(targetPos.north(1).down(1)));
                        RenderUtil.drawBoxESP(targetPos.north(1).down(1), new Color(255, 0, 0), true, new Color(255, 0, 0), 1.0f, true, true, 1, true);
                        //attackEntity(crystal);
                        if (swing.getValue()) {
                            mc.player.swingArm(EnumHand.MAIN_HAND);
                        }
                        }

                }
            }


        }

    }
    private int getEntityIdAtPosition(BlockPos pos) {
        for (Entity entity : mc.world.getEntitiesWithinAABBExcludingEntity(null, new net.minecraft.util.math.AxisAlignedBB(pos))) {
            if (entity.getPosition().equals(pos)) {
                return entity.getEntityId();
            }
        }
        return -1;
    }
    private void attackEntity(Entity entity) {
        mc.player.connection.sendPacket(new CPacketUseEntity(entity, EnumHand.MAIN_HAND));
    }
}

