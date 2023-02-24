//
// Created by wlhba on 2022-11-16.
//

#include "Organism.h"
#include "GameSpecs.h"

Organism::Organism() {
//an organism needs to have these properties: type(h or z), x and y position, move_counter, boolean has_moved, turn_counter, pointer to city object
//we need a method to let the organism know what city it is in, and what its x and y position is,
// aswell as if the square it can move to is empty or not, i.e if there is another organism there or it is the end of the "arena"
//Here is an example of how you might create a class named Organism that encapsulates basic data common to all humans and zombies. This class should have a pure virtual function (aka abstract) named turn that is overridden in the sub classes Human and Zom123
//Copy code
//class Organism {
//public:
//    // Pure virtual function (aka abstract function)
//    virtual void turn() = 0;
//    // Other members
//    bool hasMoved; // Flag to ensure that organism can only move once per turn
//    int row, col;  // Current position of the organism on the grid
//};

}

//GRIDSIZE is the size of the board
Organism::Organism(City *city, int x, int y) {
    this->width = GRIDSIZE;
    this->height = GRIDSIZE;
    this->city = city;
    //when making a new organism you need to specify the city, the width and height of the city
    this->x = x;
    this->y = y;
}

Organism::~Organism() {

}


void Organism::endTurn() {
    //set the has_moved flag to false
    moved = false;
}

bool Organism::isTurn() { //checks if the organism has moved or not
    //if the organism has moved, it is not its turn
    //if the organism has not moved, it is its turn
    return !moved;
}

ostream &operator<<(ostream &output, Organism *organism) {
    output << "I am a human." << endl;
    return output;
}

char Organism::getType() const {
    return this->type;
}

int Organism::getY() {
    return this->y;
}

int Organism::getX() {
    return this->x;
}

void Organism::setPosition(int x, int y) {
    this->x = x;
    this->y = y;

}

void Organism::setType(char type) {
    this->type = type;
}

void Organism::die() {
    //set the organism to null
    this->city->setOrganism(nullptr, this->x, this->y);
    //delete the organism
//    delete this;

}
