package gay.badstagram.pridefishinglines.renderers;

import gay.badstagram.pridefishinglines.FishingLineRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class RainbowFishingLineRenderer implements FishingLineRenderer {

    @Override
    public void render(float segmentStart, float segmentEnd, float f, float gg, float h, float i, float j, float k, MatrixStack.Entry matrices, VertexConsumer buffer) {
        if (segmentStart <= 1f && segmentStart > 10f / 12f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 0, 0, 255).normal(matrices, i, j, k);
        if (segmentStart <= 10f / 12f && segmentStart > 8f / 12f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 140, 0, 255).normal(matrices, i, j, k);
        if (segmentStart <= 8f / 12f && segmentStart > 6f / 12f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 0, 255).normal(matrices, i, j, k);
        if (segmentStart <= 6f / 12f && segmentStart > 4f / 12f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 255, 0, 255).normal(matrices, i, j, k);
        if (segmentStart <= 4f / 12f && segmentStart > 2f / 12f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 0, 255, 255).normal(matrices, i, j, k);
        if (segmentStart <= 2f / 12f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 0, 255, 255).normal(matrices, i, j, k);
    }
}
