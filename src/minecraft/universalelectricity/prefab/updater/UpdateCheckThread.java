package universalelectricity.prefab.updater;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;

import universalelectricity.prefab.implement.IUEMod;

public class UpdateCheckThread extends Thread
{
	private String _updateUrl;
	
	private IUEMod _mod;
	
	private boolean _checkComplete;
	private boolean _newVerAvailable;
	
	private String _newVer;
	
	public UpdateCheckThread(IUEMod mod)
	{
		_mod = mod;
	}
	
	@Override
	public void run()
	{
		try
		{
			URL versionFile = new URL(_mod.getUpdateURL());
			BufferedReader reader = new BufferedReader(new InputStreamReader(versionFile.openStream()));
			_newVer = reader.readLine();
			String[] parts = _newVer.split("\\.");
			int majorVersion = Integer.parseInt(parts[0]);
			int minorVersion = Integer.parseInt(parts[1]);
			int revisionVersion = Integer.parseInt(parts[2]);
			
			if (majorVersion > _mod.getMajorVersion() || minorVersion > _mod.getMinorVersion() || revisionVersion > _mod.getRevision())
				_newVerAvailable = true;
			
			_checkComplete = true;
		}
		catch(Exception e)
		{
			FMLLog.log(Level.WARNING, "Update check for " + _mod.getModName() + "failed: " + e.getMessage() );
			e.printStackTrace();
		}
	}
	
	public boolean checkComplete()
	{
		return _checkComplete;
	}
	
	public boolean newVersionAvailable()
	{
		return _newVerAvailable;
	}
	
	public String getNewVersion()
	{
		return _newVer;
	}
	
}
