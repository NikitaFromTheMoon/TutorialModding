package net.bored_moon.examplemod.block.custom.entity;

import net.bored_moon.examplemod.block.custom.IdolBlock;
import net.bored_moon.examplemod.item.ModItems;
import net.bored_moon.examplemod.screeen.IdolMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.antlr.v4.runtime.misc.NotNull;
import javax.annotation.Nullable;

public class IdolBlockEntity extends BlockEntity implements MenuProvider {
    
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public IdolBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.IDOL_BLOCK.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> IdolBlockEntity.this.progress;
                    case 1 -> IdolBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> IdolBlockEntity.this.progress = value;
                    case 1 -> IdolBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
        }
    };

    private LazyOptional<ItemStackHandler> lazyItemHandler = LazyOptional.empty();



    //public static final VoxelShape SHAPE = Stream.of(
    //        Block.box(2, -16, 3, 13, -2, 13),
    //        Block.box(0, -2, 5, 15, 12, 11),
    //        Block.box(3, 12, 6, 12, 24, 10)
    //).reduce((v1, v2) -> VoxelShape.join(v1, v2, IBooleanFunction.OR)).get();

    //@Override
    //public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
    //    return SHAPE;
//
    //}

    @Override
    public Component getDisplayName() {
        return Component.literal("Iremon's idol");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {

        return new IdolMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {

        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, IdolBlockEntity entity) {
        if (level.isClientSide()) {
            return;
        }
        if (hasRecipe(entity)) {
            entity.progress++;
            setChanged(level, pos, state);

            if (entity.progress >= entity.maxProgress) {
                craftItem(entity);

            } else {
                entity.resetProgress();
                setChanged(level, pos, state);
            }
        }
    }
    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(IdolBlockEntity entity) {

        if (hasRecipe(entity)) {
            entity.itemHandler.extractItem(1, 1, false);
            entity.itemHandler.setStackInSlot(2, new ItemStack(ModItems.FIRE_GEM.get(),
                    entity.itemHandler.getStackInSlot(2).getCount() + 1));

            entity.resetProgress();
        }
    }
    private static boolean hasRecipe(IdolBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        boolean hasFireGemInSlot = entity.itemHandler.getStackInSlot(1).getItem() == ModItems.FIRE_GEM.get();
        //boolean has

        return hasFireGemInSlot  && canInsertAmountInOutputSlot(inventory) &&
                canInsertItemInOutputSlot(inventory, new ItemStack(ModItems.FIRE_GEM.get(), 1));
    }

    private static boolean canInsertAmountInOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }

    private static boolean canInsertItemInOutputSlot(SimpleContainer inventory, ItemStack itemStack) {
        return inventory.getItem(2).getItem() == itemStack.getItem() ||
                inventory.getItem(2).isEmpty();
    }
}
