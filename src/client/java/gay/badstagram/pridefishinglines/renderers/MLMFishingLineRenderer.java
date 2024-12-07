package gay.badstagram.pridefishinglines.renderers;

import gay.badstagram.pridefishinglines.FishingLineRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class MLMFishingLineRenderer implements FishingLineRenderer {

    @Override
    public void render(float segmentStart, float segmentEnd, float f, float gg, float h, float i, float j, float k, MatrixStack.Entry matrices, VertexConsumer buffer) {
        if (segmentStart <= 1f && segmentStart > 12f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(7, 141, 112, 255).normal(matrices, i, j, k);
        if (segmentStart <= 12f / 14f && segmentStart > 10f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(39, 201, 171, 255).normal(matrices, i, j, k);
        if (segmentStart <= 10f / 14f && segmentStart > 8f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(154, 233, 195, 255).normal(matrices, i, j, k);
        if (segmentStart <= 8f / 14f && segmentStart > 6f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices, i, j, k);
        if (segmentStart <= 6f / 14f && segmentStart > 4f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(124, 174, 228, 255).normal(matrices, i, j, k);
        if (segmentStart <= 4f / 14f && segmentStart > 2f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(81, 74, 204, 255).normal(matrices, i, j, k);
        if (segmentStart <= 2f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(62, 26, 120, 255).normal(matrices, i, j, k);
    }
}
