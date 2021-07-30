package fogyeet.mixin;

import fogyeet.IGameOptions;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(GameOptions.class)
public class GameOptionsMixin implements IGameOptions {

    @Shadow @Final @Mutable
    public KeyBinding[] keysAll;

    public KeyBinding keyRenderFog;

    @Inject(
            method = "load",
            at = @At(
                    value = "HEAD"
            )
    )
    private void onLoadInjectAtHead(CallbackInfo ci) {
        keyRenderFog = new KeyBinding("Toggle fog", GLFW.GLFW_KEY_F6, "FogYeet");
        keysAll = ArrayUtils.add(keysAll, keyRenderFog);
    }

    @Override
    public KeyBinding getKeyRenderFog() {
        return keyRenderFog;
    }
}
