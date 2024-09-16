# Litre of Light Android App

This Android application provides a user interface for controlling and monitoring a "Litre of Light" system based on an ESP8266 microcontroller. 

## Features

- **Connection Status:** Displays whether the app is connected to the ESP8266 web server.
- **Light Control:**
    - Manual On/Off switch for the light.
    - Automatic Mode button to enable automatic light control based on ambient light.
- **Sensitivity Adjustment:** Radio buttons to set the light sensitivity to Low, Medium, or High.

## Prerequisites

1. An ESP8266 microcontroller flashed with the corresponding Litre of Light firmware.
2. The Android device and ESP8266 should be connected to the same Wi-Fi network.

## Installation

1. Clone or download this repository.
2. Open the project in Android Studio.
3. Make sure you have the Volley library added to your project. If not, add the following dependency in your module-level `build.gradle` file:
   ```gradle
   dependencies {
       implementation 'com.android.volley:volley:1.2.1' // Or latest version
       // ... other dependencies
   }

4. Important: Update the IP address "http://192.168.4.1" in the code to match the IP address assigned to your ESP8266. You can find the ESP8266's IP address in the serial monitor when it connects to your Wi-Fi network.
5. Build and run the app on your Android device.

## Usage

1. Launch the app. It will attempt to connect to the ESP8266 web server.
2. The Connection Status will indicate whether the connection was successful.
3. Manual Control:
    - Use the On/Off switch to manually turn the light on or off.
4. Automatic Mode:
    - Press the "Automatic Mode" button to enable automatic light control.
    - In this mode, the ESP8266 will automatically turn the light on when it's dark and off when there's enough ambient light.
5. Sensitivity:
    - Select the desired light sensitivity (Low, Medium, High) using the radio buttons. This determines how sensitive the system is to changes in ambient light.

## How it Works

The Android app communicates with the ESP8266 web server using HTTP requests (implemented with the Volley library). It sends GET requests to the following API endpoints on the ESP8266:

- /LightOn: Turns the light on.
- /LightOff: Turns the light off.
- /AutomaticMode: Enables automatic mode.
- /Low: Sets sensitivity to Low.
- /Medium: Sets sensitivity to Medium.
- /High: Sets sensitivity to High.
- /Status: Retrieves the current status (Mode, Sensitivity, Light level) from the ESP8266.
