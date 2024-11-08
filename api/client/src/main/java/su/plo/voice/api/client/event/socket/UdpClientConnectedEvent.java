package su.plo.voice.api.client.event.socket;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import su.plo.voice.api.client.socket.UdpClient;
import su.plo.voice.api.event.Event;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This event is fired once the UDP client is connected to a server.
 */
public final class UdpClientConnectedEvent implements Event {

    @Getter
    private final UdpClient client;

    public UdpClientConnectedEvent(@NotNull UdpClient client) {
        this.client = checkNotNull(client, "client cannot be null");
    }
}
