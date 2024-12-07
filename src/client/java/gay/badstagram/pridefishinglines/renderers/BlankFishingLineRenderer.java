package gay.badstagram.pridefishinglines.renderers;

import gay.badstagram.pridefishinglines.FishingLineRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class BlankFishingLineRenderer implements FishingLineRenderer {
    @Override
    public void render(float segmentStart, float segmentEnd, float f, float gg, float h, float i, float j, float k, MatrixStack.Entry matrices, VertexConsumer buffer) {
        buffer.vertex(matrices.getPositionMatrix(), f, gg, h).color(0, 0, 0, 255).normal(matrices, i, j, k);
    }
}
