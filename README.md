# _Clickbait Game_

#### _Clickbait Game, 04-10-2017_

#### By _** Stormi, Ryan, Megan, Riley, and Gloria **_

## Description
_Clickbait is an exciting click game where you complete each level by finding and clicking a specified objects. Beat the clock for extra points and try to have the top score in the top scores hall of fame!  _


## Specifications

| Behavior                   | Input Example     | Output Example    |
| -------------------------- | :-----------------:| :-----------------:|
| Application will recognize a Game class with a Game constructor that accepts player name to instantiate a game| Game newGame = new Game("Player Name")| newGame|
| Game will instantiate with beginning score of 0, and start at level 1 | newGame.getScore(), newGame.getLevelId()| 0, 1|

| Application will recognize level details from levels table | newGame.getLevelId(), newGame.getLevelName(), newGame.getLevelInstruction(), newGame.getLevelMillis() | 1, Swordfish, Find the Swordfish, 20000|
| Application will store time from beginning to end of each level, to determine time taken | startTime = 40000, endTime = 40050  | timeTaken = 50|
| Application will calculate level score based on time taken and levelMillis for bonus points | int levelScore = newGame.calculateLevelScore(startTime, endTime, newGame.getLevelMillis()) | levelScore = 20450 |
| Application will add levelScore to total score at the completion of each level | newGame.setScore(levelScore) | original score: 0, new score: 20450 |
| Application will cycle through each level from levels table in order |  | |
| Application will report when all levels are complete and record final score in record of all games | Game.all().contains(newGame)| true |
| Application will list all scores from highest to lowest | Game.getGamesByScore()| *List of Games, sorted from highest to lowest score* |




## Setup/Installation Requirements:

* _Clone the repository_
*_Launch postgres_
*_Launch psql_
*_Run the following command in psql>  CREATE DATABASE clickbait_game;_
*_Run the following command in Terminal$  psql clickbait_game < clickbait_game.psql_  
* _Run the following command in Terminal$ gradle run_
* _Open browser and go to localhost:4567_


### License

Copyright (c) 2017

This software is licensed under the MIT license.
