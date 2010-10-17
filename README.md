# TVRenamer

## About
TVRenamer is a Java GUI utility to rename TV episodes from TV listings  
Basically, it will take an ugly filename like 'Lost.S06E05.DD51.720p.WEB-DL.AVC-FUSiON.mkv' and rename it to 'Lost [6x05] Lighthouse.mkv'

## Features
 * Rename many different shows at once
 * Customise the format and content of the resulting filename
 * Drag & Drop or standard 'add file' interface
 * Native look & feel for all operating systems using SWT

## Usage
[Download](http://code.google.com/p/tv-renamer/downloads/list) the correct version for your operating system (OSX, Windows, Linux) and architecture (32 or 64 bit)

 * On Windows:
  1. Unzip the downloaded file somewhere, possibly your Desktop or C:\Program Files
  1. Double click the .exe file
 * On OSX:
  1. Unzip the downloaded file somewhere, possibly your Desktop or /Applications
  1. Double click the .app file
 * On Linux:
  1. Unzip the downloaded file somewhere, possibly your Desktop or somewhere in your path (/usr/local/bin/)
  1. Open an terminal and `cd` to where you unzipped the file to.  Then `cd` into the TVRenamer-&lt;version&gt; folder.  There should be run-linux.sh and tvrenamer.jar file there.
  1. Execute the run script via ./run-linux.sh
  1. If you want to add to the top gnome bar (and therefore don't need the terminal) add a 'Custom Application Launcher' with the below settings:  
    Type: Application  
    Name: TVRenamer  
    Command: <location of unzipped file from (1.)>/TVRenamer-&lt;version&gt;/run-linux.sh  
    Icon: Can be anything, perhaps [our icon](http://github.com/tvrenamer/tvrenamer/raw/master/res/icons/tvrenamer.png)  
    If the application doesn't start, or if you have problems switch the Type to be 'Application in Terminal'

## Common Problems
 * **Java version issues**. Ensure that you have Java version 5 or 6 installed.  Type `java -version` into your terminal and ensure that the output is similar to:  
    `java version "1.6.0_14"  
    Java(TM) SE Runtime Environment (build 1.6.0_14-b08)  
    Java HotSpot(TM) 64-Bit Server VM (build 14.0-b16, mixed mode)`
 * **TVRenamer version issues**.  Ensure that you are running the correct version of TVRenamer for the version of Java you are running.  If you don't have it right, you get a helpful error message on startup (when running on the terminal), like below:  
   `Exception in thread "main" java.lang.UnsatisfiedLinkError: Cannot load 32-bit SWT libraries on 64-bit JVM`  
  To fix this, see the output of `java -version`, the last line will say if you are running 64-bit Java or not.  Then [download](http://code.google.com/p/tv-renamer/downloads/list) the appropriate version.

## Getting Support and Submitting Bug Reports
You can get support in a number of ways:

 1. [View past bug reports](http://code.google.com/p/tv-renamer/issues/list?can=1&q=&colspec=ID+Type+Status+Priority+Milestone+Owner+Summary&cells=tiles) and if that is no help then [submit a new one](http://code.google.com/p/tv-renamer/issues/entry).  The developers will be notified and you should hear back shortly.

## Running in debug mode
If the application crashes it helps us greatly if you can provide us a stacktrace of what went wrong.  In order to do this, you just need to run the application in the terminal, then copy the output into [a new bug report](http://code.google.com/p/tv-renamer/issues/entry).

If the application doesn't start with a java error, [ensure that your JAVA_HOME environment variable is set correctly](http://www.oracle.com/technology/sample_code/tech/java/sqlj_jdbc/files/9i_jdbc/EnvSetup.html).

 * On Windows:
  1. Open the Windows Command Prompt (Windows key + r, then type cmd and push enter)
  1. Execute `java -jar TVRenamer-<version>.exe`
 * On OSX:
  1. Open the Terminal application (at /Applications/Utilities/Terminal.app)
  1. Navigate to where the TVRenamer application is.
  1. Execute it via `./TVRenamer-<version>.app`
 * On Linux:
  1. Open the Terminal application (in the Gnome Applications menu)
  1. Navigate to where the TVRenamer application is.
  1. Execute the run script via `./run-linux.sh`