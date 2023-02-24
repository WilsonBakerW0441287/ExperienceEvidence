The goal for this project is to create a modified 2D predator-prey simulation (aka a zombie apocalypse). In this simulation the prey are humans and the predators are zombies. These organisms live in a 20 x 20 grid of cells (a city) and are represented using simple ASCII characters (e.g., ‘Z’ and ‘H’) when the grid is printed.

Humans behave according to the following rules (in order):

MOVE. For every time step, the humans randomly try to move (aka run screaming) up, down, left, or right. If the neighboring cell in the selected direction is occupied or would move the human out of the city (i.e. off the grid), then the human stays in the current cell.

RECRUIT. If a human survives for three time-steps, at the end of the time step the human will recruit a new human into an adjacent empty space. This is simulated by creating a new human in an adjacent (up, down, left, or right – i.e. diagonal not allowed) cell that is empty. If there is no empty cell available, no recruiting occurs. Once a recruit is produced, a human cannot recruit again until it has survived three more steps. If humans are still alive and are unable to recruit (i.e. are surrounded by zombies and or edges) their recruit counter should be reset to zero.

Zombies behave according to the following rules (in order):

MOVE/EAT. For every time step, the zombie will move to an adjacent cell (including diagonal) containing a human and eat the human. Note that a zombie will not eat another zombie (because they taste bad). If there are no humans, the zombie will simply move to empty position.

BREED. If a zombie survives for eight time-steps, and after it has moved/eaten, it will convert a randomly selected adjacent (including diagonal) human into a zombie (i.e. replace the human with a new zombie). If a zombie is still alive and is unable to convert, their breed counter should retain a state so that they will convert a human provided the very next opportunity (post move/eat).

STARVE. If the zombie has not eaten any humans within three time-steps, at the end of the third time step the virus will lose its hold and the zombie will convert to a human (i.e. replace the zombie with a new human).


Requirements:

Implement as a polymorphic process that does not require down-casting, however Organisms may have a variable identifying their type.

Create a class named Organism that encapsulates basic data common to all humans and zombies. This class should have a pure virtual function (aka abstract) named ‘turn’ overridden in the sub classes Human and Zombie.

Only one organism may occupy a cell at a time.

Based on a time event (timer or thread sleep), the city will be updated and printed.

It would be a good idea to group all functionality of each organism into a single “turn” method (move, recruit) for humans and (move, eat, breed, starve) for zombies, and call this once in a single pass. This does not stop you from making separate methods for each action, just that one call makes them all happen.

After the turn method but before the city is ready for printing (and the next time-step) additional passes may be required for such things as resetting movement flags, etc.

The grid is enclosed, so an organism is not allowed to move off the edges of the city.

Each organism attempts to perform actions (e.g. move, eat, recruit) every time step.

Colors must be used help differentiate humans from zombies.

Set a flag in each organism to ensure that they can only move once per turn.

The city should be printed using the overloaded << operator (i.e. cout << myCity).

All move, eat, and breed recruit / convert routines must identify valid targets and randomly select amongst those targets (located in a vector). This is to avoid ‘bias of movement’.
![Gif of Result of ](https://github.com/WilsonBakerW0441287/ExperienceEvidence/blob/main/HumanZombieSim%28C%2B%2B%29/Images/ZombiesVSHumans.gif)
