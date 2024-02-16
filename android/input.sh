#!/bin/sh
adb -d shell input text $1
adb -d shell input keyevent 66