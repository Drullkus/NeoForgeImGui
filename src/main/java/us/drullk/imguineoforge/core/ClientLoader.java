package us.drullk.imguineoforge.core;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import us.drullk.imguineoforge.diagnostic.ImGuiDebugPanels;

import java.nio.file.Path;

@EventBusSubscriber(modid = ModInitializer.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientLoader {
	@SubscribeEvent
	public static void registerOverlays(RegisterGuiLayersEvent event) {
		event.registerBelowAll(ModInitializer.prefix("debug_tick"), ImGuiDebugPanels::onRender);
	}

	static void initClient() {
		if (System.getProperty("os.arch").equals("arm") || System.getProperty("os.arch").startsWith("aarch64"))
			setupImGuiLibARM64();
	}

	private static void setupImGuiLibARM64() {
		System.setProperty("imgui.library.name", "libimgui-javaarm64.dylib");

		if (FMLLoader.isProduction()) return;

		// Define the base path and the relative path to the native library. Expects game root dir to be /runs/client/.
		String relativePath = "../../build/resources/main/io/imgui/java/native-bin/";
		// Resolve the absolute path to the native library
		Path nativeLibPath = FMLPaths.GAMEDIR.get().resolve(relativePath).normalize();

		System.setProperty("imgui.library.path", nativeLibPath.toAbsolutePath().toString());
	}
}
