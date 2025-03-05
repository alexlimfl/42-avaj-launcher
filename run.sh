#!/bin/bash
echo "Running ..."

# javac src/Main.java
# java -cp src Main "scenario.txt"

find * -name "*.java" > sources.txt
javac @sources.txt


java ro.academyplus.avaj.simulator.Simulator testScenario/empty.txt
java ro.academyplus.avaj.simulator.Simulator testScenario/emptySimulationRun.txt
java ro.academyplus.avaj.simulator.Simulator testScenario/invalidCoordinates.txt
java ro.academyplus.avaj.simulator.Simulator testScenario/missingSimulationRun.txt
java ro.academyplus.avaj.simulator.Simulator testScenario/negativeCoordinates.txt
java ro.academyplus.avaj.simulator.Simulator testScenario/negativeHeight.txt
java ro.academyplus.avaj.simulator.Simulator testScenario/negativeSimulationRun.txt
java ro.academyplus.avaj.simulator.Simulator testScenario/overHundredHeight.txt
java ro.academyplus.avaj.simulator.Simulator testScenario/wrongAirCraftType.txt
java ro.academyplus.avaj.simulator.Simulator testScenario/scenario.txt

find . -name "*.class" -delete
rm sources.txt

echo "End of run.."