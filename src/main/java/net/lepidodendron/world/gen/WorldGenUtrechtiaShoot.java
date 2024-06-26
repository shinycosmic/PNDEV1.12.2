package net.lepidodendron.world.gen;

import net.lepidodendron.block.BlockUtrechtiaLeavesPlaceable;
import net.lepidodendron.block.BlockUtrechtiaLeavesSmallPlaceable;
import net.lepidodendron.util.Functions;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;


public class WorldGenUtrechtiaShoot extends WorldGenerator
{

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        return true;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position, int minHeight, int maxHeight)
    {
        boolean flag = false;

        for(int i = 0; i < 12; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (blockpos.getY() >= Functions.getAdjustedSeaLevel(worldIn, blockpos) - 4 && worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254) && (worldIn.getBlockState(blockpos.down()).getMaterial() == Material.GROUND || worldIn.getBlockState(blockpos.down()).getMaterial() == Material.CLAY || worldIn.getBlockState(blockpos.down()).getMaterial() == Material.SAND) && blockpos.getY() + rand.nextInt(6) - 3 >= minHeight && blockpos.getY() + rand.nextInt(6) - 3 <= maxHeight) {
                if (rand.nextInt(2) == 0) {
                    Functions.setBlockStateAndCheckForDoublePlant(worldIn, blockpos, BlockUtrechtiaLeavesPlaceable.block.getDefaultState().withProperty(BlockUtrechtiaLeavesPlaceable.BlockCustom.FACING, EnumFacing.UP), 2);
                } else {
                    Functions.setBlockStateAndCheckForDoublePlant(worldIn, blockpos, BlockUtrechtiaLeavesSmallPlaceable.block.getDefaultState().withProperty(BlockUtrechtiaLeavesPlaceable.BlockCustom.FACING, EnumFacing.UP), 2);
                }

                flag = true;
            }
        }

        return flag;
    }
}
