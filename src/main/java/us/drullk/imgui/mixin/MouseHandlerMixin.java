package us.drullk.imgui.mixin;

import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.drullk.imgui.ImGuiMinecraft;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
	@Inject(method = "onPress", at = @At("HEAD"), cancellable = true)
	private void consumeClick(long pWindowPointer, int pButton, int pAction, int pModifiers, CallbackInfo ci) {
		if (ImGuiMinecraft.consumeClick())
			ci.cancel();
	}

	@Inject(method = "onScroll", at = @At("HEAD"), cancellable = true)
	private void consumeScroll(long pWindowPointer, double pXOffset, double pYOffset, CallbackInfo ci) {
		if (ImGuiMinecraft.consumeClick())
			ci.cancel();
	}
}
