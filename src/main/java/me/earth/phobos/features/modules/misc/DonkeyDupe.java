package me.earth.phobos.features.modules.misc;

import me.earth.phobos.Phobos;
import me.earth.phobos.features.command.Command;
import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.modules.player.Freecam;
import me.earth.phobos.manager.ModuleManager;
import me.earth.phobos.manager.RotationManager;
import me.earth.phobos.util.RotationUtil;

public class DonkeyDupe extends Module {
    public DonkeyDupe() {
        super("DonkeyDupe", "DonkeyDupe Module for Jballs", Module.Category.MISC, true, false, false);
    }

    public void OnEnable()
    {
        Command.sendRainbowMessage("Set Freecam Speed to 0.51");
        RotationUtil.faceYawAndPitch(90,90);
        Freecam.getInstance().enable();

    }
}
