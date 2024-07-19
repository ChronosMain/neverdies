package me.earth.phobos.features.modules.combat;

import me.earth.phobos.Phobos;
import me.earth.phobos.features.command.Command;
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

public class FutureFacePlace
        extends Module {

    public Setting<Integer> minDamage = this.register(new Setting<Float>("min damage", 4F, 1F, 8F));
    public Setting<Integer> defaultDamage = this.register(new Setting<Float>("default damage", 4F, 1F, 8F));


    public FutureFacePlace() {
        super("FutureFacePlace", "soma module ask him bro", Category.COMBAT, true, false, false);
    }

    public void OnEnable()
    {
        Command.sendMessage(".autocrystal mindamage " + minDamage);
    }

    public void OnDisable()
    {
        Command.sendMessage(".autocrystal mindamage " + defaultDamage);
    }
}

