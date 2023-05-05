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
            YetAnotherConfigLib.createBuilder().title(Text.literal("Pride Fishing Lines")).save(ConfigManager::saveConfigToFile)

        val selfCategory = ConfigCategory.createBuilder().name(Text.literal("Self")).tooltip(Text.literal("Options for your own fishing line."))
            .options(listOf(
                Option.createBuilder(Boolean::class.java)
                    .name(Text.literal("Enabled"))
                    .tooltip(Text.literal("Enable the mod."))
                    .binding(true,
                    { ConfigManager.configOrException.enabled },
                    { ConfigManager.configOrException.enabled = it }).controller { opt ->
                    BooleanController(
                        opt, BooleanController.YES_NO_FORMATTER, true
                    )
                }.build(),
                Option.createBuilder(PrideFishingLineStyles::class.java)
                    .name(Text.literal("Style"))
                    .tooltip(Text.literal("The style of the fishing line."))
                    .binding(
                        PrideFishingLineStyles.RAINBOW,
                        { ConfigManager.configOrException.type },
                        { ConfigManager.configOrException.type = it }).controller { option ->
                        EnumController(
                            option,
                        )
                    }.build()))

        val othersCategory = ConfigCategory.createBuilder().name(Text.literal("Others")).tooltip(Text.literal("Options for other players' fishing lines."))
            .options(listOf(
                Option.createBuilder(Boolean::class.java)
                    .name(Text.literal("Hide Others"))
                    .tooltip(Text.literal("Hide other players' fishing lines."))
                    .binding(true,
                    { ConfigManager.configOrException.hideOthers },
                    { ConfigManager.configOrException.hideOthers = it }).controller { opt ->
                    BooleanController(
                        opt, BooleanController.YES_NO_FORMATTER, true
                    )
                }.build()))

        builder.categories(listOf(selfCategory.build(), othersCategory.build()))
        return builder.build().generateScreen(parent)
    }
}