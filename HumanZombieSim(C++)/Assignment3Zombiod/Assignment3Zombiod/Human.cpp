//
// Created by wlhba on 2022-11-16.
//

#include "Human.h"

Human::Human() {
    type = 'H';

}

Human::Human(City *city, int x, int y) : Organism(city, x, y) {
    //when making a new human you need to specify the city, the x and y coordinates
    //the human should be placed in the city at the specified coordinates
    //the human should be given a summoning sickness (can't do anything for 1 turn)
    //a new human should have "moved" already
    moved = false;
    this->city = city;
    this->type = 'H';

}

Human::~Human() {

}

void Human::move() {
    //get the 4 adjacent squares
    //if there is anything in an adjacent square, it can't move there
    //if the human has survived 3 turns it can recruit a human into the city
    moved = true;
    vector<int> directions;
    if (x > 0) {//checks if the human is in the first column
        //check if the organism in the square is a taken
        if (city->getOrganism(x - 1, y) == nullptr) {
            directions.push_back(this->WEST);
        }
    }
    if (x < width - 1) {//checks if the human is in the last column
        if (city->getOrganism(x + 1, y) == nullptr) {
            directions.push_back(this->EAST);
        }
    }
    if (y > 0) {//checks if the human is in the first row
        if (city->getOrganism(x, y - 1) == nullptr) {
            directions.push_back(this->NORTH);
        }
    }
    if (y < height - 1) {//checks if the human is in the last row
        if (city->getOrganism(x, y + 1) == nullptr) {
            directions.push_back(this->SOUTH);
        }
    }

    //make new human in the new square
    if (!directions.empty()) {//if the vector is not empty
        //randomly select a square to move to
        int random = rand() % directions.size();
        switch (directions[random]) {
            case WEST:
                city->setOrganism(this, (x - 1), y);
                city->setOrganism(nullptr, x, y);
                this->setPosition(x - 1, y);
                break;
            case EAST:
                city->setOrganism(this, (x + 1), y);
                city->setOrganism(nullptr, x, y);
                this->setPosition(x + 1, y);
                break;
            case NORTH:
                city->setOrganism(this, x, y - 1);
                city->setOrganism(nullptr, x, y);
                this->setPosition(x, y - 1);
                break;
            case SOUTH:
                city->setOrganism(this, x, y + 1);
                city->setOrganism(nullptr, x, y);
                this->setPosition(x, y + 1);
                break;
        }
    }
    //empty array
    directions.clear();
    //if the human has survived 3 turns it can recruit a human into the city
    if (moveConter == 3) {
        //recruit a human into the city
        //the human should be placed in the city at the specified coordinates
        //the human should be given a summoning sickness (can't do anything for 1 turn)
        if (x > 0) {//checks if the human is in the first column
            //check if the organism in the square is a taken
            if (city->getOrganism(x - 1, y) == nullptr) {
                directions.push_back(this->WEST);
            }
        }
        if (x < width - 1) {//checks if the human is in the last column
            if (city->getOrganism(x + 1, y) == nullptr) {
                directions.push_back(this->EAST);
            }
        }
        if (y > 0) {//checks if the human is in the first row
            if (city->getOrganism(x, y - 1) == nullptr) {
                directions.push_back(this->NORTH);
            }
        }
        if (y < height - 1) {//checks if the human is in the last row
            if (city->getOrganism(x, y + 1) == nullptr) {
                directions.push_back(this->SOUTH);
            }
        }
        if (!directions.empty()) {
            int random = rand() % directions.size();
            switch (directions[random]) {
                //make new human in the new square
                case WEST:
                    city->setOrganism(new Human(city, x - 1, y), x - 1, y);
                    cout << "Human recruited at " << x - 1 << ", " << y << endl;
                    break;
                case EAST:
                    city->setOrganism(new Human(city, x + 1, y), x + 1, y);
                    cout << "Human recruited at " << x + 1 << ", " << y << endl;
                    break;
                case NORTH:
                    city->setOrganism(new Human(city, x, y - 1), x, y - 1);
                    cout << "Human recruited at " << x << ", " << y - 1 << endl;
                    break;
                case SOUTH:
                    city->setOrganism(new Human(city, x, y + 1), x, y + 1);
                    cout << "Human recruited at " << x << ", " << y + 1 << endl;
                    break;
            }
        }
        moveConter = 0;
    }
    moveConter++;
}
