package com.ultreon.craft.groovy

import com.ultreon.craft.ModInit

class GroovyApiMod implements ModInit {
    static final def MOD_ID = "groovy_api"
    private static GroovyScriptEngine engine
    private static File hooksDir

    @Override
    void onInitialize() {
        println "Hello from Groovy! Mod ID: $MOD_ID"

        engine = new GroovyScriptEngine(new URL[]{new File("groovy-hooks").toURI().toURL()}, getClass().classLoader)

        hooksDir = new File("groovy-hooks")

        if (!hooksDir.exists()) {
            hooksDir.mkdirs()
        }
    }

    static def getEngine() { engine }

    static def getHooksDir() { hooksDir }
}
