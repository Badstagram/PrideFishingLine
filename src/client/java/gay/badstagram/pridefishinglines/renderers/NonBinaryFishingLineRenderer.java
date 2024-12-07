package gay.badstagram.pridefishinglines.renderers;

import gay.badstagram.pridefishinglines.FishingLineRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class NonBinaryFishingLineRenderer implements FishingLineRenderer {

    @Override
    public void render(float segmentStart, float segmentEnd, float f, float gg, float h, float i, float j, float k, MatrixStack.Entry matrices, VertexConsumer buffer) {
        if (segmentStart <= 1f && segmentStart > 12f / 16f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 244, 48, 255).normal(matrices, i, j, k);
        if (segmentStart <= 12f / 16f && segmentStart > 8f / 16f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices, i, j, k);
        if (segmentStart <= 8f / 16f && segmentStart > 4f / 16f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(156, 89, 209, 255).normal(matrices, i, j, k);
        if (segmentStart <= 4f / 16f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 0, 0, 255).normal(matrices, i, j, k);
    }
}
