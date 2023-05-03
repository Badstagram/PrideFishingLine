package gay.badstagram.pridefishinglines.config.platform

import dev.isxander.yacl.api.ConfigCategory
import dev.isxander.yacl.api.Option
import dev.isxander.yacl.api.YetAnotherConfigLib
import dev.isxander.yacl.gui.controllers.BooleanController
import dev.isxander.yacl.gui.controllers.cycling.EnumController
import gay.badstagram.pridefishinglines.config.ConfigManager
import gay.badstagram.pridefishinglines.config.PrideFishingLineStyles
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

object YACLConfigPlatform {
    fun buildScreen(parent: Screen?): Screen {
        val builder =
            YetAnotherConfigLib.createBuilder().title(Text.literal("foo")).save(ConfigManager::saveConfigToFile)

        val styleCategory = ConfigCategory.createBuilder().name(Text.literal("bar"))
            .options(listOf(Option.createBuilder(Boolean::class.java).name(Text.literal("enabled")).binding(true,
                    { ConfigManager.configOrException.enabled },
                    { ConfigManager.configOrException.enabled = it }).controller { opt ->
                    BooleanController(
                        opt, BooleanController.YES_NO_FORMATTER, true
                    )
                }.build(),
                Option.createBuilder(PrideFishingLineStyles::class.java).name(Text.literal("Style")).binding(
                        PrideFishingLineStyles.RAINBOW,
                        { ConfigManager.configOrException.type },
                        { ConfigManager.configOrException.type = it }).controller { option ->
                        EnumController(
                            option,
                        )
                    }.build()))

        builder.categories(listOf(styleCategory.build()))
        return builder.build().generateScreen(parent)
    }
}