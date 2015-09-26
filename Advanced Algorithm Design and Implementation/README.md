1. Big Oh:
    f(n) is O(g(n)), If c and n0, f(n) <= c.g(n) for all n > n0

2. Big Omega:
    f(n) is Omega (g(n)), If c and n0, f(n) >= c.g(n) for all n > n0

Bill Byrne best explains Big Oh, Big Omega and Big Theta - https://www.youtube.com/watch?v=ei-A_wy5Yxw

Some other best source - https://www.youtube.com/user/cannykicks/videos


Calculate double hashing:

print ( ( ( (2 * numb) + 5) % 11) + ( (7 - (numb % 7) ) ) ) % 11 

Loop invariant:

(In insertion sort) At the beginning of each iteration of the for loop, which is indexed by j , the subarray consisting of elements A[1 .. j - 1] constitutes the currently sorted hand, these properties of A[1 .. j - 1] formally is a loop invariant. [CLRS]

Initialization: It is true prior to the first iteration of the loop.
Maintenance: If it is true before an iteration of the loop, it remains true before the
next iteration.
Termination: When the loop terminates, the invariant gives us a useful property
that helps show that the algorithm is correct.
