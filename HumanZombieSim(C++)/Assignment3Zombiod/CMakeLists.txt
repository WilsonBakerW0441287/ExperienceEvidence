cmake_minimum_required(VERSION 3.24)
project(Assignment3Zombiod)

set(CMAKE_CXX_STANDARD 14)

include_directories(Assignment3Zombiod)

add_executable(Assignment3Zombiod
        Assignment3Zombiod/City.cpp
        Assignment3Zombiod/City.h
        Assignment3Zombiod/GameSpecs.cpp
        Assignment3Zombiod/GameSpecs.h
        Assignment3Zombiod/Human.cpp
        Assignment3Zombiod/Human.h
        Assignment3Zombiod/main.cpp
        Assignment3Zombiod/Organism.cpp
        Assignment3Zombiod/Organism.h
        Assignment3Zombiod/Zombie.cpp
        Assignment3Zombiod/Zombie.h)
