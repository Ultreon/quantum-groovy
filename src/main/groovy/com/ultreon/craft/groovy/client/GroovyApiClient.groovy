package com.ultreon.craft.groovy.client

import com.ultreon.craft.client.ClientModInit
import com.ultreon.craft.client.UltracraftClient
import com.ultreon.craft.client.api.events.ClientLifecycleEvents
import com.ultreon.craft.client.api.events.ClientTickEvents
import com.ultreon.craft.groovy.GroovyApi
import com.ultreon.craft.groovy.GroovyApiMod
import com.ultreon.craft.server.UltracraftServer
import net.fabricmc.loader.api.FabricLoader
import org.slf4j.LoggerFactory

class GroovyApiClient implements ClientModInit {
    @Override
    void onInitializeClient() {
        def file = new File(GroovyApiMod.hooksDir, "client-hooks.groovy")

        if (file.exists()) {
            GroovyApiMod.engine.createScript("client-hooks.groovy", new Binding(
                    logger: LoggerFactory.getLogger("Groovy:client-hooks"),

                    getClient: { -> UltracraftClient.get() },
                    getServer: { -> UltracraftServer.get() },

                    fabricLoader: FabricLoader.getInstance(),

                    getScriptName: { -> "client-hooks.groovy" },

                    clientStarted: ClientLifecycleEvents.CLIENT_STARTED,
                    clientStopped: ClientLifecycleEvents.WINDOW_CLOSED,
                    clientTick: ClientTickEvents.POST_GAME_TICK,
            )).run(file)
        }
    }
}
