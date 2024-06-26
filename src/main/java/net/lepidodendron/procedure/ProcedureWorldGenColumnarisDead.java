package net.lepidodendron.procedure;


import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.BlockDeadLog;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


@ElementsLepidodendronMod.ModElement.Tag
public class ProcedureWorldGenColumnarisDead extends ElementsLepidodendronMod.ModElement {
	public ProcedureWorldGenColumnarisDead(ElementsLepidodendronMod instance) {
		super(instance, 42);
	}

	public static void executeProcedure ( Object2ObjectOpenHashMap <String, Object> dependencies ) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure WorldGenColumnarisDead!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure WorldGenColumnarisDead!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure WorldGenColumnarisDead!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure WorldGenColumnarisDead!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		int xx = (int) dependencies.get("x");
		int yy = (int) dependencies.get("y");
		int zz = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double TrunkHeight = 0;
		double counter = 0;
		
		Material material = world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getMaterial();
		if ((world.canSeeSky(new BlockPos((int) x, (int) y, (int) z)))
			&& material != Material.GRASS
			&& material != Material.GROUND
			&& material != Material.GLASS
			&& material != Material.IRON
			&& material != Material.ROCK
			&& material != Material.SAND
			&& material != Material.WOOD
			) {			
			world.setBlockToAir(new BlockPos((int) x, (int) y, (int) z));
			
			//Trunk: up to 60 blocks, but makle them uncommon at that size:
			TrunkHeight = 25 + Math.round(Math.random() * 35);
			if ((TrunkHeight >= 50) && (Math.random() > 0.5)) {
				TrunkHeight = Math.round(TrunkHeight * 0.7);
			}
			
			counter = 0;
			while (counter <= TrunkHeight) {
				ProcedureTreeLog.executeProcedure((int) x, (int) (y + counter), (int) z, world, BlockDeadLog.block, EnumFacing.NORTH);
				counter = counter + 1;
			}

			counter = 3;
			while (counter <= TrunkHeight * 0.2) {
				xx = x + 1;
				yy = y + (int) counter;
				zz = z;
				ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockDeadLog.block, EnumFacing.UP);

				xx = x - 1;
				yy = y + (int) counter;
				zz = z;
				ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockDeadLog.block, EnumFacing.UP);

				xx = x;
				yy = y + (int) counter;
				zz = z + 1;
				ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockDeadLog.block, EnumFacing.WEST);

				xx = x;
				yy = y + (int) counter;
				zz = z - 1;
				ProcedureTreeLog.executeProcedure(xx, yy, zz, world, BlockDeadLog.block, EnumFacing.WEST);

				counter = counter + 3;
			}
			while (counter <= TrunkHeight * 0.7) {

				counter = counter + 3;
			}
			
			while (counter <= TrunkHeight * 0.85) {

				counter = counter + 3;
			}

			while (counter <= TrunkHeight) {

				counter = counter + 2;
			}

			
		}
	}
}