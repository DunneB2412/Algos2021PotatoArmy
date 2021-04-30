# Algos2021PotatoArmy

Link to repository 
https://github.com/DunneB2412/Algos2021PotatoArmy

Member List and Contribution

Memeber list;
Tianze Zhang,
Brian Sharkey,
Brian Dunne,
Luke Seckerson



Contributions:
Tianze Zhang
I’ve created the StopNameSearch class and the TST class. I also created a queue class which helps the implementation of
the TST. The function getStopsInTST in StopNameSearch takes all the stop names in stops.txt and correctly inserts them
into the TST following the requirements in the specification.The TST is returned and the user are able to search by
either full name and prefix.I also created a function that returns the list of all stop names and stop ids, these lists
are filled when you create a TST and these provide convenience for implementing the UI.

Brian Sharkey
I’ve created the tripFinder class and the tripsInfo class. The trip finder class fulfils part three of the
specifications. It returns a tripsInfo object that contains all of the relevant information on the trips containing the
specified time for ease of display in the GUI. I also wrote my piece in the design document for my methods and classes.

Brian Dunne
I created a representation of stops and trips to be more compatible with Dijkstra’s search. Unfortunately the size of
the search space and the lack of a powerful enough heuristic both rendered A* and Dijkstra’s search unusable. A
considerable amount more time would be required to optimise the search enough to be usable. My aim is to create a
‘plan’ or trip that could connect a -> b.

Luke Seckerson (-_-)
I created the GUI class. It contains all the elements needed to display a menu and display the results for all the
different functions. I embellished the graphical interface with helpful tool tips to guide the user through inputs. I
also implemented a text field that only allowed numbers to be entered which helped the tripFinder class.



