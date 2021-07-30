package fogyeet.mixin;
import fogyeet.IGameOptions;
import fogyeet.IMinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.GameOptions;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements IMinecraftClient {

    private boolean renderFog;

    @Shadow
    public GameOptions options;

    @Shadow
    private ClientPlayerEntity player;

    @Shadow protected abstract void render(boolean tick);

    @Inject(
            method = "<init>",
            at = @At(
                    value = "RETURN"
            )
    )
    private void onInitClient(RunArgs args, CallbackInfo ci) {

    }

    @Override
    public boolean renderFog() {
        return renderFog;
    }

    @Inject(
            method = "handleInputEvents",
            at = @At(
                    value = "HEAD"
            )
    )
    private void handleRenderFogKeybind(CallbackInfo ci) {
        while (((IGameOptions)options).getKeyRenderFog().wasPressed()) {
            renderFog = !renderFog;
            String uwu = "Toggled fog rendering : ";
            uwu = uwu + ((renderFog ? "ON" : "OFF"));
            player.addChatMessage(new LiteralText(uwu), true);
        }
    }
}