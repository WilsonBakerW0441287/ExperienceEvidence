//
// Created by wlhba on 2022-11-16.
//

#include "Organism.h"
#include "GameSpecs.h"

Organism::Organism() {
//an organisms properties: type(h or z), x and y position, move_counter, boolean has_moved, turn_counter, pointer to city object
//we need a method to let the organism know what city it is in, and what its x and y position is,
//as well as if the square it can move to is empty or not, i.e if there is another organism there, or it is the Border
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

bool Organism::isTurn() {
    //checks if the organism has moved or not
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
}
