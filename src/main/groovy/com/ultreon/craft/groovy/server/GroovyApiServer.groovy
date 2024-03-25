package com.ultreon.craft.groovy.server

import com.ultreon.craft.groovy.GroovyApi
import com.ultreon.craft.groovy.GroovyApiMod
import com.ultreon.craft.server.dedicated.DedicatedServerModInit
import com.ultreon.craft.server.events.ServerLifecycleEvents

class GroovyApiServer implements DedicatedServerModInit {
    @Override
    void onInitialize() {
        def file = new File(GroovyApiMod.hooksDir, "server-hooks.groovy")

        if (file.exists()) {
            GroovyApiMod.engine.createScript("server-hooks.groovy", new Binding(
                    $this: new GroovyApi("server-hooks.groovy"),
                    serverStarted: ServerLifecycleEvents.SERVER_STARTED,
                    serverStopped: ServerLifecycleEvents.SERVER_STOPPED,
//                    serverTick: ServerTickEvents.POST_GAME_TICK,
            )).run(file)
        }
    }
}
