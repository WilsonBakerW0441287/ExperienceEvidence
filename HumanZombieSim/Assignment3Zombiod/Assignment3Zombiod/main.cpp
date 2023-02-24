//
// Created by W0441287.
//
//Example main.cpp
//This supposes that city->step() calls the move method of each organism in the city
//in a single pass causing each to perform all tasks that it can.

#include <iostream>
#include <chrono>
#include <thread>
#include "GameSpecs.h"
#include "Organism.h"
#include "City.h"
#include "Human.h"
#include "Zombie.h"

using namespace std;

void ClearScreen() {
    cout << "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
}

int main() {
    City *city = new City();

    chrono::milliseconds interval(
            ITERATIONS);
    while (city->hasDiversity()) { //while both humans and zombies exist
        this_thread::sleep_for(interval);
        ClearScreen();
        city->move(); //includes all actions
//// Alternate multi-pass version with each activity happening in its own
//// pass through the array. Lends itself to prototyping and testing:
//        //   city->humansMove();
//        //   city->zombiesMoveEat();
//        //   city->humansRecruit();
//        //   city->zombiesRecruit();
//        //   city->zombiesStarve();
//        //   city->countOrganisms(Z or H goes here);
//
//        city->reset(); //resets moved flags and count of organisms
//        //city->countOrganisms(Z or H);// run once for each type
        cout << *city << endl; //prints city
//        cout << "GENERATION " << city->getGeneration() << endl;
        cout << "HUMANS: " << city->countType('H') << endl;
        cout << "ZOMBIES: " << city->countType('Z') << endl;
        cout << "Generation: " << city->generation << endl;
        city->generation++;

    }//end while
}//end main




//In the turn method for both humans and zombies,
// you can implement the various actions that each organism can take,
// such as move, recruit, eat, breed, starve, etc. You can also use the
// asMoved flag to ensure that each organism can only move once per turn.

//After the turn method is called, you can perform additional passes to reset
// movement flags and prepare the city for printing. When printing the city,
// you can use the overloaded << operator to print each organism in the
// appropriate color (e.g. blue for humans and red for zombies).

//To avoid bias of movement, you can use a vector to store the valid targets
// for each action (e.g. move, eat, recruit), and then randomly select from
// among those targets.
