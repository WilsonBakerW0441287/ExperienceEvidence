//
// Created by W0441287.
//
//Example main.cpp

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
        cout << *city << endl; //prints city
//        cout << "GENERATION " << city->getGeneration() << endl;
        cout << "HUMANS: " << city->countType('H') << endl;
        cout << "ZOMBIES: " << city->countType('Z') << endl;
        cout << "Generation: " << city->generation << endl;
        city->generation++;

    }//end while
}//end main