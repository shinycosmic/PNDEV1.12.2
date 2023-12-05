package net.lepidodendron.world.gen;

import net.lepidodendron.block.*;
import net.lepidodendron.util.Functions;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenJurassicVolcanos extends WorldGenerator
{

    public static final PropertyInteger LAYERS = PropertyInteger.create("layers", 1, 8);

    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (position = position.add(-8, 0, -8); position.getY() > 5 && worldIn.isAirBlock(position); position = position.down())
        {
            ;
        }

        if (position.getY() <= 4)
        {
            return false;
        }
        else {

            int j = position.getY();

            BlockPos startpos = new BlockPos(position.getX(), j + 1, position.getZ());
            int coneRadius = rand.nextInt(60) + 50;

            if (coneRadius + 30 < j) {
                return false;
            }

            //if (!worldIn.canSeeSky(pos.add(0, (coneRadius-coneHeight) - lavaRadius, 0))) {
            //	return;
            //}

            //System.err.println("Volcano: " + startpos.getX() + " " + startpos.getY() + " " + startpos.getZ());
            //System.err.println("coneRadius: " + coneRadius);

            boolean lava;
            if (rand.nextInt(4) != 0) {
                lava = true;
            }
            else
            {
                lava = false;
            }
            int yct = 1;
            int xct = 0;
            int zct = 0;
            BlockPos pos = null;
            int heightrandomiser = 0;
            while (yct <= coneRadius) {
                //System.err.println("yct: " + yct);
                pos = startpos.add(rand.nextInt(3)-1,0,rand.nextInt(3)-1);
                if (Math.random() > 0.66) {
                    heightrandomiser = heightrandomiser + rand.nextInt(2);
                }
                xct = -((coneRadius - yct) + heightrandomiser);
                while (xct < (coneRadius - yct) + heightrandomiser) {
                    //System.err.println("xct: " + xct);
                    zct = -((coneRadius - yct)  + heightrandomiser);
                    while (zct < (coneRadius - yct) + heightrandomiser) {
                        //System.err.println("zct: " + zct);
                        boolean doBlock;
                        if (Math.random() > 0.4) {
                            //Test elliptical:
                            int rdm1 = rand.nextInt(3);
                            int rdm2 = rand.nextInt(3);
                            doBlock = ( ( (Math.pow(xct, 2)/Math.pow(((coneRadius - yct) + heightrandomiser - rdm1),2)) + (Math.pow(zct, 2)/Math.pow(((coneRadius - yct) + heightrandomiser - rdm2),2)))<=1);
                        }
                        else {
                            //Test circular:
                            doBlock = ((Math.pow(xct, 2) + Math.pow(zct, 2)) <= Math.pow((coneRadius - yct) + heightrandomiser, 2));
                        }
                        if (doBlock) {
                            BlockPos bp = new BlockPos(pos.getX() + xct, yct + 34, pos.getZ() + zct);
                            IBlockState bs = worldIn.getBlockState(bp);
                            BlockPos bpd = new BlockPos(pos.getX() + xct, yct + 33, pos.getZ() + zct);
                            IBlockState bsd = worldIn.getBlockState(bpd);
                            if (bs.getMaterial() != Material.ROCK || !worldIn.isBlockFullCube(bp) || bs.getBlock().isReplaceable(worldIn, bp)) {
                                //Lava column:
                                if (xct == 0 && zct == 0 && lava) {
                                    if (!worldIn.isAirBlock(bp.down())
                                        && !worldIn.isAirBlock(bp.north())
                                        && !worldIn.isAirBlock(bp.south())
                                        && !worldIn.isAirBlock(bp.east())
                                        && !worldIn.isAirBlock(bp.west())
                                        && worldIn.getBlockState(bp.down()).getMaterial() != Material.WATER
                                        && worldIn.getBlockState(bp.north()).getMaterial() != Material.WATER
                                        && worldIn.getBlockState(bp.south()).getMaterial() != Material.WATER
                                        && worldIn.getBlockState(bp.east()).getMaterial() != Material.WATER
                                        && worldIn.getBlockState(bp.west()).getMaterial() != Material.WATER ) {
                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, Blocks.FLOWING_LAVA.getDefaultState(), 2);
                                    }
                                }
                                else {
                                    if (bsd.getMaterial() == Material.CRAFTED_SNOW ||bsd.getMaterial() == Material.SNOW || bsd.getMaterial() == Material.ROCK || bsd.getMaterial() == Material.GROUND || bsd.getMaterial() == Material.SAND) {

                                        boolean isSnow = false;
                                        if (bsd.getMaterial() == Material.CRAFTED_SNOW || bsd.getMaterial() == Material.SNOW) {
                                            isSnow = true;
                                        }

                                        int coneExposedHeight = coneRadius + 34 - 75;
                                        double lavaBlockChance = 1;
                                        if (yct > coneExposedHeight) {
                                            lavaBlockChance = (double)(((double)coneRadius - (double)yct) / ((double)coneExposedHeight));
                                        }

                                        if (Math.random() < lavaBlockChance) {
                                            if (Math.random() > 0.1) {
                                                if (rand.nextInt(32) == 0) {
                                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, BlockLavaRock.block.getDefaultState(), 2);
                                                    if (worldIn.canSnowAt(bp.up(), false) && worldIn.isAirBlock(bp.up())) {
                                                        int layers = rand.nextInt(4) + 1;
                                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.up(), Blocks.SNOW_LAYER.getDefaultState().withProperty(LAYERS, layers), 2);
                                                        if (rand.nextInt(6) == 0) {
                                                            Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, Blocks.SNOW.getDefaultState(), 2);
                                                        }
                                                    }
                                                    if (isSnow) {
                                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.down(), BlockLavaRock.block.getDefaultState(), 2);
                                                    }
                                                }
                                                else {
                                                    if (rand.nextInt(7) != 0) {
                                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, getVolcanoBlock(rand), 2);
                                                        if (worldIn.canSnowAt(bp.up(), false) && worldIn.isAirBlock(bp.up())) {
                                                            int layers = rand.nextInt(4) + 1;
                                                            Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.up(), Blocks.SNOW_LAYER.getDefaultState().withProperty(LAYERS, layers), 2);
                                                            if (rand.nextInt(6) == 0) {
                                                                Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, Blocks.SNOW.getDefaultState(), 2);
                                                            }
                                                        }
                                                        if (isSnow) {
                                                            Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.down(), BlockLavaRock.block.getDefaultState(), 2);
                                                        }
                                                    }
                                                    else {
                                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, BlockScorchedEarth.block.getDefaultState(), 2);
                                                        if (worldIn.canSnowAt(bp.up(), false) && worldIn.isAirBlock(bp.up())) {
                                                            int layers = rand.nextInt(4) + 1;
                                                            Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.up(), Blocks.SNOW_LAYER.getDefaultState().withProperty(LAYERS, layers), 2);
                                                            if (rand.nextInt(6) == 0) {
                                                                Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, Blocks.SNOW.getDefaultState(), 2);
                                                            }
                                                        }
                                                        if (isSnow) {
                                                            Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.down(), BlockLavaRock.block.getDefaultState(), 2);
                                                        }
                                                    }
                                                }
                                            }
                                            else {
                                                if (rand.nextInt(5) == 0) {
                                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, Blocks.COBBLESTONE.getDefaultState(), 2);
                                                    if (worldIn.canSnowAt(bp.up(), false) && worldIn.isAirBlock(bp.up())) {
                                                        int layers = rand.nextInt(4) + 1;
                                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.up(), Blocks.SNOW_LAYER.getDefaultState().withProperty(LAYERS, layers), 2);
                                                        if (rand.nextInt(6) == 0) {
                                                            Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, Blocks.SNOW.getDefaultState(), 2);
                                                        }
                                                    }
                                                    if (isSnow) {
                                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.down(), BlockLavaRock.block.getDefaultState(), 2);
                                                    }
                                                }
                                                else {
                                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, Blocks.STONE.getDefaultState(), 2);
                                                    if (worldIn.canSnowAt(bp.up(), false) && worldIn.isAirBlock(bp.up())) {
                                                        int layers = rand.nextInt(4) + 1;
                                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.up(), Blocks.SNOW_LAYER.getDefaultState().withProperty(LAYERS, layers), 2);
                                                        if (rand.nextInt(6) == 0) {
                                                            Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, Blocks.SNOW.getDefaultState(), 2);
                                                        }
                                                    }
                                                    if (isSnow) {
                                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.down(), BlockLavaRock.block.getDefaultState(), 2);
                                                    }
                                                }
                                            }
                                        }
                                        else {
                                            if (Math.random() > 0.1) {
                                                Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, BlockLavaRock.block.getDefaultState(), 2);
                                                if (worldIn.canSnowAt(bp.up(), false) && worldIn.isAirBlock(bp.up())) {
                                                    int layers = rand.nextInt(4) + 1;
                                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.up(), Blocks.SNOW_LAYER.getDefaultState().withProperty(LAYERS, layers), 2);
                                                    if (rand.nextInt(6) == 0) {
                                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, Blocks.SNOW.getDefaultState(), 2);
                                                    }
                                                }
                                                if (isSnow) {
                                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.down(), BlockLavaRock.block.getDefaultState(), 2);
                                                }
                                            } else { //Ashes:
                                                double randomiser = Math.random();
                                                if (randomiser > 0.70) {
                                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, BlockVolcanicAsh.block.getDefaultState(), 2);
                                                    if (worldIn.canSnowAt(bp.up(), false) && worldIn.isAirBlock(bp.up())) {
                                                        int layers = rand.nextInt(4) + 1;
                                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.up(), Blocks.SNOW_LAYER.getDefaultState().withProperty(LAYERS, layers), 2);
                                                        if (rand.nextInt(6) == 0) {
                                                            Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, Blocks.SNOW.getDefaultState(), 2);
                                                        }
                                                    }
                                                    if (isSnow) {
                                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.down(), BlockLavaRock.block.getDefaultState(), 2);
                                                    }
                                                } else {
                                                    if (Math.random() > 0.40) {
                                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, BlockVolcanicAshLight.block.getDefaultState(), 2);
                                                        if (worldIn.canSnowAt(bp.up(), false) && worldIn.isAirBlock(bp.up())) {
                                                            int layers = rand.nextInt(4) + 1;
                                                            Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.up(), Blocks.SNOW_LAYER.getDefaultState().withProperty(LAYERS, layers), 2);
                                                            if (rand.nextInt(6) == 0) {
                                                                Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, Blocks.SNOW.getDefaultState(), 2);
                                                            }
                                                        }
                                                        if (isSnow) {
                                                            Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.down(), BlockLavaRock.block.getDefaultState(), 2);
                                                        }
                                                    } else {
                                                        if (Math.random() > 0.10) {
                                                            Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, BlockVolcanicAshDark.block.getDefaultState(), 2);
                                                            if (worldIn.canSnowAt(bp.up(), false) && worldIn.isAirBlock(bp.up())) {
                                                                int layers = rand.nextInt(4) + 1;
                                                                Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.up(), Blocks.SNOW_LAYER.getDefaultState().withProperty(LAYERS, layers), 2);
                                                                if (rand.nextInt(6) == 0) {
                                                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, Blocks.SNOW.getDefaultState(), 2);
                                                                }
                                                            }
                                                            if (isSnow) {
                                                                Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.down(), BlockLavaRock.block.getDefaultState(), 2);
                                                            }
                                                        }
                                                        else {
                                                            Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, BlockSulphurOre.block.getDefaultState(), 2);
                                                            if (worldIn.canSnowAt(bp.up(), false) && worldIn.isAirBlock(bp.up())) {
                                                                int layers = rand.nextInt(4) + 1;
                                                                Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.up(), Blocks.SNOW_LAYER.getDefaultState().withProperty(LAYERS, layers), 2);
                                                                if (rand.nextInt(6) == 0) {
                                                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp, Blocks.SNOW.getDefaultState(), 2);
                                                                }
                                                            }
                                                            if (isSnow) {
                                                                Functions.setBlockStateAndCheckForDoublePlant(worldIn,bp.down(), BlockLavaRock.block.getDefaultState(), 2);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (Math.random() > 0.66) {
                            zct = zct + rand.nextInt(2);
                        }
                        else {
                            zct = zct + 1;
                        }
                    }
                    if (Math.random() > 0.66) {
                        xct = xct + rand.nextInt(2);
                    }
                    else {
                        xct = xct + 1;
                    }
                }
                yct = yct + 1;
            }
            //Volcano is made. Now SOMETIMES do a lava lake:

            if (lava) {
                BlockPos posLake = new BlockPos(pos.getX(), 0, pos.getZ());
                //What radius do we have here?
                int radiusLake = xct - 2;
                //Dig it out - some will spill over, which is what we want:

                //Sometimes sink the lake, sometimes overspill it:
                int digger = rand.nextInt(8) + 25;
                yct = yct + 25;
                for (int i = 0; i < digger; i++) {
                    xct = -radiusLake;
                    while (xct <= radiusLake) {
                        zct = -radiusLake;
                        while (zct <= radiusLake) {
                            if ((Math.pow((int) xct, 2) + Math.pow((int) zct, 2)) <= Math.pow((int) radiusLake, 2)) {
                                worldIn.setBlockToAir(posLake.add(xct, yct + 33, zct));
                            }
                            zct += 1;
                        }
                        xct += 1;
                    }
                    yct -= 1;
                }

                xct = -radiusLake;
                while (xct <= radiusLake) {
                    zct = -radiusLake;
                    while (zct <= radiusLake) {
                        if ((Math.pow((int) xct, 2) + Math.pow((int) zct, 2)) <= Math.pow((int) radiusLake, 2)) {
                            if (!worldIn.isAirBlock(posLake.add(xct, yct + 32, zct))) {
                                Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct, yct + 33, zct), Blocks.FLOWING_LAVA.getDefaultState(), 3);
                                if (worldIn.isAirBlock(posLake.add(xct + 1, yct + 33, zct))) {
                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct + 1, yct + 33, zct), Blocks.FLOWING_LAVA.getDefaultState(), 3);
                                }
                                if (worldIn.isAirBlock(posLake.add(xct - 1, yct + 33, zct))) {
                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct - 1, yct + 33, zct), Blocks.FLOWING_LAVA.getDefaultState(), 3);
                                }
                                if (worldIn.isAirBlock(posLake.add(xct, yct + 33, zct + 1))) {
                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct, yct + 33, zct + 1), Blocks.FLOWING_LAVA.getDefaultState(), 3);
                                }
                                if (worldIn.isAirBlock(posLake.add(xct, yct + 33, zct - 1))) {
                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct, yct + 33, zct - 1), Blocks.FLOWING_LAVA.getDefaultState(), 3);
                                }
                            }
                        }
                        zct += 1;
                    }
                    xct += 1;
                }
                //Lake done, shall we make it one deeper?
                if (radiusLake > 3) {
                    xct = -(radiusLake - 3);
                    while (xct <= (radiusLake - 3)) {
                        zct = -(radiusLake - 3);
                        while (zct <= (radiusLake - 3)) {
                            if ((Math.pow((int) xct, 2) + Math.pow((int) zct, 2)) <= Math.pow((int) (radiusLake - 3), 2)) {
                                if (!worldIn.isAirBlock(posLake.add(xct, yct + 31, zct))) {
                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct, yct + 32, zct), Blocks.FLOWING_LAVA.getDefaultState(), 3);
                                }
                            }
                            zct += 1;
                        }
                        xct += 1;
                    }
                }
                //Add diamonds under the lake:
                if (radiusLake > 5) {
                    xct = -(radiusLake - 5);
                    while (xct <= (radiusLake - 5)) {
                        zct = -(radiusLake - 5);
                        while (zct <= (radiusLake - 5)) {
                            if ((Math.pow((int) xct, 2) + Math.pow((int) zct, 2)) <= Math.pow((int) (radiusLake - 5), 2)) {
                                if (Math.random() > 0.8 && worldIn.getBlockState(posLake.add(xct, yct + 31, zct)).getMaterial() != Material.LAVA) {
                                    if (!worldIn.isAirBlock(posLake.add(xct, yct + 30, zct))) {
                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct, yct + 31, zct), Blocks.DIAMOND_ORE.getDefaultState(), 3);
                                    }
                                }
                            }
                            zct += 1;
                        }
                        xct += 1;
                    }
                }
                if (radiusLake > 7) {
                    xct = -(radiusLake - 7);
                    while (xct <= (radiusLake - 7)) {
                        zct = -(radiusLake - 7);
                        while (zct <= (radiusLake - 7)) {
                            if ((Math.pow((int) xct, 2) + Math.pow((int) zct, 2)) <= Math.pow((int) (radiusLake - 7), 2)) {
                                if (Math.random() > 0.8 && worldIn.getBlockState(posLake.add(xct, yct + 30, zct)).getMaterial() != Material.LAVA) {
                                    if (!worldIn.isAirBlock(posLake.add(xct, yct + 29, zct))) {
                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct, yct + 30, zct), Blocks.DIAMOND_ORE.getDefaultState(), 3);
                                    }
                                }
                            }
                            zct += 1;
                        }
                        xct += 1;
                    }
                }
            }
            else {
                //Do a water lake:
                BlockPos posLake = new BlockPos(pos.getX(), 0, pos.getZ());
                //What radius do we have here?
                int radiusLake = xct - 2;
                //Dig it out - some will spill over, which is what we want:

                //Sometimes sink the lake, sometimes overspill it:
                int digger = rand.nextInt(8) + 15;
                yct = yct + 15;
                for (int i = 0; i < digger; i++) {
                    xct = -radiusLake;
                    while (xct <= radiusLake) {
                        zct = -radiusLake;
                        while (zct <= radiusLake) {
                            if ((Math.pow((int) xct, 2) + Math.pow((int) zct, 2)) <= Math.pow((int) radiusLake, 2)) {
                                worldIn.setBlockToAir(posLake.add(xct, yct + 23, zct));
                            }
                            zct += 1;
                        }
                        xct += 1;
                    }
                    yct -= 1;
                }

                xct = -radiusLake;
                while (xct <= radiusLake) {
                    zct = -radiusLake;
                    while (zct <= radiusLake) {
                        if ((Math.pow((int) xct, 2) + Math.pow((int) zct, 2)) <= Math.pow((int) radiusLake, 2)) {
                            if (!worldIn.isAirBlock(posLake.add(xct, yct + 22, zct))) {
                                Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct, yct + 23, zct), Blocks.FLOWING_WATER.getDefaultState(), 3);
                                if (worldIn.isAirBlock(posLake.add(xct + 1, yct + 23, zct))) {
                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct + 1, yct + 23, zct), Blocks.FLOWING_WATER.getDefaultState(), 3);
                                }
                                if (worldIn.isAirBlock(posLake.add(xct - 1, yct + 23, zct))) {
                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct - 1, yct + 23, zct), Blocks.FLOWING_WATER.getDefaultState(), 3);
                                }
                                if (worldIn.isAirBlock(posLake.add(xct, yct + 23, zct + 1))) {
                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct, yct + 23, zct + 1), Blocks.FLOWING_WATER.getDefaultState(), 3);
                                }
                                if (worldIn.isAirBlock(posLake.add(xct, yct + 23, zct - 1))) {
                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct, yct + 23, zct - 1), Blocks.FLOWING_WATER.getDefaultState(), 3);
                                }
                            }
                        }
                        zct += 1;
                    }
                    xct += 1;
                }
                //Lake done, shall we make it one deeper?
                if (radiusLake > 3) {
                    xct = -(radiusLake - 3);
                    while (xct <= (radiusLake - 3)) {
                        zct = -(radiusLake - 3);
                        while (zct <= (radiusLake - 3)) {
                            if ((Math.pow((int) xct, 2) + Math.pow((int) zct, 2)) <= Math.pow((int) (radiusLake - 3), 2)) {
                                if (!worldIn.isAirBlock(posLake.add(xct, yct + 21, zct))) {
                                    Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct, yct + 22, zct), Blocks.FLOWING_WATER.getDefaultState(), 3);
                                }
                            }
                            zct += 1;
                        }
                        xct += 1;
                    }
                }

                //Add magma blocks under the lake:
                if (radiusLake > 5) {
                    xct = -(radiusLake - 5);
                    while (xct <= (radiusLake - 5)) {
                        zct = -(radiusLake - 5);
                        while (zct <= (radiusLake - 5)) {
                            if ((Math.pow((int) xct, 2) + Math.pow((int) zct, 2)) <= Math.pow((int) (radiusLake - 5), 2)) {
                                if (Math.random() > 0.9 && worldIn.getBlockState(posLake.add(xct, yct + 21, zct)).getMaterial() != Material.WATER) {
                                    if (!worldIn.isAirBlock(posLake.add(xct, yct + 20, zct))) {
                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct, yct + 21, zct), Blocks.MAGMA.getDefaultState(), 3);
                                    }
                                }
                            }
                            zct += 1;
                        }
                        xct += 1;
                    }
                }
                if (radiusLake > 7) {
                    xct = -(radiusLake - 7);
                    while (xct <= (radiusLake - 7)) {
                        zct = -(radiusLake - 7);
                        while (zct <= (radiusLake - 7)) {
                            if ((Math.pow((int) xct, 2) + Math.pow((int) zct, 2)) <= Math.pow((int) (radiusLake - 7), 2)) {
                                if (Math.random() > 0.9 && worldIn.getBlockState(posLake.add(xct, yct + 20, zct)).getMaterial() != Material.WATER) {
                                    if (!worldIn.isAirBlock(posLake.add(xct, yct + 19, zct))) {
                                        Functions.setBlockStateAndCheckForDoublePlant(worldIn,posLake.add(xct, yct + 20, zct), Blocks.MAGMA.getDefaultState(), 3);
                                    }
                                }
                            }
                            zct += 1;
                        }
                        xct += 1;
                    }
                }

            }
            return true;
        }
    }

    public IBlockState getVolcanoBlock (Random rand) {
        IBlockState iblockstate = Blocks.STONE.getStateFromMeta(1); //Granite
        if (rand.nextInt(2) == 0) {
            iblockstate = BlockPrehistoricGroundLush.block.getDefaultState();
        }
        else if (rand.nextInt(2) == 0) {
            iblockstate = BlockPrehistoricGroundMossy.block.getDefaultState();
        }
        else if (rand.nextInt(3) == 0) {
            iblockstate = BlockCoarseSandyDirtBlack.block.getDefaultState();
        }
        else if (rand.nextInt(3) == 0) {
            iblockstate = Blocks.DIRT.getStateFromMeta(2); //Podzol
        }
        return iblockstate;
    }
}
