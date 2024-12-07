package gay.badstagram.pridefishinglines

import kotlinx.serialization.json.Json
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory


val json = Json {
	prettyPrint = true
	encodeDefaults = true
}

val logger: Logger = LoggerFactory.getLogger("pridefishinglines")



class PrideFishingLines : ModInitializer {

	override fun onInitialize() {
		logger.info("Mod initialized")
	}
}