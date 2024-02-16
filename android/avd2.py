#!/usr/bin/env python3
import subprocess
import inquirer
import os

def list_avds():
    """List all Android emulators."""
    command = ["emulator", "-list-avds"]
    output = subprocess.check_output(command).decode("utf-8")
    return output.splitlines()

def main():
    avds = list_avds()

    choices = []
    for avd in avds:
        choices.append(avd)

    item = inquirer.prompt([
        inquirer.List('item', message='Select an emulator', choices=choices)
    ])

    print('Launching the emulator {}...'.format(item['item']))

    # Get the ANDROID_HOME path.
    android_home = os.environ['ANDROID_HOME']

    # Append the ANDROID_HOME path to the emulator command.
    emulator_binary = os.path.join(android_home, 'emulator', 'emulator')

    emulator_command = [emulator_binary, "-avd", item['item']]

    # Create a file object to write the output of the command to.
    output_file = open('emulator.log', 'w')

    # Check if the user wants to start the emulator in normal mode, cold boot mode, or wipe data mode.
    action = inquirer.prompt([
        inquirer.List('action', message='Launch options', choices=['Normal', 'Cold boot', 'Wipe data'])
    ])

    if action['action'] == 'Normal':
        pass
    elif action['action'] == 'Cold boot':
        emulator_command.append("-no-snapshot-load")
    elif action['action'] == 'Wipe data':
        emulator_command.append("-wipe-data")

    # Run the command in the background and redirect its output to the file object.
    process = subprocess.Popen(emulator_command, stdout=output_file, stderr=output_file)

    # Close the file object.
    output_file.close()

if __name__ == "__main__":
    main()
