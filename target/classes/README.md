# LOGBack

### What is Logback Project?

Logback is one of the most widely used logging frameworks in the Java Community. It’s a replacement for its predecessor, 
Log4j. Logback offers a faster implementation than Log4j, provides more options for configuration, and more flexibility 
in archiving old log files. It builds upon a decade of experience gained in designing industrial-strength logging 
systems. The resulting product, i.e. logback, is faster and has a smaller footprint than all existing logging systems, 
sometimes by a wide margin. Just as importantly, logback offers unique and rather useful features missing in other 
logging systems.

### Logback Architecture

Three classes comprise the Logback architecture; Logger, Appender, and Layout.

* A Logger is a context for log messages. This is the class that applications interact with to create log messages.

* Appenders place log messages in their final destinations. A Logger can have more than one Appender.

* Layout prepares messages for outputting. Logback supports the creation of custom classes for formatting messages, 
as well as robust configuration options for the existing ones.

### Resources

* [Logback Project](https://logback.qos.ch/)
* [Logback documentation](https://logback.qos.ch/documentation.html)