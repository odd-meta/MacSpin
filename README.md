# MacSpin

Will eventually rotate the MAC address of the device at a user defined interval and handle all the WiFi teardown/rebuilding that needs to occur. Right now it just uses ip link to change the MAC address. This works for APs with no authentication. WPA2 authentication appears to get bound up trying to use the burned-in MAC address during some of the handshaking instead of the one set with ip link.

## v0.1
Click the button, change your MAC to the hard coded value. Requires root access.  APK file included in the bin/ directory for people that want to play with but not compile this application.