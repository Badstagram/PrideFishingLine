package gay.badstagram.pridefishinglines.renderers;

import gay.badstagram.pridefishinglines.FishingLineRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class AroAceFishingLineRenderer implements FishingLineRenderer {

    @Override
    public void render(float segmentStart, float segmentEnd, float f, float gg, float h, float i, float j, float k, MatrixStack.Entry matrices, VertexConsumer buffer) {
        if (segmentStart <= 1f && segmentStart > 12f / 15f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(226, 140, 0, 255).normal(matrices, i, j, k);
        if (segmentStart <= 12f / 15f && segmentStart > 9f / 15f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(236, 205, 0, 255).normal(matrices, i, j, k);
        if (segmentStart <= 9f / 15f && segmentStart > 6f / 15f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices, i, j, k);
        if (segmentStart <= 6f / 15f && segmentStart > 3f / 15f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(98, 174, 220, 255).normal(matrices, i, j, k);
        if (segmentStart <= 3f / 15f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(32, 56, 86, 255).normal(matrices, i, j, k);
    }
}
