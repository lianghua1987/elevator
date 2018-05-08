## Interview

#### Background

The Elevator Problem - ModelA and ModelB

#### Tech Stack

- java8
- maven
- junit

#### Run

```Shell
mvn clean install
```

#### Sample

```
a: ModelA
b: ModelB
d:Default test file runs both ModelA and ModelB
q: Exit
d
Output:
10 8 1 (9)
9 1 5 1 6 1 5 (30)
2 4 1 4 2 6 8 (16)
3 7 9 3 7 5 8 7 11 1 (36)
7 11 6 10 5 6 8 7 4 12 7 8 9 (40)
6 1 8 6 8 (16)
Output:
10 8 1 (9)
9 1 5 6 (13)
2 4 2 1 6 8 (12)
3 5 7 8 9 11 1 (18)
7 11 10 6 5 6 8 12 7 4 8 9 (30)
6 1 6 8 (12)
```

#### Design

##### ModelA

For each line, from current floor, to the next request floor, so on and so forth, sum is calculated by each request's absoluate value, which also include the |FIRST_REQUEST_FLOOR - CURRENT_FLOOR|

##### ModelB

Starting from current floor, and the first request. By subtracting each other to determine the direction. Collect countinous requests for the same direction to a set to prevent duplicate value. And reset the set if the direction changes. Add temp set to a LinkedList to maintain order, be sure to remove the last element of the LinkedList if it has the same value of the first element of next set to prevent duplicate. Here you can't use LinkedHashSet as final result collection because it would remove duplicates for your final result. e.g.

3->4- >7->6->4, if you using LinkedHashSet, eventually you will get 3->4->7->6 which is obvious the wrong answer.