package basiccomponents.client;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import basiccomponents.common.BasicComponents;
import basiccomponents.common.tileentity.TileEntityCopperWire;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCopperWire extends TileEntitySpecialRenderer
{
	private ModelCopperWire model;

	public RenderCopperWire()
	{
		model = new ModelCopperWire();
	}

	public void renderAModelAt(TileEntityCopperWire tileEntity, double d, double d1, double d2, float f)
	{
		// Texture file
		bindTextureByName(BasicComponents.FILE_PATH + "CopperWire.png");
		GL11.glPushMatrix();
		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.5F, (float) d2 + 0.5F);
		GL11.glTranslatef(0, -1, 0);
		ForgeDirection dir = ForgeDirection.getOrientation(tileEntity.worldObj.getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord));

		if (dir == dir.UP)
		{
			GL11.glRotatef(180, 1f, 0f, 0f);
		}
		GL11.glTranslatef(0, 1, 0);
		GL11.glScalef(1f, -1f, -1f);

		if (tileEntity.visuallyConnected[0])
		{
			model.renderBottom();
		}

		if (tileEntity.visuallyConnected[1])
		{
			model.renderTop();
		}

		if (tileEntity.visuallyConnected[2])
		{
			model.renderBack();
		}

		if (tileEntity.visuallyConnected[3])
		{
			model.renderFront();
		}

		if (tileEntity.visuallyConnected[4])
		{
			model.renderLeft();
		}

		if (tileEntity.visuallyConnected[5])
		{
			model.renderRight();
		}

		model.renderMiddle();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double var2, double var4, double var6, float var8)
	{
		this.renderAModelAt((TileEntityCopperWire) tileEntity, var2, var4, var6, var8);
	}
}