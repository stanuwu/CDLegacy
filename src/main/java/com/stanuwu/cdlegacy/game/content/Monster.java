package com.stanuwu.cdlegacy.game.content;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum Monster {
    SLIME("Slime", "A mindless creature, formed through strange goo flowing out of the walls of certain caves. Comes in a variety of colours.", 0, 45, 0, 5),

    // CD2 LEGACY
    WILD_HOUND("Wild Hound", "A stray dog, craving only food, not affection.", 50, 0, 100, 0, 10),
    RED_LIZARD("Red Lizard", "A common reptile, often mistaken for a young dragon.", 40, 0, 100, 0, 9),
    BLORB("Blorb", "Bloated, fallen off flesh from another monster, which somehow formed a life of its own. Sometimes it even grows limbs, wobbling around and being a disgusting sight to behold.", 40, 0, 100, 0, 7),
    EYE_EATING_CROW("Eye-Eating Crow", "A crow who lives around cemeteries, attacking the grieving when there is no corpse to eat.", 35, 0, 85, 0, 7),
    WOODEN_MIMIC("Wooden Mimic", "A wooden chest corrupted by evil, engaging everyone who tries to open it.", 50, 0, 95, 0, 9),
    GOBLIN_BANDIT("Goblin Bandit", "Hostile goblin wielding a melee weapon.", 50, 0, 100, 5, 15),
    STABBING_GOBLIN("Stabbing Goblin ", "A goblin with murderous intent, enjoying the sounds of their shiv.", 45, 0, 100, 6, 15),
    ROAMING_THUNDERCLOUD("Roaming Thundercloud", "The creation of a wizard's spell, forgotten by its caster.", 30, 0, 100, 5, 50),
    STEEL_MIMIC("Steel Mimic", "A steel chest corrupted by evil, engaging everyone who tries to open it", 70, 0, 90, 0, 18),
    BANDIT("Bandit", "A human whose path led them to a life of crime.", 65, 0, 100, 10, 25),
    BANDIT_LEADER("Bandit Leader", "The leader of a small group of bandits. Some are independent, some are lieutenants of a larger bandit group.", 75, 0, 100, 11, 30),
    CONJOINED_BLORBS("Conjoined Blorbs", "Two Blorbs who conjoined completely by accident.", 80, 0, 100, 15, 21),
    LOST_SPIRIT("Lost Spirit", "The ghost of a human who found a horrifying death, trapped in the world of the living until the kill another unfortunate soul.", 50, 0, 50, 15, 40),
    SILVER_MIMIC("Silvery Mimic", "A chest made of silver corrupted by evil, engaging everyone who tries to open it.", 90, 0, 85, 15, 30),
    GOBLIN_BANDIT_SQUAD("Goblin Bandit Squad", "A squad of weak goblin bandits, but they come in numbers.", 80, 0, 90, 15, 37),
    GOBLIN_BANDIT_SQUAD_LEADER("Goblin Bandit Squad Leader", "A dwarfish orc posing as a goblin, serving as a leader of a bandit squad.", 85, 0, 95, 16, 33),
    ORC_BANDIT("Orc Bandit", "Hostile orc wielding a melee weapon, though they only use their fists sometimes.", 90, 0, 90, 20, 35),
    BLACK_DRAGON("Black Dragon", "A dragon who has been ousted by its family when it was young. It survived, only motivated by its anger.", 150, 0, 70, 20, 40),
    FALLEN_ANGEL("Fallen Angel", "Deserters, who could not longer bear the ignorance of their maker. They were granted to roam the world of the living, but only to be punished with ill thoughts after arriving.", 120, 0, 100, 20, 40),
    CORRUPTED_SPIRIT("Corrupted Spirit", "The soul of a person who killed before when they were alive. Sadly, the don't use their second chance for a betterment.", 100, 0, 50, 25, 60),
    GOLDEN_MIMIC("Golden Mimic", "A chest made of gold corrupted by evil, engaging everyone who tries to open it.", 100, 0, 80, 30, 50),
    PLATINUNM_MIMIC("Platinum Mimic", "A chest made of platinum corrupted by evil, engaging everyone who tries to open it.", 130, 0, 75, 40, 50),
    BLACK_MOTHER_DRAGON("Black Mother Dragon", "A dragon who has been ousted by its family when it was young, who started a family of her own after killing her parents.", 300, 0, 70, 50, 80),
    QUEJUKU("Quejuku", "A creature so strange no one ever manged to describe it accurately on paper. The accounts differ so much that many scholars belief that it changes certain aspect of its body regarding to what their opponent fears the most.", 999, 0, 100, 100, 100),
    PLAIN_SLIME("Plain Slime", "A slime so plain it's often not even considered as a threat.", 45, 0, 100, 0, 5),
    AMATEURISH_BANDIT("Amateurish Bandit", "Even a regular slime is a more dangerous threat than this good for nothing. They often give up the life of crime after one lost battle.", 40, 0, 100, 0, 7),
    CORRUPTED_CRITTER("Corrupted Critter", "Small mammals like rabbits or rats who had their once fearful mind warped into a crazy state.", 30, 0, 95, 0, 8),
    LITTLE_EYE("Little Eye", "An still functional eye of a watcher which was shed voluntarily in order to observe their domain.", 25, 0, 100, 0, 6),
    BROWN_WOLF("Brown Wolf", "The smallest species of wolf who dares to attack humans. Only slightly bigger than a fox.", 60, 0, 100, 5, 15),
    BROWN_ALPHA_WOLF("Brown Alpha Wolf", "The leader of a pack of brown wolves. Can hold their own against larger wolf species.", 70, 0, 100, 8, 20),
    SMALLTIME_POACHER("Smalltime Poacher", "A hunter without a license or ethics. Doesn't take it lightly when someones marches into their hunting grounds.", 55, 0, 100, 8, 20),
    GREEN_OGRE("Green Ogre", "A giant monster armed with an even larger club. Protective of its territory.", 75, 0, 80, 10, 20),
    BANISHED_REAPER("Banished Reaper", "A taker of souls who enjoyed their profession for the wrong reasons.", 100, 0, 100, 20, 50),
    WATCHER("Watcher", "An enormous beast covered with hundreds or even thousand of eyes. One the most dangerous common beasts of the realm.", 250, 0, 100, 40, 60),
    GNAWING_RAT("Gnawing Rat", "A rat who attacks and eats everything it sees, even other rats.", 20, 0, 90, 0, 5),
    GLOWING_BUG("Glowing Bug", "A flesh-eating insect, using its yellowish skin to reflect light.", 35, 0, 100, 5, 10),
    HUNGRY_GHOUL("Hungry Ghoul", "An dwarfish creature with long limbs rotting, poisonous teeth.", 25, 0, 100, 2, 8),
    CAVE_DWELLER("Cave Dweller", "An unfortunate soul which body and mind changed through their unwanted stay in a hideous cave.", 65, 0, 80, 10, 18),
    WHITE_EYED_SCORPION("White-Eyed Scorpion", "A scorpion who is roughly the size of a pig. Lives in caves because the sunlight hurts its eyes.", 40, 0, 85, 8, 18),
    WOLVERINE_SPIDER("Wolverine Spider", "An aggressive arachnid, crawling in the deepest of caves. They got their name from its deadly bite strength.", 50, 0, 100, 15, 50),
    ATAMAKIUM("Atamakium", "A sentinent liquid which is able to control the cave they flowing through, using the rocky walls to attack and defend itself.", 150, 0, 50, 35, 50),
    HIJEDJEN("Hijedjen", "A large, wolf like creature covered in stone skin. It's roar constantly echoes trough a cave once it let it out there.'", 220, 0, 70, 50, 100),
    HUNGRY_DROWNED("Hungry Drowned", "An undead who met their demise at the bottom of the sea.", 30, 0, 100, 0, 8),
    EAR_RIPPING_GULL("Ear-Ripping Gull", "A kind of seagull know to attack humanoids. Ornithologist believe this behavior is a result of the main prey of the gulls: An ear-like shaped mollusc.", 40, 0, 85, 0, 6),
    WASHED_UP_PIRATE("Washed Up Pirate", "A pirate who was nearly killed in a storm, roaming the shores.", 60, 0, 100, 10, 28),
    STRANDPLUENDERER("Strandplünderer", "Scavengers of a strange speaking tribe, searching the beaches for cargo of ship wrecks. They don't like to share.", 75, 0, 100, 15, 33),
    LISHKIT_CRAB("Lishkit-Crab", "A crab named after the giant Lishkit because of its enormous size. Its claws are known for their ability to cut nearly every material into two.", 90, 0, 80, 35, 50),
    LISHKIT_LOBSTER("Lishkit-Lobster", "A lobster named after the giant Lishkit because of its enormous size. It's claws are even stronger than the claws of a Lishkit-Crab.", 130, 0, 80, 45, 65),
    ROAMING_SKELETON("Roaming Skeleton", "A soul cursed to rot in its already rotten body.", 30, 0, 100, 0, 10),
    ROTTING_UNDEAD("Rotting Undead", "A zombie who has been (not) alive for over a century.", 35, 0, 100, 5, 12),
    SKELETON_WARRIOR("Skeleton Warrior", "The remains of a fallen warrior whose desire for blood hasn't even been quenched by their demise.", 45, 0, 80, 10, 25),
    SKELETON_ARCHER("Skeleton Archer", "Robbed of their eyes through their decay, they began to fire at everything the sense.", 45, 0, 100, 15, 30),
    FRESH_UNDEAD("Fresh Undead", "A zombie who turned only moments ago. Some of them even can use some of their old moves in the afterlife.", 50, 0, 100, 15, 30),
    ANCIENT_SPIRIT("Ancient Spirit", "The ghost of a human who has forgotten their own death. Some say the ony kill in order to remember how they died.", 40, 0, 50, 20, 45),
    NECROMANCER_OF_THE_ORDER("Necromancer of the Order", "A member of an evil organisation who specializes in necromancy.", 80, 0, 100, 30, 40),
    MIRROR_PORTRAIT("Mirror Portrait", "An image of yourself from a time line where you died a gruesome death.", 100, 0, 10, 50, 10),
    SANDSPEWER("Sandspewer", "An aggressive reptile who lunges sand to attack its prey.", 40, 0, 100, 0, 8),
    KAKAKA("Kakaka", "A bird like creature made out of cacti. Their claws sting like a regular cactus.", 35, 0, 100, 0, 9),
    MALNOURISHED_MUMMY("Malnourished Mummy", "A mummy who regained their cut out organs, having regained a sense of hunger.", 35, 0, 100, 0, 12),
    YELLOW_SKINNED_SCORPION("Yellow-Skinned Scorpion", "A subspecies of the White-Eyed Scorpion. A nocturnal threat found in most deserts.", 40, 0, 85, 8, 18),
    SAND_GOLEM("Sand Golem", "A giant humanoid made out of sand. Its hard to land a solid attack.", 80, 0, 60, 15, 20),
    SADWONMOR("Sadwonmor", "A gigantic worm slithering on and under the giant dunes of the desert, devouring everything along its way.", 300, 0, 100, 50, 80),
    CONVICT_IN_HIDING("Convict in Hiding", "A criminal who made the woods their new home.", 50, 0, 100, 0, 7),
    WANDERING_TREE("Wandering Tree", "A tree whose roots started to become legs to hunt the fiends who destroyed their habitat.", 80, 0, 90, 10, 20),
    BLACK_WOLF("Black Wolf", "The wolf species people instantly think about when you talk about wolves. The rarely attack humans.", 70, 0, 100, 10, 22),
    BLAK_ALPHA_WOLF("Black Alpha Wolf", "The leader of a pack of black wolves. Often has scars around it eyes gained in battle over the leadership.", 80, 0, 100, 11, 25),
    SCARRED_CRIZZLY("Scarred Grizzly", "A big bear who lost its cubs to a hunting party. The scars show the battle which soon followed after.", 85, 0, 100, 15, 40),
    SUCKING_MOSQUITO("Sucking Mosquito", "The smallest and biggest pest of the jungle.", 20, 0, 70, 0, 5),
    BLACK_GILLED_PIRANHA("Black-Gilled Piranha", "A predator found in many rivers and streams. There black gills give off an awful stench.", 30, 0, 85, 0, 8),
    TROPICAL_POACHER("Tropical Poacher", "A poacher who specialises killing mammals of the jungle.", 60, 0, 100, 10, 20),
    ANCIENT_TEMPLE_GUARDIAN("Ancient Temple Guardian", "A sentinel made out of a violet stone, protecting an old temple of a long lost civilization.", 100, 0, 80, 30, 30),
    MUJULIN("Mijulin", "A giant lizard like being made out of the vegetation of its territory. Feasts on the fruits that grows on it own body.", 230, 0, 100, 50, 70),
    CURSED_SNOWMAN("Cursed Snowman", "The favorite joke of ice wizards in the area.", 30, 0, 100, 0, 3),
    ICE_WIZARD_OF_THE_ORDER("Ice Wizard of the Order", "A member of an evil organisation who specializes in ice spells.", 55, 0, 100, 10, 20),
    POLAR_WOLF("Polar Wolf", "A subspecies of the black wolf, found in could regions.", 75, 0, 100, 10, 22),
    POLAR_ALPHA_WOLF("Polar Alpha Wolf", "The leader of a pack of polar wolves. The blood stains in its fur prove its status.", 85, 0, 100, 11, 25),
    SNOW_GOLEM("Snow Golem", "A giant humanoid made out of strange hardened snow.", 80, 0, 90, 10, 20),
    CURSED_ROCK("Cursed Rock", "A rock that has been cursed to roll forever, crushing everything in its path.", 70, 0, 80, 0, 5),
    MOUNTAIN_BANDIT("Mountain Bandit", "A human whose path led them to a life of crime in the mountains.", 65, 0, 90, 10, 25),
    MOUNTAIN_BANDIT_LEADER("Mountain Bandit Leader", "The leader of a small group of mountain bandits. They often command a base high up in the mountains.", 75, 0, 90, 11, 30),
    MOUNTAIN_TROLL("Mountain Troll", "A stupid and grey skinned monster. Dislikes all kinds of intelligent life.", 90, 0, 80, 20, 20),
    STONE_GOLEM("Stone Golem", "A giant humanoid covered in a armor of stones and minerals. Often thought to be a defense mechanism of the mountains they roam.", 100, 0, 70, 10, 20),
    OPTIMISTIC_PYROMANIAC("Optimistic Pyromaniac", "An aspiring fire wizard wannabe, for all the wrong reasons.", 50, 0, 100, 0, 10),
    FIRE_WIZARD_OF_THE_ORDER("Fire Wizard of the Order", "A member of an evil organisation who specializes in fire spells.", 55, 0, 100, 10, 30),
    FIRE_GOLEM("Fire Golem", "They look Like their cousin the Stone Golem, but are covered in fire and found in volcanoes rather than normal mountains.", 110, 0, 80, 15, 40),
    DEVILS_MINION("Devil's Minion", "Asked and granted to bring fear into the lives of the living by the devil himself.", 90, 0, 80, 25, 45),
    MAGMA_GOLEM("Magma Golem", "Only found in mountain ranges including volcanoes, this Golem seems to be a rare fusion of a stone and a fire Golem.", 130, 0, 70, 30, 50),
    BUZZING_DRAGONFLY("Buzzing Dragonfly", "Bigger and more aggressive than normal dragonflies. Easy to kill, but their bites can cause grave wounds.", 20, 0, 100, 3, 30),
    TOXIC_DROWNED("Toxic Drowned", "An undead who met their demise at the bottom of the swamp. Their body is filled with toxic sludge.", 30, 0, 100, 0, 8),
    ALLIGATOR_HUNTER("Alligator Hunter", "A poacher who specialises killing alligators. They are not nice to them.", 70, 0, 100, 10, 22),
    TOXIC_SMOKE("Toxic Smoke", "A cloud o poisonous smoke which is controlled by an otherwise weak spirit.", 60, 0, 100, 10, 10),
    HUNTED_ALLIGATOR("Hunted Alligator", "An alligator agitated through a recent attempt on its life.", 80, 0, 90, 20, 40),
    VISKEN("Visken", "The most hated kind of fish off all fishermen, known for chewing on rods and fingers.", 30, 0, 100, 0, 8),
    PIRATE_BOAT("Pirate Boat", "A small boat full of average pirates armed with sabers.", 80, 0, 100, 10, 25),
    CRYING_SIREN("Crying Siren", "A sea creature who shape shifts into the most loved person of it's prey to lure them in.", 40, 0, 100, 10, 20),
    LION_SHARK("Lion Shark", "A medium sized shark. Known for their yellow color.", 70, 0, 100, 15, 35),
    KIRAWA("Kirawa", "A whale known to attack ships as much as its normal prey. Their teeth are among the strongest from all marine life.", 200, 0, 60, 50, 80),
    CRAZY_MONK("Crazy Monk", "A monk whose mind has been corrupted by only the sins of their own clergy. Attacks random travelers to cleanse their soul.", 25, 0, 100, 0, 12),
    PARANOID_ESCAPEE("Paranoid Escapee", "A prisoner who managed to flee from their cell, using a rather extreme method to ensure that there will be no witnesses.", 50, 0, 100, 5, 18),
    CORRUPT_GUARD("Corrupt Guard", "A corrupt member of the city guard.", 70, 0, 90, 10, 22),
    CORRUPT_GUARD_OFFICER("Corrupt Guard Officer", "A corrupt officer of the city guard.", 75, 0, 90, 11, 25),
    NOBLE_GUARD("Noble Guard", "A guard who doesn't question the orders they are given from they worst of the rich.", 100, 0, 85, 20, 35),
    GREAT_WIZARD_OF_THE_ORDER("Grand Wizard of the Order", "An officer of an evil organisation who specializes in a variety of spells.", 110, 0, 70, 15, 25),
    ;
    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final int minLevel;
    @Getter
    private final int health;
    @Getter
    private final int resistance;
    @Getter
    private final int damage;

    Monster(String name, String description, int minLevel, int health, int resistance, int damage) {
        this.name = name;
        this.description = description;
        this.minLevel = minLevel;
        this.health = health;
        this.resistance = resistance;
        this.damage = damage;
    }

    // CD2 Legacy
    Monster(String name, String description, int health, int w, int resistance, int minLevel, int damage) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.resistance = Math.max(1 - resistance, 0);
        this.minLevel = minLevel;
        this.damage = damage;
    }

    public static Monster randomForLevel(int level) {
        List<Monster> monsters = new ArrayList<>();
        for (Monster m : Monster.values()) {
            if (m.minLevel <= level) monsters.add(m);
        }
        return monsters.get(new Random().nextInt(0, monsters.size()));
    }
}
