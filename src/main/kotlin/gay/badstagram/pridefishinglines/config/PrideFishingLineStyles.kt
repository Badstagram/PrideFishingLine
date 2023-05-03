package gay.badstagram.pridefishinglines.config

import dev.isxander.yacl.api.NameableEnum
import net.minecraft.text.Text


enum class PrideFishingLineStyles : NameableEnum {
    RAINBOW {
        override fun getDisplayName(): Text = Text.literal("Rainbow")
    },
    BI {
        override fun getDisplayName(): Text = Text.literal("Bisexual")
    },
    MLM {
        override fun getDisplayName(): Text = Text.literal("MLM")
    },
    LESBIAN {
        override fun getDisplayName(): Text = Text.literal("Lesbian")
    },
    NON_BINARY {
        override fun getDisplayName(): Text = Text.literal("Non Binary")
    },
    INTERSEX {
        override fun getDisplayName(): Text = Text.literal("Intersex")
    },
    DEMISEXUAL {
        override fun getDisplayName(): Text = Text.literal("Demisexual")
    },
    TRANSGENDER {
        override fun getDisplayName(): Text = Text.literal("Transgender")
    },
    AGENDER {
        override fun getDisplayName(): Text = Text.literal("Agender")
    },
    AROMANTIC_ASEXUAL {
        override fun getDisplayName(): Text = Text.literal("AroAce")
    },
    AROMANTIC {
        override fun getDisplayName(): Text = Text.literal("Aromantic")
    },
    ASEXUAL {
        override fun getDisplayName(): Text = Text.literal("Asexual")
    },
    PANSEXUAL {
        override fun getDisplayName(): Text = Text.literal("Pansexual")
    },
    POLYSEXUAL {
        override fun getDisplayName(): Text = Text.literal("Polysexual")
    },
    GENDERQUEER {
        override fun getDisplayName(): Text = Text.literal("Genderqueer")
    },
    DEMIBOY {
        override fun getDisplayName(): Text = Text.literal("Demiboy")
    },
    DEMIGIRL {
        override fun getDisplayName(): Text = Text.literal("Demigirl")
    },
    BIGENDER {
        override fun getDisplayName(): Text = Text.literal("Bigender")
    },
}