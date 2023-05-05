package gay.badstagram.pridefishinglines.mixin;

import gay.badstagram.pridefishinglines.config.ConfigManager;
import gay.badstagram.pridefishinglines.config.PrideFishingLineStyles;
import gay.badstagram.pridefishinglines.config.PrideFishingLinesConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.UUID;

@Mixin(FishingBobberEntityRenderer.class)
public abstract class FishingBobberEntityRendererMixin {

    @Shadow
    private static float percentage(int value, int max) {
        return 0;
    }

    @Inject(method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/FishingBobberEntityRenderer;renderFishingLine(FFFLnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/client/util/math/MatrixStack$Entry;FF)V"),
            locals = LocalCapture.CAPTURE_FAILSOFT)
    private void onRenderFishingLine(FishingBobberEntity fishingBobberEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci, PlayerEntity playerEntity, MatrixStack.Entry entry, Matrix4f matrix4f, Matrix3f matrix3f, VertexConsumer vertexConsumer, int j, ItemStack itemStack, float h, float k, float l, double d, double e, double m, double n, double o, double p, double q, float r, double s, double t, double u, float v, float w, float x, VertexConsumer vertexConsumer2, MatrixStack.Entry entry2, int y, int z) {
        PlayerEntity bobberOwner = fishingBobberEntity.getPlayerOwner();
        PrideFishingLinesConfig cfg = ConfigManager.INSTANCE.getConfigOrException();




        if (bobberOwner != null) {
            customRenderFishingLine(fishingBobberEntity, v, w, x, vertexConsumer2, entry2, percentage(z, 16), percentage(z + 1, 16), cfg);
        }
    }

    @Redirect(method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/FishingBobberEntityRenderer;renderFishingLine(FFFLnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/client/util/math/MatrixStack$Entry;FF)V"))
    private void mixin(float x, float y, float z, VertexConsumer entry2, MatrixStack.Entry matrices, float segmentStart, float segmentEnd) {
    }

    private static void customRenderFishingLine(FishingBobberEntity fishingBobberEntity, float x, float y, float z, VertexConsumer buffer,
                                                MatrixStack.Entry matrices, float segmentStart, float segmentEnd, PrideFishingLinesConfig cfg) {


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
        float r2;
        float g2;
        float b2;
        PlayerEntity bobberOwner = fishingBobberEntity.getPlayerOwner();
        if (bobberOwner == null) return;

        if (!cfg.getEnabled()) {
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 0, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            return;
        };

        if (cfg.getHideOthers() && !bobberOwner.getUuidAsString().equals(MinecraftClient.getInstance().player.getUuidAsString())) {
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 0, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            return;
        };

        switch (cfg.getType()) {
            case BI -> {
                if (segmentStart <= 1f && segmentStart > 10f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(214, 2, 112, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 10f / 16f && segmentStart > 6f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(155, 79, 150, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 6f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 56, 168, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case MLM -> {
                if (segmentStart <= 1f && segmentStart > 12f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(7, 141, 112, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 12f / 14f && segmentStart > 10f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(39, 201, 171, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 10f / 14f && segmentStart > 8f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(154, 233, 195, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 8f / 14f && segmentStart > 6f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 6f / 14f && segmentStart > 4f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(124, 174, 228, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 4f / 14f && segmentStart > 2f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(81, 74, 204, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 2f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(62, 26, 120, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case RAINBOW -> {
                if (segmentStart <= 1f && segmentStart > 10f / 12f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 0, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 10f / 12f && segmentStart > 8f / 12f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 140, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 8f / 12f && segmentStart > 6f / 12f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 6f / 12f && segmentStart > 4f / 12f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 255, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 4f / 12f && segmentStart > 2f / 12f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 0, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 2f / 12f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 0, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case LESBIAN -> {
                if (segmentStart <= 1f && segmentStart > 12f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(213, 45, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 12f / 15f && segmentStart > 9f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 154, 86, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 9f / 15f && segmentStart > 6f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 6f / 15f && segmentStart > 3f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(211, 98, 164, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 3f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(163, 2, 98, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case NON_BINARY -> {
                if (segmentStart <= 1f && segmentStart > 12f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 244, 48, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 12f / 16f && segmentStart > 8f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 8f / 16f && segmentStart > 4f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(156, 89, 209, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 4f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 0, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case INTERSEX -> {
                if (segmentStart <= 1f && segmentStart > 12f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 216, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 12f / 16f && segmentStart > 10f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(121, 2, 170, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 10f / 16f && segmentStart > 6f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 216, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 6f / 16f && segmentStart > 4f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(121, 2, 170, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 4f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 216, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case DEMISEXUAL -> {
                if (segmentStart <= 1f && segmentStart > 11f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 11f / 16f && segmentStart > 9f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(109, 0, 112, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 9f / 16f && segmentStart > 7f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 0, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 7f / 16f && segmentStart > 5f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(109, 0, 112, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 5f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(210, 210, 210, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case TRANSGENDER -> {
                if (segmentStart <= 1f && segmentStart > 12f / 15f || segmentStart <= 3f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(91, 206, 250, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if ((segmentStart <= 12f / 15f && segmentStart > 9f / 15f) || (segmentStart <= 6f / 15f && segmentStart > 3f / 15f))
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(245, 169, 184, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 9f / 15f && segmentStart > 6f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case ASEXUAL -> {
                if (segmentStart <= 1f && segmentStart > 12f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 0, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 12f / 16f && segmentStart > 8f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(163, 163, 163, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 8f / 16f && segmentStart > 4f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 4f / 16f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(128, 0, 128, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case AROMANTIC -> {
                if (segmentStart <= 1f && segmentStart > 12f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(61, 165, 66, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 12f / 15f && segmentStart > 9f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(167, 211, 121, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 9f / 15f && segmentStart > 6f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 6f / 15f && segmentStart > 3f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(169, 169, 169, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 3f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 0, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case AGENDER -> {
                if (segmentStart <= 1f && segmentStart > 12f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 0, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 12f / 14f && segmentStart > 10f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(188, 196, 198, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 10f / 14f && segmentStart > 8f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 8f / 14f && segmentStart > 6f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(182, 245, 131, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 6f / 14f && segmentStart > 4f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 4f / 14f && segmentStart > 2f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(188, 196, 198, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 2f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 0, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case AROMANTIC_ASEXUAL -> {
                if (segmentStart <= 1f && segmentStart > 12f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(226, 140, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 12f / 15f && segmentStart > 9f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(236, 205, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 9f / 15f && segmentStart > 6f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 6f / 15f && segmentStart > 3f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(98, 174, 220, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 3f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(32, 56, 86, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case PANSEXUAL -> {
                if (segmentStart <= 1f && segmentStart > 10f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(254, 33, 139, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 10f / 15f && segmentStart > 5f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(254, 215, 0, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 5f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(33, 176, 254, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case POLYSEXUAL -> {
                if (segmentStart <= 1f && segmentStart > 10f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(246, 28, 158, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 10f / 15f && segmentStart > 5f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(7, 213, 105, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 5f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(28, 146, 246, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case DEMIBOY -> {
                if ((segmentStart <= 1f && segmentStart > 12f / 14f) || segmentStart <= 2f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(127, 127, 127, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if ((segmentStart <= 12f / 14f && segmentStart > 10f / 14f) || (segmentStart <= 4f / 14f && segmentStart > 2f / 14f))
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(196, 196, 196, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if ((segmentStart <= 10f / 14f && segmentStart > 8f / 14f) || (segmentStart <= 6f / 14f && segmentStart > 4f / 14f))
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(154, 217, 235, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 8f / 14f && segmentStart > 6f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case DEMIGIRL -> {
                if ((segmentStart <= 1f && segmentStart > 12f / 14f) || segmentStart <= 2f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(127, 127, 127, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if ((segmentStart <= 12f / 14f && segmentStart > 10f / 14f) || (segmentStart <= 4f / 14f && segmentStart > 2f / 14f))
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(196, 196, 196, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if ((segmentStart <= 10f / 14f && segmentStart > 8f / 14f) || (segmentStart <= 6f / 14f && segmentStart > 4f / 14f))
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 174, 201, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 8f / 14f && segmentStart > 6f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case GENDERQUEER -> {
                if (segmentStart <= 1f && segmentStart > 10f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(181, 126, 220, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 10f / 15f && segmentStart > 5f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 5f / 15f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(74, 129, 35, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
            case BIGENDER -> {
                if (segmentStart <= 1f && segmentStart > 12f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(196, 121, 160, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 12f / 14f && segmentStart > 10f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(236, 166, 203, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 10f / 14f && segmentStart > 8f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(214, 199, 233, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 8f / 14f && segmentStart > 6f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 6f / 14f && segmentStart > 4f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(214, 199, 233, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 4f / 14f && segmentStart > 2f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(155, 199, 232, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
                if (segmentStart <= 2f / 14f)
                    buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(107, 131, 207, 255).normal(matrices.getNormalMatrix(), i, j, k).next();
            }
        }
    }
}
