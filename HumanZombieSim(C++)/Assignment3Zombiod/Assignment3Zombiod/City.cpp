//
// Created by wlhba on 2022-11-16.
//

#include "City.h"
#include "Zombie.h"
#include "Human.h"
#include "Organism.h"

City::City() {
    // declare a city
    // 2D array of Organism pointers
    // initialize all pointers to nullptr
    // create a human and a zombie
    // place them in the city
    // set the human and zombie pointers to point to the human and zombie objects
    int width = GRID_WIDTH;
    int height = GRID_HEIGHT;
    //sets everything to null pointers
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
            grid[i][j] = nullptr;
        }
    }

    //define A few organisms on the grid
    Human *human = new Human(this, 1, 1);
    Human *human2 = new Human(this, 2, 15);
    Human *human3 = new Human(this, 3, 3);
    Zombie *zombie = new Zombie(this, 15, 15);
    Zombie *zombie2 = new Zombie(this, 13, 17);
    Zombie *zombie3 = new Zombie(this, 5, 5);
    //let the city know the organisms are on the board
    grid[1][1] = human;
    grid[2][15] = human2;
    grid[3][3] = human3;
    grid[15][15] = zombie;
    grid[13][17] = zombie2;
    grid[5][5] = zombie3;
}

City::~City() {

}

bool City::hasDiversity() { //making sure one of each is alive
//use a for loop to loop thru the grid and check if there is humans and zombies
    int zcount = 0, hcount = 0, tcount = 0;
    for (int i = 0; i < GRID_WIDTH; i++) {
        for (int j = 0; j < GRID_HEIGHT; j++) {
            if (grid[i][j] != nullptr) {
                if (grid[i][j]->getType() == 'H') {
                    hcount++;
                } else if (grid[i][j]->getType() == 'Z') {
                    zcount++;
                }
                tcount++;
            }
        }
    }
    if (zcount > 0 && hcount > 0) {
        return true;
    } else {
        return false;
    }
}

void City::setOrganism(Organism *organism, int x, int y) {
    this->grid[y][x] = organism;
}

void City::move() {
    //this is the function that will be called in main to move all the organisms
    //loop thru the grid and call the move function for each organism
    //if the organism is a human, call the recruit function
    //if the organism is a zombie, call the infect function
    //otherwise the organism is null pointer, do nothing
    for (int i = 0; i < GRID_WIDTH; i++) {
        for (int j = 0; j < GRID_HEIGHT; j++) {
            if (grid[i][j] != nullptr) {
                if (grid[i][j]->isTurn()) {
                    grid[i][j]->move();
                }
            }
        }
    }
    this->reset();//reset the moved variable to false
}

ostream &operator<<(ostream &output, City &city) {
    //this allows us to cout << CityObject
    //it will print out the grid
    //if there is a human, print H
    //if there is a zombie, print Z
    //if there is neither, print ~

    for (int i = 0; i < GRID_WIDTH; i++) {
        for (int j = 0; j < GRID_HEIGHT; j++) {

            if (city.grid[i][j] == nullptr) {
                output << " ~ ";
            } else if (city.grid[i][j]->type == 'H') {
                char type = city.grid[i][j]->getType();
                output << " H ";
            } else if (city.grid[i][j]->type == 'Z') {
                char type = city.grid[i][j]->getType();
                output << " Z ";
            }
        }//inner loop
        output << endl;
    }//outer loop

    return output;
}

void City::reset() {
    for (auto &i: this->grid) {
        for (auto &j: i) {
            if (j != nullptr) {
                j->endTurn();
            }
            //end the turn for all organisms
        }//inner loop
    }//outer loop
}//end reset

unsigned char City::getGeneration() {
    return 0;
}

int City::countType(char type) {
    //this will count all the remaining organisms of a certain type
    //it will be used in the hasDiversity function
    int organismCount = 0;
    //if the organism type is H, count all the H's
    //if the organism type is Z, count all the Z's
    //return the count

    for (int i = 0; i < GRID_WIDTH; i++) {
        for (int j = 0; j < GRID_HEIGHT; j++) {
            if (grid[i][j] != nullptr) {
                if (grid[i][j]->type == type) {
                    organismCount++;
                }
            }
        }
    }

    return organismCount;
}

Organism *City::getOrganism(int x, int y) {
    if (x < 0 || x >= GRID_WIDTH || y < 0 || y >= GRID_HEIGHT) {
        return nullptr;
    }
    return grid[y][x];
}