package gay.badstagram.pridefishinglines.config

import gay.badstagram.pridefishinglines.json
import gay.badstagram.pridefishinglines.logger
import kotlinx.serialization.encodeToString
import net.fabricmc.loader.api.FabricLoader
import java.io.File
import kotlin.io.path.Path

object ConfigManager {
    fun saveConfig() {
        configFile.writeText(json.encodeToString(config))
    }


    private val configFile =
        File(Path(FabricLoader.getInstance().configDir.toString(), "pridefishinglines.json").toUri())
    private var config: PrideFishingLineConfig? = null

    init {
        if (!configFile.parentFile.exists()) {
            configFile.parentFile.mkdirs()
        }
        if (!configFile.exists()) {
            configFile.createNewFile()
            configFile.writeText(json.encodeToString(PrideFishingLineConfig.DEFAULT))
        }
        runCatching {
            config = json.decodeFromString<PrideFishingLineConfig>(configFile.readText())

            logger.debug(config?.toString())
        }
    }

    val configOrException: PrideFishingLineConfig
        get() = config ?: throw RuntimeException("Config is null")
}