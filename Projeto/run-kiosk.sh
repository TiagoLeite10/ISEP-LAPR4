#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export BASE_CP=base.app.kiosk.console/target/base.app.kiosk.console-1.4.0-SNAPSHOT.jar:base.app.kiosk.console/target/dependency/*;

#REM call the java VM, e.g,
java -cp $BASE_CP eapli.base.app.kiosk.console.BaseKioskApp
