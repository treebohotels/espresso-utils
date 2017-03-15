#CONFIG
FLAVOR="qa"
PACKAGE_NAME="com.treebo.bumblebee"

AVD_NAME_1="Treebo_Emulator_1"
AVD_NAME_2="Treebo_Emulator_2"
PORT_1=5559
PORT_2=5569

##############################################################################

#Ensure the Test APK is built already.
#TEST_APK_FILE="app/build/outputs/apk/app-${FLAVOR}-debug-androidTest-unaligned.apk"
#if [ ! -f "${TEST_APK_FILE}" ]
#then
#	echo "androidTest APK doesn't exist, exiting.  Make sure you run ./gradlew app:assembleQaDebug app:assembleQaDebugAndroidTest"
#exit
#else
#	echo "androidTest APK Exists, continuing"
#fi

#Calculate the Serial Number of the emulator instance
SERIAL_1=emulator-${PORT_1}

echo "Creating (forceful) AVD with name ${AVD_NAME_1}"
# We have to echo "no" because it will ask us if we want to use a custom hardware profile, and we don't.
echo "no" | android create avd \
    --name "${AVD_NAME_1}" \
    --target "android-22" \
    --abi "google_apis/x86_64" \
    --skin "WXGA800-7in" \
	--force
echo "AVD ${AVD_NAME_1} created."

#Start the Android Emulator
#"2>&1" combines stderr and stdout into the stdout stream
START_EMULATOR_1="emulator \
	-avd ${AVD_NAME_1} \
	-netspeed full \
	-gpu on \
	-netdelay none \
	-port ${PORT_1} \
	-partition-size 2047"

echo $START_EMULATOR_1
$START_EMULATOR_1 2>&1 &

#Ensure Android Emulator has booted successfully before continuing
EMU_BOOTED='unknown'
while [[ ${EMU_BOOTED} != *"stopped"* ]]; do
    sleep 5
    EMU_BOOTED=`adb -s ${SERIAL_1} shell getprop init.svc.bootanim || echo unknown`
done

duration=$(( SECONDS - start ))
echo "Android Emulator started after $duration seconds."

#Calculate the Serial Number of the emulator instance
SERIAL_2=emulator-${PORT_2}

echo "Creating (forceful) AVD with name ${AVD_NAME_2}"
# We have to echo "no" because it will ask us if we want to use a custom hardware profile, and we don't.
echo "no" | android create avd \
    --name "${AVD_NAME_2}" \
    --target "android-22" \
    --abi "google_apis/x86_64" \
    --skin "WXGA800-7in" \
    --sdcard "1024M" \
	--force
echo "AVD ${AVD_NAME_2} created."

#Start the Android Emulator
#"2>&1" combines stderr and stdout into the stdout stream
START_EMULATOR_2="emulator \
	-avd ${AVD_NAME_2} \
	-netspeed full \
	-gpu on \
	-netdelay none \
	-port ${PORT_2} \
	-partition-size 2047"

echo $START_EMULATOR_2
$START_EMULATOR_2 2>&1 &

#Ensure Android Emulator has booted successfully before continuing
EMU_BOOTED='unknown'
while [[ ${EMU_BOOTED} != *"stopped"* ]]; do
    sleep 5
    EMU_BOOTED=`adb -s ${SERIAL_2} shell getprop init.svc.bootanim || echo unknown`
done

duration=$(( SECONDS - start ))
echo "Android Emulator started after $duration seconds."

# Get the file name of the QA APK (we need to cope with the version number changing)
#APK_LIST_AS_TEXT="$(ls -t app/build/outputs/apk/*-android-debug-*-${FLAVOR}.apk)"
#APK_FILES=( $APK_LIST_AS_TEXT )

# Use the Spoon utility as a test runner
#SPOON_COMMAND="java -jar spoon-runner-1.2.0-jar-with-dependencies.jar \
#	-serial ${SERIAL} \
#	--apk ${APK_FILES[0]} \
#	--test-apk ${TEST_APK_FILE} \
#	--no-animations \
#	--fail-on-failure \
#	--debug
#echo "Running: ${SPOON_COMMAND}"
#${SPOON_COMMAND}

# Use the Spoon utility as a test runner
SPOON_COMMAND="./gradlew spoonStagingAndroidTest -PspoonClassName=com.treebo.bumblebee.testsuites.SanityTestSuite"
echo "Running: ${SPOON_COMMAND}"
${SPOON_COMMAND}

#Stop the Android Emulator
#echo "Killing the Android Emulator with serial: ${SERIAL_1}"
#adb -s ${SERIAL_1} emu kill
#echo "Killing the Android Emulator with serial: ${SERIAL_2}"
#adb -s ${SERIAL_2} emu kill