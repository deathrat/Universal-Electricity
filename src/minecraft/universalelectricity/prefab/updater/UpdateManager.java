package universalelectricity.prefab.updater;

import universalelectricity.prefab.implement.IUEMod;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

public class UpdateManager implements IPlayerTracker
{
	private boolean _notificationDisplayed;
	private IUEMod _mod;
	private UpdateCheckThread _updateThread;
	
	public UpdateManager(IUEMod mod)
	{
		_mod = mod;
		_updateThread = new UpdateCheckThread(mod);
		if(mod.shouldCheckForUpdate())
		{
			_updateThread.start();
		}
	}

	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		if(!_notificationDisplayed && _updateThread.checkComplete())
		{
			_notificationDisplayed = true;
			player.sendChatToPlayer("[" + _mod.getVersion() + "]" + " A new version is available: " + _updateThread.getNewVersion());
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player)
	{
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player)
	{
	}
	
}
