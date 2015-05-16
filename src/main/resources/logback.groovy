import static ch.qos.logback.core.spi.FilterReply.ACCEPT
import static ch.qos.logback.core.spi.FilterReply.DENY

appender("STDOUT", ConsoleAppender) {
  filter(ch.qos.logback.classic.filter.LevelFilter) {
  	level = INFO
  	onMatch = DENY
  	onMismatch = ACCEPT
  }
  encoder(PatternLayoutEncoder) {
    pattern = "%-5level %logger{30} - %msg%n"
  }
}

appender("LISTENER", FileAppender) {
  file = "log/listeners.log"
  append = false
  encoder(PatternLayoutEncoder) {
  	pattern = "%d{HH:mm:ss.SSS} %-5level %logger{30} - %msg%n"
  }
}

appender("ERROR", FileAppender) {
  filter(ch.qos.logback.classic.filter.ThresholdFilter) {
    level = ERROR
  }
  file = "log/error.log"
  append = true
  encoder(PatternLayoutEncoder) {
    pattern = "%d{HH:mm:ss.SSS} [%thread] %logger{30} - %msg%n"
  }
}

appender("SERVER", FileAppender) {
  filter(ch.qos.logback.classic.filter.ThresholdFilter) {
    level = INFO
  }
  file = "log/server.log"
  append = false
  encoder(PatternLayoutEncoder) {
  	pattern = "%d{HH:mm:ss.SSS} %-5level %logger{30} - %msg%n"
  }
}

appender("JETTY", FileAppender) {
  file = "log/jetty.log"
  append = false
  encoder(PatternLayoutEncoder) {
  	pattern = "%d{HH:mm:ss.SSS} %-5level %logger{30} - %msg%n"
  }
}

root(ALL, ["STDOUT"])
logger("org.eclipse.jetty", ALL, ["JETTY", "ERROR"], false)
logger("org.age.mag", ALL, ["SERVER", "ERROR"])
logger("org.age.mag.hazelcast.listeners", ALL, ["LISTENER", "ERROR"], false)

