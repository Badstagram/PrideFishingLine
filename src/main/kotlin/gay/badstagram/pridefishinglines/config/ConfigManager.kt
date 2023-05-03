package gay.badstagram.pridefishinglines.config

import gay.badstagram.pridefishinglines.json
import gay.badstagram.pridefishinglines.logger
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.io.File

object ConfigManager {
    fun saveConfigToFile() {
        configFile.writeText(json.encodeToString(config))
    }


    private val configFile = File(System.getProperty("user.dir") + "/config", "pride_fishing_lines.json")
    private var config: PrideFishingLinesConfig? = null

    val configOrException: PrideFishingLinesConfig
        get() = config ?: throw RuntimeException("Config is null")

    init {
        if(!configFile.parentFile.exists()) {
            configFile.parentFile.mkdirs()
        }
        if(!configFile.exists()) {
            configFile.createNewFile()
            configFile.writeText(json.encodeToString(PrideFishingLinesConfig.DEFAULT))
        }
        runCatching {
            config = json.decodeFromString<PrideFishingLinesConfig>(configFile.readText())

            logger.debug(config?.toString())
        }
    }
}