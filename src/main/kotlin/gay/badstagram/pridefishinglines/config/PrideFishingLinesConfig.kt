package gay.badstagram.pridefishinglines.config

import kotlinx.serialization.Serializable

@Serializable
data class PrideFishingLinesConfig(
    var enabled: Boolean,
    var type: PrideFishingLineStyles
) {
    companion object {
        val DEFAULT = PrideFishingLinesConfig(
            true,
            PrideFishingLineStyles.RAINBOW
        )
    }
}
