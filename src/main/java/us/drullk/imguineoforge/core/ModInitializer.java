package us.drullk.imguineoforge.core;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ModInitializer.MODID)
public class ModInitializer {
    public static final String MODID = "imgui";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ModInitializer() {
        if (FMLEnvironment.dist.isClient()) {
            Minecraft.getInstance().submit(ClientLoader::initClient);
        } else {
            LOGGER.error("Imgui is not useful on dedicated servers!");
        }
    }

    public static ResourceLocation prefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
