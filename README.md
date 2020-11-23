# CS 1660 Project: Option 2, Hunter Osterhoudt
Hello and welcome to my repository for the project option 2 in CS 1660 Intro to Cloud Computing Fall Semester 2020. As the title suggests, I went for the second option for this project.

## Repository Directory
In this repo, there are four folders containing very important files! 

- inverted-index contains my files for the inverted-index implementation (Driver.java, InvertedIndexMapper.java, and InvertedIndexReducer.java), as well as the class files and a JAR.
- search contains my files for the search function implementation using Hadoop MapReduce (Driver.java, SearchMapper.java, and SearchReducer.java), as well as the class files and a JAR.
- top-N contains my files for the topN implementation (Driver.java, TopNMapper.java, and TopNReducer.java), as well as the class files and a JAR.
- projectGUI contains the files that are relevant to the Docker deployed GUI part of the project. In this folder, you can find the Dockerfile used to build my Docker image, the Java file for my GUI (projectUI.java), the JAR to run my GUI on Docker (projectUIwithDependencies.jar), and a guide to run my program (guide.txt).

## How to Run On Docker
### Assumptions
- I'm running on a Macbook Pro, so I'm not entirely sure if the run command will be different between machines.
- The user is pulling from this GitHub repository to obtain the proper files to build and run the image.
- Docker is installed and running on the machine.
- I'm using XQuartz to enable the GUI support on my machine; for Windows, it's assumed the user is using Xming and has it set up properly.
    - Find the XQuartz tutorial [here](https://www.turgaykivrak.com/posts/docker-run-gui-app/).
    - Find the Xming tutorial [here](https://docs.microsoft.com/en-us/archive/blogs/jamiedalton/windows-10-docker-gui). (**Note**, after reading the brief Xming tutorial, **Windows users** make sure to add **your** IP Address to the c:\Program Files (x86)\Xming\x0.hosts file. This is the only step for Windows I had previously not provided. Check provided link for details).
- The user knows their IP address.

### How to Run
My guidelines are, again, under the assumption that the user is using an Apple machine. However, I have included possible steps for Windows users, in parantheses following the initial step. Windows is emboldened **like this** to help the user find the step
- Have a terminal that can run the image open (XQuartz for Mac, Xming for Windows)
    - **Mac users**, a quick and easy way to get into xterm is to go to the directory you're running the Dockerfile in (for this project it will be the "projectGUI" folder), then simply enter the command "xterm" and an xterm terminal with necessary permissions will pop up.
- Ensure that if you pulled the entire GitHub repository, you are located inside of the "projectGUI" folder
- Enter the following command, using your IP address in place of the brackets: "export DISPLAY=[IP Address]:0" (**for Windows**, try using "set-variable -name DISPLAY -value [IP address]:0.0". If the tutorial provided on Xming is correct, you can skip the next step)
- Enter the following command as written: "xhost + ${hostname}" (**Windows users** can likely skip this step [explanation given in above bullet point])
- Enter the following command as written: "docker run -it -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix:ro hjosterhoudt/cs1660-project-option-2"

## Code Walkthrough with Demonstration of Applications
The link to the video (hosted on OneDrive) can be found [here.](https://pitt-my.sharepoint.com/:v:/g/personal/hjo6_pitt_edu/EVUjH0GqBBFLosVXmDev4ioB00D8psLpQ9LPkfKg9-to9g?e=xZfkEc) Demonstration begins at timestamp 28:20. The video audio kind of cuts a bit of the intro off, but all the relevant information is still in tact. At timestamp 32:15, I had received a violation from Google and my project was temporarily taken down. I'm not sure whether this means my project/cluster will be accessible, but I wanted to make the reader aware of this possible issue.
