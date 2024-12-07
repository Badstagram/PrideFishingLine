package gay.badstagram.pridefishinglines.renderers;

import gay.badstagram.pridefishinglines.FishingLineRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class PansexualFishingLineRenderer implements FishingLineRenderer {

    @Override
    public void render(float segmentStart, float segmentEnd, float f, float gg, float h, float i, float j, float k, MatrixStack.Entry matrices, VertexConsumer buffer) {
        if (segmentStart <= 1f && segmentStart > 10f / 15f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(254, 33, 139, 255).normal(matrices, i, j, k);
        if (segmentStart <= 10f / 15f && segmentStart > 5f / 15f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(254, 215, 0, 255).normal(matrices, i, j, k);
        if (segmentStart <= 5f / 15f)
            buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(33, 176, 254, 255).normal(matrices, i, j, k);
    }
}
