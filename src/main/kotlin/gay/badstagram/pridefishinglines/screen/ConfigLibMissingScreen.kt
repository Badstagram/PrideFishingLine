package gay.badstagram.pridefishinglines.screen

import gay.badstagram.pridefishinglines.minecraft
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
    Text.literal("screen.configLibMissing.title"),
    Text.literal("screen.configLibMissing.message"),
    Text.literal("screen.configLibMissing.modrinth"),
    Text.literal("screen.configLibMissing.curseforge")

) {
    override fun addButtons(i: Int) {
        super.addButtons(i)
        addButton(
            ButtonWidget.builder(Text.literal("screen.configLibMissing.openFolder")) {
                Util.getOperatingSystem().open(File("mods"))
            }.position(width / 2 - 155, i+25).size(150, 20).build()
        )

        addButton(
            ButtonWidget.builder(Text.literal("screen.configLibMissing.close")) {
                minecraft.setScreen(null)
            }.position(width / 2 + 5, i+25).size(150, 20).build()
        )
    }
}