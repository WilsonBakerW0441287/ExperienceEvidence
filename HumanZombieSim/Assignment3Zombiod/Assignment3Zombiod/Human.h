//
// Created by wlhba on 2022-11-16.
//

#ifndef ASSIGNMENT3ZOMBIOD_HUMAN_H
#define ASSIGNMENT3ZOMBIOD_HUMAN_H

#include "Organism.h"

class Human : public Organism {
public:
    enum {
        WEST, NORTH, EAST, SOUTH, NUM_DIRECTIONS
    };

    Human();

    Human(City *city, int x, int y);

    virtual ~Human();

    void move();

};

#endif
