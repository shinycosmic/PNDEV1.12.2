package net.lepidodendron.world.gen;

import net.lepidodendron.block.BlockCladophlebis;
import net.lepidodendron.util.Functions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenCladophlebis extends WorldGenerator
{

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        boolean flag = false;

        for (int i = 0; i < 28; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (blockpos.getY() >= Functions.getAdjustedSeaLevel(worldIn, blockpos)-4 && worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254) && BlockCladophlebis.block.canPlaceBlockAt(worldIn, blockpos))
            {
                Functions.setBlockStateAndCheckForDoublePlant(worldIn,blockpos, BlockCladophlebis.block.getDefaultState(), 2);
                flag = true;
            }
        }

        return flag;
    }
}
