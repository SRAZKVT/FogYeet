package fogyeet.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import fogyeet.IMinecraftClient;

import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.BackgroundRenderer.FogType;
import net.minecraft.client.render.Camera;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {

    @Inject(
            method = "applyFog",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true)
    private static void yeetTheFog(Camera camera, FogType fogType, float viewDistance, boolean thickFog, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (!((IMinecraftClient)client).renderFog()) {
            ci.cancel(); // this injects a return statement
        }
    }
}