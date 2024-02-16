#!/bin/bash

# Define ANSI escape codes
bold=$(tput bold)
orange=$(tput setaf 3)
green=$(tput setaf 2)
reset=$(tput sgr0)

# Get the list of connected devices
devices=$(adb devices | sed "1d" | awk '{print $1}')

# Print the header row
printf "%-35s %-40s %-20s %-40s\n" "Package" "Version" "Version Code" "Stock Version"
echo "------------------------------------------------------------------------------------------------------------------------"

# Iterate over the list of devices and run the command on each device
for device in $devices
do
  # Get the device name, manufacturer, Android OS version, API level, and CPU architecture
  name=$(adb -s $device shell getprop ro.product.model)
  os_version=$(adb -s $device shell getprop ro.build.version.release)
  api_level=$(adb -s $device shell getprop ro.build.version.sdk)
  cpu_arch=$(adb -s $device shell getprop ro.product.cpu.abi)
  echo "${bold}${orange}Device $name (Android $os_version, API $api_level, $cpu_arch)${reset}"
  apps=( "com.android.vending" "com.google.android.gms" )
  for pkg in "${apps[@]}"; do
    pkg_version=$(adb -s $device shell dumpsys package $pkg | grep -m1 versionName | cut -d= -f2 | tr -d '\r')
    pkg_version_code=$(adb -s $device shell dumpsys package $pkg | grep -m1 versionCode | awk -F= '/versionCode=/{print $2}' | awk '{print $1}')
    pkg_stock_version=$(adb -s $device shell dumpsys package $pkg | grep -m2 versionName | tail -n 1 | cut -d= -f2 | tr -d '\r')
    printf "%-35s %-40s %-20s %-40s\n" "$pkg" "$pkg_version" "$pkg_version_code" "$pkg_stock_version"
  done

  echo "------------------------------------------------------------------------------------------------------------------------"
done
