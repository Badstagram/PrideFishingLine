package gay.badstagram.pridefishinglines.renderers;

import gay.badstagram.pridefishinglines.FishingLineRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class LesbianFishingLineRenderer implements FishingLineRenderer {

    @Override
    public void render(float segmentStart, float segmentEnd, float f, float gg, float h, float i, float j, float k, MatrixStack.Entry matrices, VertexConsumer buffer) {
        if (segmentStart <= 1f && segmentStart > 12f / 15f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(213, 45, 0, 255).normal(matrices, i, j, k);
        if (segmentStart <= 12f / 15f && segmentStart > 9f / 15f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 154, 86, 255).normal(matrices, i, j, k);
        if (segmentStart <= 9f / 15f && segmentStart > 6f / 15f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices, i, j, k);
        if (segmentStart <= 6f / 15f && segmentStart > 3f / 15f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(211, 98, 164, 255).normal(matrices, i, j, k);
        if (segmentStart <= 3f / 15f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(163, 2, 98, 255).normal(matrices, i, j, k);
    }
}
