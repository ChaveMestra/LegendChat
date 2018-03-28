package br.com.devpaulo.legendchat.delays;

import java.util.HashMap;

import br.com.devpaulo.legendchat.channels.types.Channel;
import org.bukkit.entity.Player;

public class DelayManager {
	private final HashMap<String,Delay> delays = new HashMap<>();
	public DelayManager() {
	}
	
	public void addPlayerDelay(Player p, Channel c) {
		String name = p.getName().toLowerCase();
		if(hasPlayerDelay(name)) {
			getPlayerDelay(name).addDelay(p,c);
		}
		else {
			Delay d = new Delay(name);
			delays.put(name, d);
			d.addDelay(p,c);
		}
	}
	
	public Delay getPlayerDelay(String name) {
		name = name.toLowerCase();
		if(hasPlayerDelay(name))
			return delays.get(name);
		return null;
	}
	
	public int getPlayerDelayFromChannel(String name, Channel c) {
		name = name.toLowerCase();
		if(hasPlayerDelay(name))
			return delays.get(name).getDelay(c);
		return 0;
	}
	
	public void removePlayerDelay(String name) {
		name = name.toLowerCase();
		if(!hasPlayerDelay(name))
			return;
		Delay d = getPlayerDelay(name);
		if(d.getDelaysSize()>0)
			for(Channel c : d.getDelayedChannels())
				d.removeDelay(c);
		delays.remove(name);
	}
	
	public boolean hasPlayerDelay(String name) {
		return delays.containsKey(name.toLowerCase());
	}

}
