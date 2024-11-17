# Prime Number Finder App

This is a Java application to find prime numbers up to a user-specified limit. The program uses multithreading to divide the workload into multiple threads, ensuring efficient processing.

## Features
- Accepts user input for the upper limit (`N`) and the number of threads.
- Divides the range into smaller parts and processes them in parallel.
- Uses Java's `ExecutorService` and `Callable` for multithreaded computation.
- Outputs the list of prime numbers and the execution time.

---

## How to Run


### Steps to Run

1. **Clone or Download the Repository**
   - If this project is hosted on a repository, clone it using:
     ```bash
     git clone https://github.com/eLQeR/IsPrimeCounter.git
     ```
   - Alternatively, download the code files and save them in a folder.

2. **Save the File**
   - Ensure the main file is named `PrimeNumberFinderApp.java`.

3. **Compile the Code**
   - Run the following command to compile the program:
     ```bash
     javac PrimeNumberFinderApp.java
     ```
   - If successful, this will generate a `PrimeNumberFinderApp.class` file in the same folder.

4. **Run the Program**
   - Execute the compiled program:
     ```bash
     java PrimeNumberFinderApp
     ```

5. **Provide Input**
   - The program will prompt you to enter:
     - An upper limit (`N`) for the prime numbers.
     - The number of threads to use.
   - Example:
     ```
     Enter the upper limit (N): 1000
     Enter the number of threads: 4
     ```

6. **View Output**
   - The program will display:
     - The list of prime numbers up to `N`.
     - The total execution time in milliseconds.


