package me.earth.phobos.features.modules.misc;

import me.earth.phobos.Phobos;
import me.earth.phobos.features.command.Command;
import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.modules.player.Freecam;
import me.earth.phobos.manager.ModuleManager;
import me.earth.phobos.manager.RotationManager;
import me.earth.phobos.util.MovementUtil;
import me.earth.phobos.util.RotationUtil;
import me.earth.phobos.util.Timer;
import org.spongepowered.asm.mixin.transformer.ClassInfo;

public class DonkeyDupe extends Module {

    private static DonkeyDupe INSTANCE = new DonkeyDupe();

    static {
        DonkeyDupe.INSTANCE = new DonkeyDupe();
    }
    public DonkeyDupe() {
        super("DonkeyDupe", "DonkeyDupe Module for Jballs", Category.MISC, true, false, false);
    }

    public static DonkeyDupe getInstance() {
        if (DonkeyDupe.INSTANCE == null) {
            DonkeyDupe.INSTANCE = new DonkeyDupe();
        }
        return DonkeyDupe.INSTANCE;
    }

    private void setInstance() {

        DonkeyDupe.INSTANCE = this;
    }

    @Override
    public void onEnable() {
        Command.sendMessage("Setting Freecam Speed to 0.51", true);
        Phobos.rotationManager.setPlayerPitch(180);
        Phobos.rotationManager.setPlayerYaw(180);
        try {
            Phobos.timerManager.wait(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Freecam.getInstance().enable();
        Freecam.getInstance().getSettingByName("speed").setValue(0.51);
    }

    @Override
    public void onDisable() {
        if (!(Freecam.getInstance().isDisabled()))
        {
            Freecam.getInstance().disable();
        }
    }
}
