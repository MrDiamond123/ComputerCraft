/**
 * This file is part of ComputerCraft - http://www.computercraft.info
 * Copyright Daniel Ratcliffe, 2011-2016. Do not distribute without permission.
 * Send enquiries to dratcliffe@gmail.com
 */

package dan200.computercraft.shared.peripheral.common;

import dan200.computercraft.shared.peripheral.PeripheralType;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class ItemPeripheralBase extends ItemBlock implements IPeripheralItem
{
    protected ItemPeripheralBase( Block block )
    {
        super( block );
        setMaxStackSize( 64 );
        setHasSubtypes( true );
    }

    public abstract PeripheralType getPeripheralType( int damage );

    @Override
    public final int getMetadata( int damage )
    {
        return damage;
    }

    @Override
    public boolean canPlaceBlockOnSide( World world, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack ) // canPlaceItemBlockOnSide
    {
        PeripheralType type = getPeripheralType( stack );
        switch( type )
        {
            case WirelessModem:
            case WiredModem:
            case AdvancedModem:
            {
                return world.isSideSolid( pos, side );
            }
            case Cable:
            {
                return true;
            }
            default:
            {
                return super.canPlaceBlockOnSide( world, pos, side, player, stack );
            }
        }
    }

    @Override
    public String getUnlocalizedName( ItemStack stack )
    {
        PeripheralType type = getPeripheralType( stack );
        switch( type )
        {
            case DiskDrive:
            default:
            {
                return "tile.computercraft:drive";
            }
            case Printer:
            {
                return "tile.computercraft:printer";
            }
            case Monitor:
            {
                return "tile.computercraft:monitor";
            }
            case AdvancedMonitor:
            {
                return "tile.computercraft:advanced_monitor";
            }
            case WirelessModem:
            {
                return "tile.computercraft:wireless_modem";
            }
            case WiredModem:
            case WiredModemWithCable:
            {
                return "tile.computercraft:wired_modem";
            }
            case Cable:
            {
                return "tile.computercraft:cable";
            }
            case AdvancedModem:
            {
                return "tile.computercraft:advanced_modem";
            }
        }
    }

    // IPeripheralItem implementation

    @Override
    public final PeripheralType getPeripheralType( ItemStack stack )
    {
        return getPeripheralType( stack.getItemDamage() );
    }
}
