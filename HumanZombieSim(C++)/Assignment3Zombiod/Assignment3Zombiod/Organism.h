//
// Created by wlhba on 2022-11-16.
//

#ifndef ASSIGNMENT3ZOMBIOD_ORGANISM_H
#define ASSIGNMENT3ZOMBIOD_ORGANISM_H

#include <vector>
#include <iostream>
#include "City.h"

using namespace std;

class City;

class Organism
{
protected:
    int x;
    int y;
    int width;
    int height;
    bool moved= false;
    int moveConter=0;
    City *city;

public:

    char type;
    Organism();
    Organism( City *city, int x, int y );
    virtual ~Organism();

    virtual void move() = 0;
    void setType(char type);
    void setPosition( int x, int y );
    int getX();
    int getY();
    char getType() const;

    void endTurn();
    bool isTurn();

    friend ostream& operator<<( ostream &output, Organism *organism );

    void die();
};

#endif //ASSIGNMENT3ZOMBIOD_ORGANISM_H
