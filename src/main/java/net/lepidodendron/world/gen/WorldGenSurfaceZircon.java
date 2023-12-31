package net.lepidodendron.world.gen;

import net.lepidodendron.block.BlockLavaRockZirconOre;
import net.lepidodendron.util.Functions;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSurfaceZircon extends WorldGenerator
{

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        boolean flag = false;

        for (int i = 0; i < 22; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if ((!worldIn.provider.isNether() || blockpos.getY() < 254) && (blockpos.getY() >= Functions.getAdjustedSeaLevel(worldIn, blockpos) - 1) && worldIn.isAirBlock(blockpos)
            	&& (
            		((worldIn.getBlockState(blockpos.down())).getMaterial() == Material.GROUND)
            		|| ((worldIn.getBlockState(blockpos.down())).getMaterial() == Material.GRASS)
            		|| ((worldIn.getBlockState(blockpos.down())).getMaterial() == Material.SAND)
                    || ((worldIn.getBlockState(blockpos.down())).getMaterial() == Material.ROCK)
                    || ((worldIn.getBlockState(blockpos.down())).getMaterial() == Material.CLAY)
            	)
            )
            {
                Functions.setBlockStateAndCheckForDoublePlant(worldIn,blockpos.down(), BlockLavaRockZirconOre.block.getDefaultState(), 2);
                flag = true;
            }
        }

        return flag;
    }
}
