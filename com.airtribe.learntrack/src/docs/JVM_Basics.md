 ## What is JDK, JRE, JVM?
- JVM (Java Virtual Machine): It runs the Java bytecode. JVM is platform-dependent, which means Windows, Linux, and macOS have different JVM implementations.
- JRE (Java Runtime Environment): It contains the **JVM + libraries** required to run Java applications. It is used when we only need to execute Java programs.
- JDK (Java Development Kit): It contains **JRE + development tools* such as javac, java etc. Developers use JDK to write, compile, debug, and run Java applications.

````
Simple relationship:

JDK
└── JRE
    └── JVM
````

 ## What is Bytecode?

- When we compile a Java source file using javac, the .java file is converted into a .class file containing bytecode. 
- It is like a intermediate code which can be run on any machine with appropriate JVM.

````
HelloWorld.java
    ↓ javac
HelloWorld.class
    ↓ JVM
Machine Code
````

- Bytecode is an intermediate, platform-independent instruction set understood by the JVM. The JVM converts/interprets/compiles this bytecode into machine-specific instructions.


 ## What does "Write Once, Run Anywhere" mean?

- Java follows "Write Once, Run Anywhere" (WORA) because Java source code is compiled into platform-independent bytecode, rather than directly into machine code for a specific operating system.
- The same .class file can run on Windows, Linux, or macOS as long as the appropriate JVM is available. This is why Java applications are highly portable.