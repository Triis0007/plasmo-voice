package su.plo.voice.server.command;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.plo.lib.chat.TextComponent;
import su.plo.lib.server.command.MinecraftCommand;
import su.plo.lib.server.command.MinecraftCommandSource;
import su.plo.voice.api.server.PlasmoVoiceServer;
import su.plo.voice.api.server.player.VoicePlayer;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public final class VoiceListCommand implements MinecraftCommand {

    private final PlasmoVoiceServer voiceServer;

    @Override
    public void execute(@NotNull MinecraftCommandSource source, @NotNull String[] arguments) {
        List<String> players = voiceServer.getPlayerManager().getPlayers()
                .stream()
                .filter(VoicePlayer::hasVoiceChat)
                .map((player) -> player.getInstance().getName())
                .sorted()
                .collect(Collectors.toList());

        source.sendMessage(TextComponent.translatable(
                "commands.plasmovoice.list",
                players.size(),
                voiceServer.getPlayerManager().getPlayers().size(),
                players.size() > 0
                        ? String.join(", ", players)
                        : TextComponent.translatable("commands.plasmovoice.list.empty")
        ));
    }

    @Override
    public boolean hasPermission(@NotNull MinecraftCommandSource source, @Nullable String[] arguments) {
        return source.hasPermission("voice.list");
    }
}