#!/bin/bash
echo "Running ..."

# javac src/Main.java
# java -cp src Main "scenario.txt"

find * -name "*.java" > sources.txt
javac @sources.txt

java ro.academyplus.avaj.simulator.Simulator scenario.txt

echo "End of run.."