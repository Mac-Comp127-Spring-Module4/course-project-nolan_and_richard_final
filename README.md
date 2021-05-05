# Doodle Jump Remastered

COMP 127-03
Richard Kim and Nolan Meyer

# ===========================================

Our goal was to recreate the popular game "Doodle Jump". Due to our time constraints and limited knowledge, we made a simpler version. Rather then the infinite scrolling in the original version, we made more of a "jumper" game where the "doodle" object bounces on jump pads. Some extra functionalities include the jump pads being randomly set on the bottom and resetting with each hop as well as the obstacles that random spawn in the side that the doodle has to dodge. We made the obstacles come in from the left side to increase the difficulty since each set of spawned jump pads will usually include one near the left side, but with the obstacles, it makes that jump pad more difficult to access.

# ===========================================

In our program, the main method is in DoodleJumpGame.java. When you press run, it opens a new Canvaswindow that is your game window. There is a pause at the beginning for the player to read the instructions. You only have one life and if your doodle dies, the window will say "Game Over! Your score was: __".

# ===========================================

The continuous randomization of the jump pads was a very difficult part of the project. Resetting the jump pad with each interaction between the doodle and the jump pad was a little confusing, especially with our lack of experience with the random package. Further, it was difficult to implement the obstacles randomly as well as figuring out a good way for them to maneuver through the canvas without making it impossible for the player.
