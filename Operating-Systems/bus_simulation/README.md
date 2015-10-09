###Goal:

The purpose of the simulation is to observe the behavior of the system, and answer the following questions:
- Do the distances between two consecutive buses keep
uniform? If not, what should be done to ensure they are
uniform?
- What is the average size of a waiting queue at each stop
(and what are its maximum and minimum)?

###Events:

1. person: A person arrives in the queue at a bus stop. After a random (exponentially-distributed inter-arrival) time, another person is scheduled to arrive in the
queue

2. arrival: A bus arrives at a bus stop. If there is no one in the queue, the bus proceeds to the next stop.

3. boarder: A person boards the bus and the length of the queue diminishes by 1; If the queue is now empty, the bus proceeds to the next stop, otherwise the next passenger boards the bus


###Assumptions:
- The stops are equally spaced in a circle
- The buses may not pass one another
- 15 bus stops
- 5 buses
- The time to drive between any two contiguous stops is 5 minutes
- The passenger’s mean arrival rate at each stop is 5 persons/min
- The boarding time is 2 seconds for each passenger
