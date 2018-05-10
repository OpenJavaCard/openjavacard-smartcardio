## OpenJavaCard SmartcardIO

This is an implementation of the javax.smartcardio API. It can be used on platforms such as Android that do not provide this framework.

The project contains both an independent framework implementation and interfaces for Android smartcard access methods.

We implemented this because we need it for our Android applications.

[![Build Status](https://travis-ci.org/OpenJavaCard/openjavacard-tools.svg?branch=master)](https://travis-ci.org/OpenJavaCard/openjavacard-smartcardio)

### Features

 * Android support
   * Access to NFC cards and tokens
   * Demo app
 * Framework
   * Not fully completed

### Status

 * Not completed
 * Goals
    * Support NFC first and well
    * Want to support USB
    * Want to support Bluetooth/BLE

### Acknowledgements

We are not the first ones to think of doing something like this.

### Legal

Vast majority of the code has been developed for this project:

```
openjavacard-smartcardio: OpenJavaCard SmartcardIO
Copyright (C) 2018 Ingo Albrecht, prom@berlin.ccc.de

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 3.0 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
