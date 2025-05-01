package club.frozed.tablist.packet;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerTeams;
import net.kyori.adventure.text.Component;

import java.util.function.Consumer;

public class TabPacketListener implements PacketListener {

    private final Component displayName = Component.text("tab");
    private final Consumer<WrapperPlayServerTeams.ScoreBoardTeamInfo> updateDisplayName = i -> i.setDisplayName(this.displayName);

    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.TEAMS) {
            WrapperPlayServerTeams scoreboardTeam = new WrapperPlayServerTeams(event);

            if (/* FIXME: No idea what h == 4 is */ !scoreboardTeam.getTeamName().equalsIgnoreCase("tab")) {
                scoreboardTeam.setTeamName("tab");
                scoreboardTeam.getTeamInfo().ifPresent(this.updateDisplayName);
                // FIXME: No idea that h = 3 is
            }
        }
    }

}
