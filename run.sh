#!/bin/bash
echo -e "Running ..."

find . -name "*.java" > sources.txt
javac @sources.txt

echo -e "Test: Without arguement:"
java ro.academyplus.avaj.simulator.Simulator
if [ $? -ne 0 ]; then
    echo -e "❌ Test failed\n"
else
    echo -e "✅ Test passed\n"
fi

echo -e "Test: With more than 1 arguement:"
java ro.academyplus.avaj.simulator.Simulator testScenario/empty.txt testScenario/empty.txt
if [ $? -ne 0 ]; then
    echo -e "❌ Test failed\n"
else
    echo -e "✅ Test passed\n"
fi

echo -e "Test: With empty file:"
java ro.academyplus.avaj.simulator.Simulator testScenario/empty.txt
if [ $? -ne 0 ]; then
    echo -e "❌ Test failed\n"
else
    echo -e "✅ Test passed\n"
fi

echo -e "Test: With missing simulation cycle:"
java ro.academyplus.avaj.simulator.Simulator testScenario/missingSimulationCycle.txt
if [ $? -ne 0 ]; then
    echo -e "❌ Test failed\n"
else
    echo -e "✅ Test passed\n"
fi

echo -e "Test: With negative simulation cycle:"
java ro.academyplus.avaj.simulator.Simulator testScenario/negativeSimulationCycle.txt
if [ $? -ne 0 ]; then
    echo -e "❌ Test failed\n"
else
    echo -e "✅ Test passed\n"
fi

echo -e "Test: With invalid simulation cycle:"
java ro.academyplus.avaj.simulator.Simulator testScenario/invalidSimulationCycle.txt
if [ $? -ne 0 ]; then
    echo -e "❌ Test failed\n"
else
    echo -e "✅ Test passed\n"
fi

echo -e "Test: With invalid coordinates:"
java ro.academyplus.avaj.simulator.Simulator testScenario/invalidCoordinates.txt
if [ $? -ne 0 ]; then
    echo -e "❌ Test failed\n"
else
    echo -e "✅ Test passed\n"
fi

echo -e "Test: With negative coordinates:"
java ro.academyplus.avaj.simulator.Simulator testScenario/negativeCoordinates.txt
if [ $? -ne 0 ]; then
    echo -e "❌ Test failed\n"
else
    echo -e "✅ Test passed\n"
fi

echo -e "Test: With negative heights:"
java ro.academyplus.avaj.simulator.Simulator testScenario/negativeHeights.txt
if [ $? -ne 0 ]; then
    echo -e "❌ Test failed\n"
else
    echo -e "✅ Test passed\n"
fi

echo -e "Test: With over a hundred heights:"
java ro.academyplus.avaj.simulator.Simulator testScenario/overHundredHeight.txt
if [ $? -ne 0 ]; then
    echo -e "❌ Test failed\n"
else
    echo -e "✅ Test passed\n"
fi

echo -e "Test: With wrong aircraft type:"
java ro.academyplus.avaj.simulator.Simulator testScenario/wrongAirCraftType.txt
if [ $? -ne 0 ]; then
    echo -e "❌ Test failed\n"
else
    echo -e "✅ Test passed\n"
fi

echo -e "Test: Valid scenario file"
java ro.academyplus.avaj.simulator.Simulator testScenario/scenario.txt
if [ $? -ne 0 ]; then
    echo -e "❌ Test failed\n"
else
    echo -e "✅ Test passed\n"
fi

find . -name "*.class" -delete

rm sources.txt

echo -e "End of run.."