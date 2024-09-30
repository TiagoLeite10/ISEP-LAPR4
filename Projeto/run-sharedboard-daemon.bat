REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=base.daemon.sharedboard\target\base.daemon.sharedboard-1.4.0-SNAPSHOT.jar;base.daemon.sharedboard\target\dependency\*;

REM call the java VM, e.g,
java -cp %BASE_CP% eapli.base.daemon.sharedboard.BaseSharedBoardDaemon