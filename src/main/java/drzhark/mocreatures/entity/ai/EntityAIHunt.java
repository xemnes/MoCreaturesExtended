/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.mocreatures.entity.ai;

import com.google.common.base.Predicate;
import drzhark.mocreatures.entity.MoCEntityAnimal;
import drzhark.mocreatures.entity.tameable.MoCEntityTameableAnimal;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIHunt<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {

    private final EntityCreature hunter;
    private final Class<T> targetClass;

    public EntityAIHunt(EntityCreature entity, Class<T> classTarget, int chance, boolean checkSight, boolean onlyNearby, Predicate<EntityLivingBase> predicate) {
        super(entity, classTarget, chance, checkSight, onlyNearby, predicate);
        this.hunter = entity;
        this.targetClass = classTarget;
    }

    public EntityAIHunt(EntityCreature entityCreature, Class<T> classTarget, boolean checkSight) {
        this(entityCreature, classTarget, checkSight, false);
    }

    public EntityAIHunt(EntityCreature entity, Class<T> classTarget, boolean checkSight, boolean onlyNearby) {
        this(entity, classTarget, 10, checkSight, onlyNearby, null);

    }

    @Override
    public boolean shouldExecute() {
        // Conditions: Don't hunt when tamed and target entity is of class Player
        boolean hunterHasOwner = ((MoCEntityTameableAnimal)this.hunter).getIsTamed();
        boolean hunterTargetsPlayers = EntityPlayer.class.isAssignableFrom(this.targetClass);
        return (!hunterTargetsPlayers || !hunterHasOwner) && ((MoCEntityAnimal) this.hunter).getIsHunting() && super.shouldExecute();
    }
}
