//
// Created by wlhba on 2022-11-16.
//

#include "Zombie.h"
#include "Human.h"
#include "Organism.h"

//this method is eat/move, as if there is a human in the same square as the zombie, the zombie eats the human
//they can also move to an adjacent square
//they can ALSO turn a human into a zombie if the zombie has lived for 3 turns the new zombie a summoning sickness (can't do anything for 1 turn)
//if the zombie has not eaten a human in 8 turns, it turns back into a human

Zombie::Zombie(City *city, int x, int y) : Organism(city, x, y) {
    moved = false;
    this->city = city;
    this->type = 'Z';
    moveConter = 0;
}

Zombie::~Zombie() {

}

void Zombie::move() {
    if (moveConter == 8) {
        starve(x, y);
        return;
    }
    //the zombie has 8 possible moves
    //if there is anything in the adjacent square
    moved = true;
    vector<int> directions;
    vector<Organism *> humanArray;
    int humanX;
    int humanY;
    //the zombie is now going to lookaround for humans
    if (x > 0) {//checks if the zombie is in the first column
        //check if the organism in the square is a taken
        if (city->getOrganism(x - 1, y) != nullptr) {
            if (city->getOrganism(x - 1, y)->getType() == 'H') {
                humanArray.push_back(city->getOrganism(x - 1, y));
            }
        } else if (city->getOrganism(x - 1, y) == nullptr) { directions.push_back(this->WEST); }
    }
    if (x < width - 1) {//checks if the zombie is in the last column
        if (city->getOrganism(x + 1, y) != nullptr) {
            if (city->getOrganism(x + 1, y)->getType() == 'H') {
                humanArray.push_back(city->getOrganism(x + 1, y));
            }
        } else if (city->getOrganism(x + 1, y) == nullptr) {
            directions.push_back(this->EAST);
        }
    }
    if (y > 0) {//checks if the zombie is in the first row
        if (city->getOrganism(x, y - 1) != nullptr) {
            if (city->getOrganism(x, y - 1)->getType() == 'H') {
                humanArray.push_back(city->getOrganism(x, y - 1));
            }
        } else if (city->getOrganism(x, y - 1) == nullptr) {
            directions.push_back(this->NORTH);
        }
    }
    if (y < height - 1) {//checks if the zombie is in the last row
        if (city->getOrganism(x, y + 1) != nullptr) {
            if (city->getOrganism(x, y + 1)->getType() == 'H') {
                humanArray.push_back(city->getOrganism(x, y + 1));
            }
        } else if (city->getOrganism(x, y + 1) == nullptr) {
            directions.push_back(this->SOUTH);
        }
    }
    if (x > 0 && y > 0) {//checks if the zombie is in the first row and first column
        if (city->getOrganism(x - 1, y - 1) != nullptr) {
            if (city->getOrganism(x - 1, y - 1)->getType() == 'H') {
                humanArray.push_back(city->getOrganism(x - 1, y - 1));
            }
        } else if (city->getOrganism(x - 1, y - 1) == nullptr) {
            directions.push_back(this->NORTHWEST);
        }
    }
    if (x < width - 1 && y > 0) {//checks if the zombie is in the first row and last column
        if (city->getOrganism(x + 1, y - 1) != nullptr) {
            if (city->getOrganism(x + 1, y - 1)->getType() == 'H') {
                humanArray.push_back(city->getOrganism(x + 1, y - 1));
            }
        } else if (city->getOrganism(x + 1, y - 1) == nullptr) {
            directions.push_back(this->NORTHEAST);
        }
    }
    if (x > 0 && y < height - 1) {//checks if the zombie is in the last row and first column
        if (city->getOrganism(x - 1, y + 1) != nullptr) {
            if (city->getOrganism(x - 1, y + 1)->getType() == 'H') {
                humanArray.push_back(city->getOrganism(x - 1, y + 1));
            }
        } else if (city->getOrganism(x - 1, y + 1) == nullptr) {
            directions.push_back(this->SOUTHWEST);
        }
    }
    if (x < width - 1 && y < height - 1) {//checks if the zombie is in the last row and last column
        if (city->getOrganism(x + 1, y + 1) != nullptr) {
            if (city->getOrganism(x + 1, y + 1)->getType() == 'H') {
                humanArray.push_back(city->getOrganism(x + 1, y + 1));
            }
        } else if (city->getOrganism(x + 1, y + 1) == nullptr) {
            directions.push_back(this->SOUTHEAST);
        }
    }
    if (!humanArray.empty()) {
        //if there is a human in the array, the zombie will eat a random human
        //pick a random human
        int random = rand() % humanArray.size();
        humanX = humanArray[random]->getX();
        humanY = humanArray[random]->getY();
        //"eat" the human by replacing it with a new zombie
        humanArray[random]->die();
        city->setOrganism(new Zombie(city, humanX, humanY), humanX, humanY);
        cout << "Zombie ate a human at (" << humanX << ", " << humanY << ")" << endl;
        moveConter = 0;
    }
    if (!directions.empty()) {

        int direction = directions[rand() % directions.size()];

        if (direction == this->WEST) {
            city->setOrganism(this, (x - 1), y);
            city->setOrganism(nullptr, x, y);
            x = x - 1;
        }
        if (direction == this->NORTHWEST) {
            city->setOrganism(this, (x - 1), (y - 1));
            city->setOrganism(nullptr, x, y);
            x = x - 1;
            y = y - 1;
        }
        if (direction == this->NORTH) {
            city->setOrganism(this, x, (y - 1));
            city->setOrganism(nullptr, x, y);
            y = y - 1;
        }
        if (direction == this->NORTHEAST) {
            city->setOrganism(this, (x + 1), (y - 1));
            city->setOrganism(nullptr, x, y);
            x = x + 1;
            y = y - 1;
        }
        if (direction == this->EAST) {
            city->setOrganism(this, (x + 1), y);
            city->setOrganism(nullptr, x, y);
            x = x + 1;
        }
        if (direction == this->SOUTHEAST) {
            city->setOrganism(this, (x + 1), (y + 1));
            city->setOrganism(nullptr, x, y);
            x = x + 1;
            y = y + 1;
        }
        if (direction == this->SOUTH) {
            city->setOrganism(this, x, (y + 1));
            city->setOrganism(nullptr, x, y);
            y = y + 1;
        }
        if (direction == this->SOUTHWEST) {
            city->setOrganism(this, (x - 1), (y + 1));
            city->setOrganism(nullptr, x, y);
            x = x - 1;
            y = y + 1;
        }
    }
    moveConter++;
}


void Zombie::starve(int x, int y) {
    //turn the zombie into a human
    city->setOrganism(new Human(city, x, y), x, y);
}
