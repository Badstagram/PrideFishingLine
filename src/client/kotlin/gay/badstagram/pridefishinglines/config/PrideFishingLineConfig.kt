package gay.badstagram.pridefishinglines.config

import kotlinx.serialization.Serializable

@Serializable
data class PrideFishingLineConfig(
    var enabled: Boolean,
    var hideOthers: Boolean,
    var type: PrideFishingLineStyles,
) {
    companion object {
        val DEFAULT = PrideFishingLineConfig(
            enabled = true,
            hideOthers = false,
            type = PrideFishingLineStyles.RAINBOW
        )
    }
}