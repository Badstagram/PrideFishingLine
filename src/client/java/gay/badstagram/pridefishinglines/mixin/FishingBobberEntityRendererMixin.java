package gay.badstagram.pridefishinglines.mixin;

import gay.badstagram.pridefishinglines.FishingLineRenderer;
import gay.badstagram.pridefishinglines.config.ConfigManager;
import gay.badstagram.pridefishinglines.config.PrideFishingLineConfig;
import gay.badstagram.pridefishinglines.renderers.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.client.render.entity.state.FishingBobberEntityState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;


@Mixin(FishingBobberEntityRenderer.class)
public abstract class FishingBobberEntityRendererMixin {

    @Shadow
    private static float percentage(int value, int max) {
        return 0; // Dummy impl
    }

    @Inject(
            method = "render(Lnet/minecraft/client/render/entity/state/FishingBobberEntityState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/FishingBobberEntityRenderer;renderFishingLine(FFFLnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/client/util/math/MatrixStack$Entry;FF)V"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void onRenderFishingLine(
            FishingBobberEntityState fishingBobberEntityState, MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci, MatrixStack.Entry entry,
            VertexConsumer vertexConsumer, float f, float g, float h, VertexConsumer vertexConsumer2,
            MatrixStack.Entry entry2, int j, int k
    ) {
        PrideFishingLineConfig cfg = ConfigManager.INSTANCE.getConfigOrException();

        customRenderFishingLine(fishingBobberEntityState, f, g, h, vertexConsumer2, entry2, percentage(k, 16),
                percentage(k + 1, 16), cfg);
    }

    @Redirect(
            method = "render(Lnet/minecraft/client/render/entity/state/FishingBobberEntityState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/FishingBobberEntityRenderer;renderFishingLine(FFFLnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/client/util/math/MatrixStack$Entry;FF)V"
            )
    )
    private void mixin(float x, float y, float z, VertexConsumer entry2, MatrixStack.Entry matrices, float segmentStart, float segmentEnd) {
    }

    @Unique
    private static void customRenderFishingLine(FishingBobberEntityState fishingBobberEntity, float x, float y, float z,
                                                VertexConsumer buffer,
                                                MatrixStack.Entry matrices, float segmentStart, float segmentEnd,
                                                PrideFishingLineConfig cfg
    ) {
        // Gross math
        // Im gonna be real, I have 0 idea what this actually does
        float f = x * segmentStart;
        float gg = y * (segmentStart * segmentStart + segmentStart) * 0.5F + 0.25F;
        float h = z * segmentStart;
        float i = x * segmentEnd - f;
        float j = y * (segmentEnd * segmentEnd + segmentEnd) * 0.5F + 0.25F - gg;
        float k = z * segmentEnd - h;
        float l = MathHelper.sqrt(i * i + j * j + k * k);
        i /= l;
        j /= l;
        k /= l;

        FishingLineRenderer renderer;

        if (!cfg.getEnabled()) {
            new BlankFishingLineRenderer().render(segmentStart, segmentEnd, f, gg, h, i, j, k, matrices, buffer);
            return;
        }

        switch (cfg.getType()) {
            case AGENDER -> renderer = new AgenderFishingLineRenderer();
            case AROMANTIC -> renderer = new AromanticFishingLineRenderer();
            case AROMANTIC_ASEXUAL -> renderer = new AroAceFishingLineRenderer();
            case ASEXUAL -> renderer = new AsexualFishingLineRenderer();
            case BI -> renderer = new BisexualFishingLineRenderer();
            case BIGENDER -> renderer = new BiGenderFishingLineRenderer();
            case DEMIBOY -> renderer = new DemiBoyFishingLineRenderer();
            case DEMIGIRL -> renderer = new DemiGirlFishingLineRenderer();
            case DEMISEXUAL -> renderer = new DemiSexualFishingLineRenderer();
            case GENDERQUEER -> renderer = new GenderQueerFishingLineRenderer();
            case INTERSEX -> renderer = new IntersexFishingLineRenderer();
            case LESBIAN -> renderer = new LesbianFishingLineRenderer();
            case MLM -> renderer = new MLMFishingLineRenderer();
            case NON_BINARY -> renderer = new NonBinaryFishingLineRenderer();
            case PANSEXUAL -> renderer = new PansexualFishingLineRenderer();
            case POLYSEXUAL -> renderer = new PolySexualFishingLineRenderer();
            case RAINBOW -> renderer = new RainbowFishingLineRenderer();
            case TRANSGENDER -> renderer = new TransgenderFishingLineRenderer();
            default -> renderer = new BlankFishingLineRenderer();
        }

        renderer.render(segmentStart, segmentEnd, f, gg, h, i, j, k, matrices, buffer);
    }
}
