package gay.badstagram.pridefishinglines.renderers;

import gay.badstagram.pridefishinglines.FishingLineRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class IntersexFishingLineRenderer implements FishingLineRenderer {

    @Override
    public void render(float segmentStart, float segmentEnd, float f, float gg, float h, float i, float j, float k, MatrixStack.Entry matrices, VertexConsumer buffer) {
        if (segmentStart <= 1f && segmentStart > 12f / 16f)
            buffer.vertex(matrices, f, gg, h).color(255, 216, 0, 255).normal(matrices, i, j, k);
        if (segmentStart <= 12f / 16f && segmentStart > 10f / 16f)
            buffer.vertex(matrices, f, gg, h).color(121, 2, 170, 255).normal(matrices, i, j, k);
        if (segmentStart <= 10f / 16f && segmentStart > 6f / 16f)
            buffer.vertex(matrices, f, gg, h).color(255, 216, 0, 255).normal(matrices, i, j, k);
        if (segmentStart <= 6f / 16f && segmentStart > 4f / 16f)
            buffer.vertex(matrices, f, gg, h).color(121, 2, 170, 255).normal(matrices, i, j, k);
        if (segmentStart <= 4f / 16f)
            buffer.vertex(matrices, f, gg, h).color(255, 216, 0, 255).normal(matrices, i, j, k);
    }
}
