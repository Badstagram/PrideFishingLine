package gay.badstagram.pridefishinglines;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public interface FishingLineRenderer {
    void render(
            float segmentStart,
            float segmentEnd,
            float f,
            float gg,
            float h,
            float i,
            float j,
            float k,
            MatrixStack.Entry matrices,
            VertexConsumer buffer
    );
}
