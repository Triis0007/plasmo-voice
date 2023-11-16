package su.plo.voice;

import su.plo.voice.server.ModVoiceServer;
import su.plo.voice.util.version.ModrinthLoader;

//#if FABRIC
import net.fabricmc.api.ModInitializer;
//#else
//$$ import su.plo.voice.client.ModVoiceClient;
//$$ import su.plo.slib.mod.channel.ModChannelManager;
//$$
//$$ import net.minecraft.resources.ResourceLocation;
//$$ import net.minecraftforge.common.MinecraftForge;
//$$ import net.minecraftforge.eventbus.api.SubscribeEvent;
//$$ import net.minecraftforge.fml.common.Mod;
//$$ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//$$ import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//$$ import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//$$ import net.minecraftforge.network.event.EventNetworkChannel;

//#if MC>=12002
//$$ import net.minecraftforge.network.ChannelBuilder;
//#else
//$$ import net.minecraftforge.network.NetworkRegistry;
//$$ import net.minecraftforge.network.NetworkRegistry.ChannelBuilder;
//#endif

//#endif

//#if FORGE
//$$ @Mod("plasmovoice")
//#endif
public final class ModVoice
        //#if FABRIC
        implements ModInitializer
        //#endif
{

    //#if FORGE
    //$$ private EventNetworkChannel channel;
    //$$
    //$$ public ModVoice() {
    //$$     FMLJavaModLoadingContext.get().getModEventBus().register(this);
    //$$ }
    //$$
    //$$ @SubscribeEvent
    //$$ public void onClientSetup(FMLClientSetupEvent event) {
    //$$     ModVoiceClient voiceClient = new ModVoiceClient();
    //$$     MinecraftForge.EVENT_BUS.register(voiceClient);
    //$$     voiceClient.onInitialize(channel);
    //$$ }
    //$$
    //$$ @SubscribeEvent
    //$$ public void onCommonSetup(FMLCommonSetupEvent event) {
    //$$     this.channel = createChannel(ModVoiceServer.CHANNEL);
    //$$
    //$$     createChannel(ModVoiceServer.FLAG_CHANNEL);
    //$$
    //$$     EventNetworkChannel serviceChannel = createChannel(ModVoiceServer.SERVICE_CHANNEL);
    //$$
    //$$     ModChannelManager.addForgeChannel(ModVoiceServer.CHANNEL, channel);
    //$$     ModChannelManager.addForgeChannel(ModVoiceServer.SERVICE_CHANNEL, serviceChannel);
    //$$
    //$$     ModVoiceServer voiceServer = new ModVoiceServer(ModrinthLoader.FORGE);
    //$$     voiceServer.onInitialize();
    //$$ }
    //$$
    //$$ private EventNetworkChannel createChannel(ResourceLocation resourceLocation) {
    //$$     return ChannelBuilder.named(resourceLocation)
    //#if MC>=12002
    //$$             .optional()
    //#else
    //$$             .networkProtocolVersion(() -> NetworkRegistry.ACCEPTVANILLA)
    //$$             .clientAcceptedVersions(NetworkRegistry.acceptMissingOr(NetworkRegistry.ACCEPTVANILLA))
    //$$             .serverAcceptedVersions(NetworkRegistry.acceptMissingOr(NetworkRegistry.ACCEPTVANILLA))
    //#endif
    //$$             .eventNetworkChannel();
    //$$ }
    //#endif

    //#if FABRIC
    @Override
    public void onInitialize() {
        ModVoiceServer voiceServer = new ModVoiceServer(ModrinthLoader.FABRIC);
        voiceServer.onInitialize();
    }
    //#endif
}
