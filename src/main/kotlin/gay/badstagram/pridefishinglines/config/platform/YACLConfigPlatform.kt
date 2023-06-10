package gay.badstagram.pridefishinglines.config.platform

import dev.isxander.yacl3.api.ConfigCategory
import dev.isxander.yacl3.api.Option
import dev.isxander.yacl3.api.OptionDescription
import dev.isxander.yacl3.api.YetAnotherConfigLib
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder
import dev.isxander.yacl3.api.controller.EnumControllerBuilder
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
                        Option.createBuilder<Boolean>()
                                .name(Text.literal("Enabled"))
                                .description(OptionDescription.createBuilder().text(Text.literal("Enable the mod")).build())
                                .binding(true,
                                        { ConfigManager.configOrException.enabled },
                                        { ConfigManager.configOrException.enabled = it })
                                .controller { opt -> BooleanControllerBuilder.create(opt).coloured(true).yesNoFormatter() }
                                .build(),
                        Option.createBuilder<PrideFishingLineStyles>()
                                .name(Text.literal("Style"))
                                .description(OptionDescription.createBuilder().text(Text.literal("The style of the fishing line.")).build())
                                .binding(
                                        PrideFishingLineStyles.RAINBOW,
                                        { ConfigManager.configOrException.type },
                                        { ConfigManager.configOrException.type = it })
                                .controller { opt -> EnumControllerBuilder.create(opt).enumClass(PrideFishingLineStyles::class.java) }
                                .build()))

        val othersCategory = ConfigCategory.createBuilder().name(Text.literal("Others")).tooltip(Text.literal("Options for other players' fishing lines."))
                .options(listOf(
                        Option.createBuilder<Boolean>()
                                .name(Text.literal("Hide Others"))
                                .description(OptionDescription.createBuilder().text(Text.literal("Hide other players' fishing lines")).build())
                                .binding(true,
                                        { ConfigManager.configOrException.hideOthers },
                                        { ConfigManager.configOrException.hideOthers = it })
                                .controller { opt -> BooleanControllerBuilder.create(opt).coloured(true).yesNoFormatter() }
                                .build()))

        builder.categories(listOf(selfCategory.build(), othersCategory.build()))
        return builder.build().generateScreen(parent)
    }
}