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
  append = false
  encoder(PatternLayoutEncoder) {
    pattern = "%d{HH:mm:ss.SSS} [%thread] %logger{30} - %msg%n"
  }
}

appender("SERVER", FileAppender) {
  file = "log/server.log"
  append = false
  encoder(PatternLayoutEncoder) {
  	pattern = "%d{HH:mm:ss.SSS} %-5level %logger{30} - %msg%n"
  }
}

appender("ALL", FileAppender) {
  file = "log/all.log"
  append = false
  encoder(PatternLayoutEncoder) {
  	pattern = "%d{HH:mm:ss.SSS} %-5level %logger{30} - %msg%n"
  }
}


root(ALL, ["STDOUT"])
logger("org.thymeleaf", ALL, ["ALL", "ERROR"], false)
logger("com.hazelcast", ALL, ["ALL", "ERROR"], false)
logger("org.age", ALL, ["ALL", "ERROR"], false)
logger("org.springframework", ALL, ["ALL", "ERROR"], false)
logger("org.age.mag", ALL, ["SERVER", "ERROR", "STDOUT"])
logger("org.age.mag.hazelcast.listeners", ALL, ["LISTENER", "ERROR"], false)

