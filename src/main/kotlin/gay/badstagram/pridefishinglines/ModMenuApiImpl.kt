package gay.badstagram.pridefishinglines

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import com.terraformersmc.modmenu.gui.ModsScreen
import gay.badstagram.pridefishinglines.config.platform.YACLConfigPlatform
import gay.badstagram.pridefishinglines.screen.ConfigLibMissingScreen
import net.fabricmc.loader.api.FabricLoader

class ModMenuApiImpl : ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory {
            if (FabricLoader.getInstance().isModLoaded("yet-another-config-lib")) {
                YACLConfigPlatform.buildScreen(ModsScreen(null))
            } else {
                ConfigLibMissingScreen()
            }
        }
    }
}