package fcplugins.tk.apply.objects;

import java.util.UUID;

public class Punishment {

	private final UUID uuid;
	private long time;

	public Punishment(UUID uuid, long time) {
		this.uuid = uuid;
		this.time = time;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public UUID getUuid() {
		return uuid;
	}

	public boolean isExpired() {
		return time < System.currentTimeMillis();
	}

}
