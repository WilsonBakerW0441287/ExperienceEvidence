cmake_minimum_required(VERSION 3.23)
project(Assignment3Zombiod)

set(CMAKE_CXX_STANDARD 14)

add_executable(Assignment3Zombiod main.cpp Zombie.cpp Zombie.h Organism.cpp Organism.h City.cpp City.h GameSpecs.cpp GameSpecs.h Human.cpp Human.h)
