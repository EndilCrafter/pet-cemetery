package com.teamabnormals.pet_cemetery.common.entity;

import com.teamabnormals.pet_cemetery.core.other.PCUtil;
import com.teamabnormals.pet_cemetery.core.registry.PCEntityTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;

public class SkeletonCat extends Cat {

	public SkeletonCat(EntityType<? extends SkeletonCat> type, Level worldIn) {
		super(type, worldIn);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Cat.createAttributes()
				.add(Attributes.MAX_HEALTH, 10.0D - PCUtil.HEALTH_DIFF)
				.add(Attributes.MOVEMENT_SPEED, 0.3F + PCUtil.SPEED_DIFF)
				.add(Attributes.ATTACK_DAMAGE, 3.0D + PCUtil.DAMAGE_DIFF);
	}

	@Override
	public SkeletonCat getBreedOffspring(ServerLevel world, AgeableMob entity) {
		SkeletonCat cat = PCEntityTypes.SKELETON_CAT.get().create(world);
		if (this.random.nextBoolean()) {
			cat.setVariant(this.getVariant());
		} else {
			cat.setVariant(cat.getVariant());
		}

		if (this.isTame()) {
			cat.setOwnerUUID(this.getOwnerUUID());
			cat.setTame(true);
			if (this.random.nextBoolean()) {
				cat.setCollarColor(this.getCollarColor());
			} else {
				cat.setCollarColor(cat.getCollarColor());
			}
		}

		return cat;
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.SKELETON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SKELETON_DEATH;
	}
}