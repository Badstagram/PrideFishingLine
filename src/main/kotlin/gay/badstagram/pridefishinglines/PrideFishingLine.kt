package gay.badstagram.pridefishinglines

import kotlinx.serialization.json.Json
import net.fabricmc.api.ModInitializer
import net.minecraft.client.MinecraftClient
import org.slf4j.LoggerFactory


val json = Json {
    prettyPrint = true
    encodeDefaults = true
}

val logger = LoggerFactory.getLogger("pridefishinglines")

val minecraft: MinecraftClient
    get() = MinecraftClient.getInstance()

class PrideFishingLine : ModInitializer {
    /**
     * Runs the mod initializer.
     */
    override fun onInitialize() {}
}
