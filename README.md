his code profiler will be used to measure the duration in time that sections of code take to execute. The profiler class should have a start() method to start a timer and a stop() method to stop the timer. In addition, the profiler will have a count() method to count how many times a section of code has been executed.

Each of these methods will take a string that identifies the timer/counter. start() and stop() will take an optional message as well that is recorded with the duration. So, to measure how long a block of code takes to execute you will do this:
