package gay.badstagram.pridefishinglines.renderers;

import gay.badstagram.pridefishinglines.FishingLineRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class BiGenderFishingLineRenderer implements FishingLineRenderer {

    @Override
    public void render(float segmentStart, float segmentEnd, float f, float gg, float h, float i, float j, float k, MatrixStack.Entry matrices, VertexConsumer buffer) {
        if (segmentStart <= 1f && segmentStart > 12f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(196, 121, 160, 255).normal(matrices, i, j, k);
        if (segmentStart <= 12f / 14f && segmentStart > 10f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(236, 166, 203, 255).normal(matrices, i, j, k);
        if (segmentStart <= 10f / 14f && segmentStart > 8f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(214, 199, 233, 255).normal(matrices, i, j, k);
        if (segmentStart <= 8f / 14f && segmentStart > 6f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(255, 255, 255, 255).normal(matrices, i, j, k);
        if (segmentStart <= 6f / 14f && segmentStart > 4f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(214, 199, 233, 255).normal(matrices, i, j, k);
        if (segmentStart <= 4f / 14f && segmentStart > 2f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(155, 199, 232, 255).normal(matrices, i, j, k);
        if (segmentStart <= 2f / 14f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(107, 131, 207, 255).normal(matrices, i, j, k);
    }
}

