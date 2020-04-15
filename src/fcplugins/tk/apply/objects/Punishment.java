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

	public static String getFormatedTime(final long time) {
		long varsegundos = time / 1000L % 60L;
		long varminutos = time / 60000L % 60L;
		long varhoras = time / 3600000L % 24L;
		long vardias = time / 86400000L % 7L;
		String segundos = String.valueOf(varsegundos).replaceAll("-", "");
		String minutos = String.valueOf(varminutos).replaceAll("-", "");
		String horas = String.valueOf(varhoras).replaceAll("-", "");
		String dias = String.valueOf(vardias).replaceAll("-", "");
		if ((dias.equals("0")) && (horas.equals("0")) && (minutos.equals("0"))) {
			return segundos + "s";
		}
		if ((dias.equals("0")) && (horas.equals("0"))) {
			return minutos + "m " + segundos + "s";
		}
		if (dias.equals("0")) {
			return horas + "h " + minutos + "m " + segundos + "s";
		}
		return dias + "d " + horas + "h " + minutos + "m " + segundos + "s ";
	}

}
