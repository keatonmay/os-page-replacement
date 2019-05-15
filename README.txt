Keaton May
krm170730
CS 4348.004

This program runs 4 different page replacement algorithms on a file which contains reference strings delimited by new lines, with pages delimited by spaces. Program takes 2 command line arguments: file name and integer number of frames.

To compile and run, use following commands:

make

java page_replacement fileName numberOfFrames


Program outputs average number of page faults for each algorithm over the lines in the file.