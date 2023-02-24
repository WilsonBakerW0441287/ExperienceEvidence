//
// Created by wlhba on 2022-11-16.
//

#ifndef ASSIGNMENT3ZOMBIOD_ZOMBIE_H
#define ASSIGNMENT3ZOMBIOD_ZOMBIE_H


#include "Organism.h"

class Zombie : public Organism {
public:
    enum {
        WEST, NORTHWEST, NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, NUM_DIRECTIONS
    };

    Zombie();

    Zombie(City *city, int width, int height);

    virtual ~Zombie();

    void move();

    void starve(int x, int y);
};

#endif //ASSIGNMENT3ZOMBIOD_ZOMBIE_H
