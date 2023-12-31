package net.lepidodendron.world.gen;

import net.lepidodendron.block.BlockAridHorsetail;
import net.lepidodendron.block.BlockFurcifolium;
import net.lepidodendron.util.Functions;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFurcifolium extends WorldGenerator
{

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        boolean flag = false;

        for (int i = 0; i < 48; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4));

            if (blockpos.getY() >= Functions.getAdjustedSeaLevel(worldIn, blockpos)-4 && worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 254) && BlockAridHorsetail.block.canPlaceBlockAt(worldIn, blockpos))
            {
                boolean waterCriteria = false;
                //Is there water nearby?
                int xct = -4;
                int yct;
                int zct;
                while ((xct < 5) && (!waterCriteria)) {
                    yct = -3;
                    while ((yct <= 0) && (!waterCriteria)) {
                        zct = -4;
                        while ((zct < 5) && (!waterCriteria)) {
                            if ((worldIn.getBlockState(position.add(xct, yct, zct))).getMaterial() == Material.WATER) {
                                waterCriteria = true;
                            }
                            zct = zct + 1;
                        }
                        yct = yct + 1;
                    }
                    xct = xct + 1;
                }
                if (waterCriteria) {
                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,blockpos, BlockFurcifolium.block.getDefaultState(), 2);
                    flag = true;
                }
            }
        }

        return flag;
    }
}
