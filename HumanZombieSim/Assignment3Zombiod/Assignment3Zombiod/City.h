//
// Created by wlhba on 2022-11-16.
//

#ifndef ASSIGNMENT3ZOMBIOD_CITY_H
#define ASSIGNMENT3ZOMBIOD_CITY_H


#include <iostream>
#include "GameSpecs.h"

using namespace std;

class Organism;

const int GRID_WIDTH = GRIDSIZE;
const int GRID_HEIGHT = GRIDSIZE;

class City {
protected:
    Organism *grid[GRID_HEIGHT][GRID_WIDTH];

public:
    City();

    int generation = 0;

    virtual ~City();

    Organism *getOrganism(int x, int y);

    void setOrganism(Organism *organism, int x, int y);

    void move();

    friend ostream &operator<<(ostream &output, City &city);

    bool hasDiversity();

    void reset();

    unsigned char getGeneration();

    int countType(char type);


};

#endif