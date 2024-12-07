package gay.badstagram.pridefishinglines.renderers;

import gay.badstagram.pridefishinglines.FishingLineRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class BisexualFishingLineRenderer implements FishingLineRenderer {

    @Override
    public void render(float segmentStart, float segmentEnd, float f, float gg, float h, float i, float j, float k, MatrixStack.Entry matrices, VertexConsumer buffer) {
        if (segmentStart <= 1f && segmentStart > 10f / 16f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(214, 2, 112, 255).normal(matrices, i, j, k);
        if (segmentStart <= 10f / 16f && segmentStart > 6f / 16f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(155, 79, 150, 255).normal(matrices, i, j, k);
        if (segmentStart <= 6f / 16f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 56, 168, 255).normal(matrices, i, j, k);
    }
}
