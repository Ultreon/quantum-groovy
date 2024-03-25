package com.ultreon.craft.groovy

import com.ultreon.craft.client.UltracraftClient
import com.ultreon.craft.server.UltracraftServer
import groovy.transform.BaseScript
import net.fabricmc.loader.api.FabricLoader
import org.slf4j.LoggerFactory

class GroovyApi {
    def fabricLoader = FabricLoader.getInstance()
    def logger

    def client = { -> UltracraftClient.get() }
    def server = { -> UltracraftServer.get() }

    private String scriptName

    GroovyApi(scriptName) {
        this.scriptName = scriptName
        logger = LoggerFactory.getLogger("Groovy:${scriptName}")
    }

    def getScriptName() { scriptName }
}
