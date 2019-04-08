# josemitd-word-counter
## Counts words in a plain text file.

## Main technologies
* Java 8
* Srping Boot 1.5.19.RELEASE
* Maven 3.3.9 for build, run an deploy
* Logback for logging messages.

## Funcionalities
Given a plain text file as command line parameter, the application group different words and counts them. Result appears in console.

## Configuration
Its possible limit the size of input file. 
**Default value its 2Mbits (2000000 bytes)**
For edit, simply modify property "maximum-filesize" in application.properties file inside jar archifact.
**Size must be expressed in bytes**

## RUN APPLICATION
1. Build with maven goal: `mvn clean package` 
2. Move the generated archifact (josemitd-word-counter-1.1.0.jar) to your prefered folder
3. Running the artifact with `java -jar <path-to-folder>\josemitd-word-counter-1.1.0.jar <path-to-file>`

### From source code
1. Launch the command in parent folder directory of source code `mvn spring-boot:run -Drun.arguments=<path-to-file>`

## Output example
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - :::: STARTING WORDS COUNTER APPLICATION :::: 
* 2019-03-26 16:01:45 [main] DEBUG c.j.w.WordCounterApplication - Validating input params
* 2019-03-26 16:01:45 [main] DEBUG c.j.w.WordCounterApplication - Validating if file exits in: D:\Trabajo\Proyectos\Personales\josemitd-word-counter\src\test\resources\documentA
* 2019-03-26 16:01:45 [main] DEBUG c.j.w.service.WordCounterService - Counting words in file: D:\Trabajo\Proyectos\Personales\josemitd-word-counter\src\test\resources\documentA
* 2019-03-26 16:01:45 [main] DEBUG c.j.w.service.WordCounterService - Found 58 words
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - :::: PRINTING WORD COUNTING :::: 
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - is: 4
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - a: 4
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - the: 4
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - ship: 3
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - those: 3
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - this: 3
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - plans: 3
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - intercepted: 2
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - transmissions: 2
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - consular: 2
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - you: 2
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - were: 2
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - we: 2
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - are: 2
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - have: 2
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - ambassador: 2
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - inform: 1
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - bring: 1
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - main: 1
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - stun: 1
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - diplomatic: 1
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - she'll: 1
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - commander: 1
* 2019-03-26 16:01:45 [main] INFO  c.j.w.WordCounterApplication - her: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - me: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - if: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - all: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - in: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - want: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - vader: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - done: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - she: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - mission: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - no: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - be: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - death: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - alive: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - prisoner: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - lord: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - for: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - computer: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - not: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - found: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - tear: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - and: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - where: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - you've: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - on: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - apart: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - set: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - star: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - i: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - aaah: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - right: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - with: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - what: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - there: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - until: 1
* 2019-03-26 16:01:46 [main] INFO  c.j.w.WordCounterApplication - Started WordCounterApplication in 0.719 seconds (JVM running for 1.817)
