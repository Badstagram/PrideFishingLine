package gay.badstagram.pridefishinglines.config

import kotlinx.serialization.Serializable

@Serializable
data class PrideFishingLinesConfig(
    var enabled: Boolean,
    var hideOthers: Boolean,
    var type: PrideFishingLineStyles
) {
    companion object {
        val DEFAULT = PrideFishingLinesConfig(
            enabled = true,
            hideOthers = false,
            type = PrideFishingLineStyles.RAINBOW
        )
    }
}
