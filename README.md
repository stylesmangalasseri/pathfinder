# pathfinder
Solution for finding the shortest path in given custom route map using ASTART Algorithm

A* (A-Star) search algorithm
--------------------------------
    The A* (A-Star) search algorithm is a path-finding algorithm with many uses, including Artificial Intelligence for games. The search builds the route from tile to tile until it reaches the goal. 
To help choose the best possible tile the algorithm will use an equation that takes into account the distance from the tile to the goal and the cost of moving to that tile.

Custom route map details
--------------------------------
Non-walkable:

    Water (~)

Walkable:

    Flatlands (. or @ or X)
    Forest (*)
    Mountain (^)
    
Movement Cost for Terrain:

    N/A = Water (~)
    1 = Flatlands (. or @ or X)
    2 = Forest (*)
    3 = Mountain (^)

System requirement to execute
--------------------------------
1. JAVA 1.7 or above
    https://java.com/en/download/
2. MAVEN 3.0.3 or above
    https://maven.apache.org/download.cgi

Steps to execute
--------------------------------

1. Checkout the source code from GITHUB
2. Compile the source code using the command "mvn clean install". this will download dependency from central repository
3. run the source code using the command "mvn exec:java"
   Please add the input and output map details using VM arguments as follows
   
   Eg: mvn exec:java  -DINPUT_FILE_NAME=C:\Users\user\Desktop\large_map.txt -DOUTPUT_FILE_NAME=C:\Users\user\Desktop\large_map_out.txt