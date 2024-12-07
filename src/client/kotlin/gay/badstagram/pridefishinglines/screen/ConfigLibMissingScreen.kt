package gay.badstagram.pridefishinglines.screen

import net.minecraft.client.MinecraftClient
import it.unimi.dsi.fastutil.booleans.BooleanConsumer
import net.minecraft.client.gui.screen.ConfirmScreen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text
import net.minecraft.util.Util
import java.io.File

class ConfigLibMissingScreen : ConfirmScreen(
    BooleanConsumer {
        val url = when(it) {
            true -> "https://modrinth.com/mod/yacl"
            false -> "https://www.curseforge.com/minecraft/mc-mods/yacl"
        }
        Util.getOperatingSystem().open(url)
    },
    Text.literal("Config Library missing"),
    Text.literal("Pride Fishing Lines is using YACL for configuration. It seems like this mod is not installed."),
    Text.literal("Download from Modrinth"),
    Text.literal("Download from CurseForge")

) {
    override fun addButtons(i: Int) {
        super.addButtons(i)
        addButton(
            ButtonWidget.builder(Text.literal("Open mods folder")) {
                Util.getOperatingSystem().open(File("mods"))
            }.position(width / 2 - 155, i+25).size(150, 20).build()
        )

        addButton(
            ButtonWidget.builder(Text.literal("Close")) {
                MinecraftClient.getInstance().setScreen(null)
            }.position(width / 2 + 5, i+25).size(150, 20).build()
        )
    }
}