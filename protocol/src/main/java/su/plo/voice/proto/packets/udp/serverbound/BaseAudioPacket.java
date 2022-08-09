package su.plo.voice.proto.packets.udp.serverbound;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import lombok.Getter;
import su.plo.voice.proto.packets.Packet;

import java.io.IOException;

public abstract class BaseAudioPacket implements Packet<ServerPacketUdpHandler> {

    @Getter
    protected long sequenceNumber;

    @Getter
    protected byte[] data;

    @Override
    public void read(ByteArrayDataInput in) throws IOException {
        this.sequenceNumber = in.readLong();

        int length = in.readInt();
        byte[] data = new byte[length];
        in.readFully(data);
        this.data = data;
    }

    @Override
    public void write(ByteArrayDataOutput out) throws IOException {
        out.writeLong(sequenceNumber);

        out.writeInt(data.length);
        out.write(data);
    }
}
