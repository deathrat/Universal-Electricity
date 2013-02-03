package universalelectricity.prefab.implement;

public interface IUEMod
{
	/**
	 * 
	 * @return The name of your mod
	 * 
	 */
	public String getModName();
	
	/**
	 * 
	 * @return The major version of your mod.
	 * 
	 */
	public int getMajorVersion();
	
	/**
	 * 
	 * @return The minor version of your mod.
	 * 
	 */
	public int getMinorVersion();
	
	/**
	 * 
	 * @return The revision number of your mod.
	 * 
	 */
	public int getRevision();
	
	/**
	 * 
	 * @return A string containing the version of your mod. Format: Major.Minor.Revision 
	 * 
	 */
	public String getVersion();
	
	/**
	 * 
	 * @return true if you want to throw an exception when versions are wrong.
	 * 
	 */
	public boolean isStrict();
	
	
	/**
	 * 
	 * @return The URL to check for new versions.
	 * 
	 */
	public String getUpdateURL();

	/**
	 * 
	 * @return If should check for updates(preferably set in a config).
	 * 
	 */
	public boolean shouldCheckForUpdate();

}
